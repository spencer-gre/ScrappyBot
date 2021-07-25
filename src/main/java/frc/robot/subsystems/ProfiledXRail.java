// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.controller.ElevatorFeedforward;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDSubsystem;
import frc.robot.Constants.XRailConstants;

public class ProfiledXRail extends ProfiledPIDSubsystem {
  /** Creates a new ProfiledXRail. */
  private final WPI_TalonSRX m_tal = new WPI_TalonSRX(XRailConstants.kSRXXRail);
  private final ElevatorFeedforward m_feedforward = new ElevatorFeedforward(XRailConstants.kS, XRailConstants.kG, XRailConstants.kV, XRailConstants.kA);
  public ProfiledXRail() {
    super(
        // The ProfiledPIDController used by the subsystem
        new ProfiledPIDController(
            XRailConstants.kP,
            XRailConstants.kI,
            XRailConstants.kD,
            // The motion profile constraints
            new TrapezoidProfile.Constraints(
				XRailConstants.kMaxVelocityRadPerSecond, 
				XRailConstants.kMaxAccelerationRadPerSecSquared)), 
		0);
  }

  @Override
  public void useOutput(double output, TrapezoidProfile.State setpoint) {
    // Use the output (and optionally the setpoint) here
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return 0;
  }
}
