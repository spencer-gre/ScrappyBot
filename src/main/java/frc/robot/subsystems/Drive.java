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
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
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
  private DifferentialDriveKinematics m_kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(24));
  private DifferentialDriveOdometry m_odometry;

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
    m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(navx.getAngle()));

    SmartDashboard.putData("Field", m_field);
  }

  public void telopDrive(double y, double x) {
    drive.arcadeDrive(y, x);
  }

  public void updateOdometry() {
    m_odometry.update(
        Rotation2d.fromDegrees(navx.getAngle()),
        nativeUnitsToDistanceMeters(leftEncoder.getPosition()),
        nativeUnitsToDistanceMeters(rightEncoder.getPosition()));
  }

  private double nativeUnitsToDistanceMeters(double sensorCounts) {
    double motorRotations = (double) sensorCounts / 4096;
    double wheelRotations = motorRotations / 10.71;
    double positionMeters = wheelRotations * (2 * Math.PI * Units.inchesToMeters(3));
    return positionMeters;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Rotation2d gyroAngle = Rotation2d.fromDegrees(-navx.getAngle());
    updateOdometry();
    m_field.setRobotPose(m_odometry.getPoseMeters());
    SmartDashboard.putNumber("OdometryX", m_odometry.getPoseMeters().getX());
    SmartDashboard.putNumber("OdometryY", m_odometry.getPoseMeters().getY());
  }
}
