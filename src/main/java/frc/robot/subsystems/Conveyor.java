// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Conveyor extends SubsystemBase {
  private WPI_TalonSRX tal;
  private AnalogInput ultrasound;
  private Counter counter;
  /** Creates a new Conveyor. */
  public Conveyor() {
    tal = new WPI_TalonSRX(Constants.SRX_CONVEYOR);
    
    ultrasound = new AnalogInput(1);
    counter = new Counter(0);
    counter.clearDownSource();

    tal.configFactoryDefault();
    tal.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
    tal.setSensorPhase(true);
    tal.setInverted(false);
  }

  public void overrideConveyor() {
    tal.set(0.6);
  }

  public int getCount() {
    return counter.get();
  }

  public double getEncoder() {
    return tal.getSelectedSensorPosition();
  }

  public int getUltrasound() {
    return ultrasound.getValue();
  }

  public void stop() {
    tal.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // if(getPhotosensor()){
    //   incCount();
    // }
    SmartDashboard.putNumber("ConveyorEncoder", getEncoder());
    SmartDashboard.putNumber("Count", getCount());
    SmartDashboard.putNumber("Ultrasound", getUltrasound());
  }
}
