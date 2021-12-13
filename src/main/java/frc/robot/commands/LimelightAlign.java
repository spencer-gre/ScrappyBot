// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Limelight;

public class LimelightAlign extends CommandBase {
  /** Creates a new LimelightAlign. */
  private Limelight m_limelight;
  private Drive m_drivetrain;

  private double m_steeringKP = 0.055;
  private double m_targetArea = 2.1;
  private double m_driveKP = 0.80;
  public LimelightAlign(Limelight lime, Drive drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_limelight = lime;
    m_drivetrain = drive;
    addRequirements(m_limelight, m_drivetrain);;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double right = m_limelight.getTX()*m_steeringKP; // Right Y
    double left  = (m_targetArea-m_limelight.getTA())*m_driveKP; // Left X

    if (m_limelight.isTargetValid()) {
      if (m_limelight.getTA() >= m_targetArea) {
        left = 0;
        right = 0;
      }
    } else {
     left = 0;
     right = 0;
    }

    m_drivetrain.telopDrive(-left, right);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
