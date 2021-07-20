// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class XRail extends SubsystemBase {
  private final WPI_TalonSRX tal;
  /** Creates a new XRail. */
  public XRail() {
    tal = new WPI_TalonSRX(8);
  }

  public void start() {
    tal.set(0.5);
  }

  public void stop(){
    tal.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
