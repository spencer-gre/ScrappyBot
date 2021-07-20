// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Winch extends SubsystemBase {
  private WPI_TalonSRX winchMaster;
  private WPI_VictorSPX winchSlave;
  /** Creates a new Winch. */
  public Winch() {
    winchMaster = new WPI_TalonSRX(Constants.SRX_WINCH_MASTER);
    winchSlave = new WPI_VictorSPX(Constants.SPX_WINCH_SLAVE);
    winchSlave.follow(winchMaster);
  }

  public void start() {
    winchMaster.set(0.1);
  }

  public void stop() {
    winchMaster.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
