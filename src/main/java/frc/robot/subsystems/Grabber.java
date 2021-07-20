// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Grabber extends SubsystemBase {
  private WPI_VictorSPX spx;
  /** Creates a new Grabber. */
  public Grabber() {
    spx = new WPI_VictorSPX(Constants.SPX_GRABBER);
  }

  public void startGrab() {
    spx.set(0.25);
  }

  public void stopGrab() {
    spx.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
