// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.CANCoder;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class Drive extends SubsystemBase {
  private WPI_TalonSRX rightMaster;
  private WPI_TalonSRX leftMaster;
  private WPI_VictorSPX leftSlave;
  private WPI_TalonSRX rightSlave;
  private CANCoder leftEncoder;
  private CANCoder rightEncoder;
  private AHRS navx;

  private DifferentialDrive drive;
  private final Field2d m_field = new Field2d();
  private DifferentialDriveOdometry m_odometry;
  Pose2d m_pose = new Pose2d(0, 0, new Rotation2d());

  /** Creates a new Drive. */
  public Drive() {
    rightMaster = new WPI_TalonSRX(DriveConstants.kRightMaster);
    leftMaster = new WPI_TalonSRX(DriveConstants.kLeftMaster);
    leftSlave = new WPI_VictorSPX(DriveConstants.kLeftSlave);
    rightSlave = new WPI_TalonSRX(DriveConstants.kRightSlave);
    leftEncoder = new CANCoder(10);
    rightEncoder = new CANCoder(11);
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);

    drive = new DifferentialDrive(leftMaster, rightMaster);
    rightSlave.follow(rightMaster);
    leftSlave.follow(leftMaster);

    navx = new AHRS(SPI.Port.kMXP);
    m_odometry = new DifferentialDriveOdometry(new Rotation2d(), new Pose2d(0, 0, new Rotation2d()));

    SmartDashboard.putData("Field", m_field);
  }

  public void telopDrive(double y, double x) {
    drive.arcadeDrive(y, x);
  }

  private double nativeUnitsToDistanceMeters(double sensorCounts) {
    double motorRotations = (double) sensorCounts / 360;
    double positionMeters = motorRotations * (Math.PI * Units.inchesToMeters(6));
    return positionMeters;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Rotation2d gyroAngle = Rotation2d.fromDegrees(-navx.getAngle());
    // updateOdometry();
    SmartDashboard.putNumber("Yaw", navx.getYaw());
    Rotation2d gyroAngle = Rotation2d.fromDegrees(-navx.getYaw());
    m_pose = m_odometry.update(gyroAngle, nativeUnitsToDistanceMeters(leftEncoder.getPosition()), nativeUnitsToDistanceMeters(rightEncoder.getPosition()));
    m_field.setRobotPose(m_odometry.getPoseMeters());
    SmartDashboard.putNumber("OdometryX", m_odometry.getPoseMeters().getX());
    SmartDashboard.putNumber("OdometryY", m_odometry.getPoseMeters().getY());
    SmartDashboard.putNumber("CanCoderLeft", leftEncoder.getPosition());
  }
}
