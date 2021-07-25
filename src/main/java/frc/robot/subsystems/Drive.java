// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;

public class Drive extends SubsystemBase {
  private WPI_TalonSRX rightMaster;
  private WPI_TalonSRX leftMaster;
  private WPI_VictorSPX leftSlave;
  private WPI_TalonSRX rightSlave;

  private DifferentialDrive drive;

  /** Creates a new Drive. */
  public Drive() {
    rightMaster = new WPI_TalonSRX(DriveConstants.kRightMaster);
    leftMaster = new WPI_TalonSRX(DriveConstants.kLeftMaster);
    leftSlave = new WPI_VictorSPX(DriveConstants.kLeftSlave);
    rightSlave = new WPI_TalonSRX(DriveConstants.kRightSlave);
    
    drive = new DifferentialDrive(leftMaster, rightMaster);

    leftSlave.follow(leftMaster);
    rightSlave.follow(rightMaster);
  }

  public void telopDrive(double y, double x) {
    drive.arcadeDrive(y, x);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
