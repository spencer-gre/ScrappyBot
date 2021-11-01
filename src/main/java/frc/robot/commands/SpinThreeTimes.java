// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheel;

public class SpinThreeTimes extends CommandBase {
  /** Creates a new SpinThreeTimes. */
  private final ColorWheel m_ColorWheel;
  public SpinThreeTimes(ColorWheel color) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_ColorWheel = color;
    addRequirements(m_ColorWheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_ColorWheel.resetWheelPos();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_ColorWheel.spinThreeTimes();
    SmartDashboard.putNumber("COLORWHEELPOS", m_ColorWheel.getWheelPos());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_ColorWheel.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(m_ColorWheel.getWheelPos() >= 250) {
      return true;
    }
    return false;
  }
}
