// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.XRailConstants;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.Grab;
import frc.robot.commands.ManualXRail;
import frc.robot.commands.OverrideConveyor;
import frc.robot.commands.RunWinch;
import frc.robot.commands.RunXRail;
import frc.robot.commands.ShootBall;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Grabber;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Winch;
import frc.robot.subsystems.XRail;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drive m_drive = new Drive();
  private final Shooter m_shooter = new Shooter();
  private final Conveyor m_conveyor = new Conveyor();
  private final Grabber m_grabber = new Grabber();
  private final XRail m_xrail = new XRail();
  private final Winch m_winch = new Winch();


  private final Joystick m_joystick = new Joystick(0);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    final DoubleSupplier leftSupply = () -> m_joystick.getRawAxis(1);
    final DoubleSupplier rightSupply = () -> m_joystick.getRawAxis(0);
    m_drive.setDefaultCommand(new ArcadeDrive(m_drive, leftSupply, rightSupply));
    
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    final JoystickButton trigger = new JoystickButton(m_joystick, 1);
    final JoystickButton thumb = new JoystickButton(m_joystick, 2);
    final JoystickButton three = new JoystickButton(m_joystick, 3);
    final JoystickButton eight = new JoystickButton(m_joystick, 8);
    final JoystickButton eleven = new JoystickButton(m_joystick, 11);
    final JoystickButton sixteen = new JoystickButton(m_joystick, 16);
    final JoystickButton twelve = new JoystickButton(m_joystick, 12);



    trigger.whileHeld(new ShootBall(m_shooter));
    thumb.whileHeld(new OverrideConveyor(m_conveyor));
    three.whileHeld(new Grab(m_grabber)); 
    eleven.whileHeld(new ManualXRail(m_xrail, true));
    twelve.whenPressed(new RunXRail(m_xrail, -8000));
    sixteen.whileHeld(new ManualXRail(m_xrail, false));
    eight.whileHeld(new RunWinch(m_winch));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
}
