// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheelArm;

public class RetractColorWheel extends CommandBase {
  /** Creates a new RetractColorWheel. */
  ColorWheelArm m_cwa;

  public RetractColorWheel(ColorWheelArm cwa) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_cwa = cwa;
    addRequirements(m_cwa);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_cwa.lowerSolenoid();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_cwa.stopSolenoid();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
