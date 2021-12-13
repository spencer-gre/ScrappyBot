// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor;

public class CycleConveyor extends CommandBase {
  /** Creates a new CycleConveyor. */
  private Conveyor m_conveyor;
  public CycleConveyor(Conveyor conveyor) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_conveyor = conveyor;
    addRequirements(m_conveyor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_conveyor.overrideConveyor();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_conveyor.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // No Balls
    if(m_conveyor.getOne()) {
      return true;
    }
    return false;
  }
}
