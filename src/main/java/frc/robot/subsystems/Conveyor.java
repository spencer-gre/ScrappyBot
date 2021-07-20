// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Conveyor extends SubsystemBase {
  private WPI_TalonSRX tal;
  private AnalogInput ultrasound;
  private DigitalInput photosensor;
  private int count;
  /** Creates a new Conveyor. */
  public Conveyor() {
    tal = new WPI_TalonSRX(Constants.SRX_CONVEYOR);
    // tal.configFactoryDefault();
    // tal.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
    // tal.setSensorPhase(false);
    // tal.setInverted(false);
    // tal.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 30);
    // tal.setStatusFramePeriod(StatusFrameEnhanced.Status_10_Targets, 30);
    // tal.configNominalOutputForward(0);
    // tal.configNominalOutputReverse(0);
    // tal.configPeakOutputForward(0.25);
    // tal.configPeakOutputReverse(0.25);
    
    // tal.selectProfileSlot(0, 1);
    // tal.config_kF(0, Constants.kGains.kF, 30);
    // tal.config_kP(0, Constants.kGains.kP, 30);
    // tal.config_kI(0, Constants.kGains.kI, 30);
    // tal.config_kD(0, Constants.kGains.kD, 30);


    // tal.configMotionCruiseVelocity(15000, 30);
    // tal.configMotionAcceleration(6000, 30);

    // tal.setSelectedSensorPosition(0, 0, 30);
  }

  public void overrideConveyor() {
    tal.set(0.6);
  }

  public void stop() {
    tal.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
