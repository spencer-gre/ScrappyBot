// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class XRail extends SubsystemBase {
  private final WPI_TalonSRX tal;
  /** Creates a new XRail. */
  public XRail() {
    tal = new WPI_TalonSRX(8);
    tal.configFactoryDefault();
    tal.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
    tal.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
    tal.setNeutralMode(NeutralMode.Brake);
    tal.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
    tal.setSensorPhase(false);
    tal.setInverted(false);
    tal.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 30);
    tal.setStatusFramePeriod(StatusFrameEnhanced.Status_10_Targets, 30);
    tal.configNominalOutputForward(0, 30);
    tal.configNominalOutputReverse(0, 30);
    tal.configPeakOutputForward(0.25);
    tal.configPeakOutputReverse(0.25);
    
    tal.selectProfileSlot(0, 1);
    tal.config_kF(0, Constants.kGains.kF, 30);
    tal.config_kP(0, Constants.kGains.kP, 30);
    tal.config_kI(0, Constants.kGains.kI, 30);
    tal.config_kD(0, Constants.kGains.kD, 30);


    tal.configMotionCruiseVelocity(15000, 30);
    tal.configMotionAcceleration(6000, 30);

    tal.setSelectedSensorPosition(0, 0, 30);
  }

  public void operate(boolean direction) {
    if(direction == true) {
      tal.set(0.75);
    }
    if(direction == false) {
      tal.set(-0.75);
    }
  }

  public void pos(double pos) {
    tal.set(ControlMode.MotionMagic, pos);
  }

  public void stop(){
    tal.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("XRail", tal.getSelectedSensorPosition());
  }
}
