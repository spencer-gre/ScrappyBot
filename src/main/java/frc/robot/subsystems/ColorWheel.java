// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorWheel extends SubsystemBase {
  /** Creates a new ColorWheel. */
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(I2C.Port.kOnboard);

  private final ColorMatch m_colorMatch = new ColorMatch();
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  private final WPI_TalonSRX tal = new WPI_TalonSRX(4);

  char reqColor;
  char colorString;
  Boolean colorMatched = false;

  public ColorWheel() {
    m_colorMatch.addColorMatch(kGreenTarget);
    m_colorMatch.addColorMatch(kRedTarget);
    m_colorMatch.addColorMatch(kBlueTarget);
    m_colorMatch.addColorMatch(kYellowTarget);

    tal.configFactoryDefault();
    tal.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
    tal.setSensorPhase(true);
    tal.setInverted(false);
  }

  public void startMotor() {
    tal.set(ControlMode.PercentOutput, 0.5);
  }

  public void stopMotor() {
    tal.set(ControlMode.PercentOutput, 0);
  }

  public char getRequired() {
    final String gData = DriverStation.getInstance().getGameSpecificMessage();
    if (gData.length() > 0) {
      reqColor = gData.charAt(0);
    }
    return reqColor;
  }

  public char getColor() {
    final Color detectedColor = m_colorSensor.getColor();
    final ColorMatchResult match = m_colorMatch.matchClosestColor(detectedColor);

    if (match.color == kBlueTarget) {
      colorString = 'B';
    } else if (match.color == kRedTarget) {
      colorString = 'R';
    } else if (match.color == kGreenTarget) {
      colorString = 'G';
    } else if (match.color == kYellowTarget) {
      colorString = 'Y';
    } else {
      colorString = 'U';
    }
    return colorString;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putString("DetectedColor", Character.toString(getColor()));
    SmartDashboard.putString("ReqColor", Character.toString(getRequired()));
  }
}
