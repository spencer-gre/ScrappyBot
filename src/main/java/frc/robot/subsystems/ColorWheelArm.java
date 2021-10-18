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
    serv = new PWM(1);
    serv.setBounds(2.0, 1.52, 1.5, 1.48, 1);
  }

  public void liftServo() {
    serv.setPosition(0.3);
  }

  public void lowerServo() {
    serv.setPosition(0);
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
