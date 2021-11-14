// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.XRail;

public class LowerXRail extends CommandBase {
  /** Creates a new LowerXRail. */
  private final XRail m_xrail;
  public LowerXRail(XRail xrail) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_xrail = xrail;
    addRequirements(m_xrail);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_xrail.getEncoder() < -60){
      m_xrail.down();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_xrail.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(m_xrail.getEncoder() > -60) {
      return true;
    }
    return false;
  }
}
