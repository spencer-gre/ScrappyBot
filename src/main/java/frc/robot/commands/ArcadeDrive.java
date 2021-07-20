// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drive;

public class ArcadeDrive extends CommandBase {
  private Drive m_Drive;
  private RobotContainer m_robotontainer;
  private DoubleSupplier m_leftOutput, m_rightOutput;

  /** Creates a new ArcadeDrive. */
  public ArcadeDrive(Drive drive, DoubleSupplier leftValue, DoubleSupplier rightValue) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_leftOutput = leftValue;
    m_rightOutput = rightValue;
    m_Drive = drive;

    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Drive.telopDrive(m_leftOutput.getAsDouble(), m_rightOutput.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
