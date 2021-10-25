// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorWheelArm extends SubsystemBase {
  /** Creates a new ColorWheelArm. */
  private PWM serv;
  public ColorWheelArm() {
    serv = new Servo(1);
  }

  public void liftServo() {
    serv.setPosition(0.38);
  }

  public void lowerServo() {
    serv.setPosition(1);
  }

  public void stopServo() {
    serv.stopMotor();
  }

  public double get() {
    return serv.getPosition();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Servo", serv.getPosition());
  }
}
