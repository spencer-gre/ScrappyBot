// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

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
    tal.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 30);
    tal.setStatusFramePeriod(StatusFrameEnhanced.Status_10_Targets, 30);
    tal.configNominalOutputForward(0);
    tal.configNominalOutputReverse(0);
    tal.configPeakOutputForward(0.65);
    tal.configPeakOutputReverse(0.65);

    tal.selectProfileSlot(0, 1);
    tal.config_kF(0, Constants.kWheelGains.kF, 30);
    tal.config_kP(0, Constants.kWheelGains.kP, 30);
    tal.config_kI(0, Constants.kWheelGains.kI, 30);
    tal.config_kD(0, Constants.kWheelGains.kD, 30);


    tal.configMotionCruiseVelocity(6000, 30);
    tal.configMotionAcceleration(6000, 30);

    tal.setSelectedSensorPosition(0, 0, 30);

    tal.setNeutralMode(NeutralMode.Brake); 
  }

  public void startMotor() {
    tal.set(ControlMode.PercentOutput, 0.5);
  }

  public void stopMotor() {
    tal.set(ControlMode.PercentOutput, 0);
  }

  public void spinThreeTimes() {
    tal.set(ControlMode.MotionMagic, 250);
  }

  public double getWheelPos() {
    return tal.getSelectedSensorPosition();
  }
  
  public void resetWheelPos() {
    tal.setSelectedSensorPosition(0, 0, 10);
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
    SmartDashboard.putNumber("ColorWheel Pos", getWheelPos());
  }
}
