// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
  /** Creates a new Arm. */
  private Spark spark;

  private AnalogInput seatMotor;
  private final WPI_TalonSRX tal;

  public Arm() {
    spark = new Spark(0);
    seatMotor = new AnalogInput(0);
    tal = new WPI_TalonSRX(8);
    seatMotor.resetAccumulator();
    // tal.setSelectedSensorPosition(0);
    // tal.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
  }

  public void liftArm() {
    if (getRevLimitSwitch()) {
      spark.set(-0.75);
    }
  }

  public void lowerArm() {
    if (getFwdLimitSwitch()) {
      spark.set(0.75);
    }
  }

  public boolean getFwdLimitSwitch() {
    boolean closed = tal.getSensorCollection().isFwdLimitSwitchClosed();
    SmartDashboard.putBoolean("fwd", closed);
    return closed;
  }

  public boolean getRevLimitSwitch() {
    boolean closed = tal.getSensorCollection().isRevLimitSwitchClosed();
    SmartDashboard.putBoolean("reverse", closed);
    return closed;
  }

  public double getCount() {
    double count = seatMotor.getAccumulatorCount();
    SmartDashboard.putNumber("ArmCount", count);
    return spark.getRaw();
  }

  public void stopArm() {
    spark.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (getRevLimitSwitch()) {
      seatMotor.resetAccumulator();
    }
    getFwdLimitSwitch();
    getRevLimitSwitch();
  }
}
