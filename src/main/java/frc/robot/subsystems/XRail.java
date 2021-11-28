// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class XRail extends SubsystemBase {
  private final WPI_TalonSRX tal;
  private final TalonSRXConfiguration config;
  /** Creates a new XRail. */
  public XRail() {
    tal = new WPI_TalonSRX(8);
    config = new TalonSRXConfiguration();

    config.primaryPID.selectedFeedbackSensor = FeedbackDevice.QuadEncoder;
    config.forwardLimitSwitchSource = LimitSwitchSource.Deactivated;
    config.reverseLimitSwitchSource = LimitSwitchSource.Deactivated;
    config.forwardLimitSwitchNormal = LimitSwitchNormal.Disabled;
    config.reverseLimitSwitchNormal = LimitSwitchNormal.Disabled;
    tal.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
    
    tal.configAllSettings(config);    // config.
    tal.setNeutralMode(NeutralMode.Brake);
  	tal.setSensorPhase(false);
		tal.setInverted(false);
    tal.setSelectedSensorPosition(0, 0, 30);   
    // tal.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
    // tal.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);

  }

  public void down() {
    if(tal.getSensorCollection().isFwdLimitSwitchClosed()) {
      tal.set(ControlMode.PercentOutput, -0.75);
    }
  }

  public void up() {
    if(tal.getSensorCollection().isFwdLimitSwitchClosed()) {
      tal.set(ControlMode.PercentOutput, 1);
    }
  }

  public void zero() {
    tal.setSelectedSensorPosition(0, 0, 30);
  }

  public void stop(){
    tal.stopMotor();
  }

  public double getEncoder() {
    return tal.getSelectedSensorPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("XRail", tal.getSelectedSensorPosition());
  }
}
