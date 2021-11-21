// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.ExtendGrabber;
import frc.robot.commands.Grab;
import frc.robot.commands.LiftColorWheel;
import frc.robot.commands.LiftXRailArm;
import frc.robot.commands.LowerColorWheelArm;
import frc.robot.commands.LowerXRail;
import frc.robot.commands.LowerXRailArm;
import frc.robot.commands.RaiseColorWheelArm;
import frc.robot.commands.RaiseXRail;
import frc.robot.commands.RetractGrabber;
import frc.robot.commands.RunWinch;
import frc.robot.commands.SequentialLower;
import frc.robot.commands.SequentialRaise;
import frc.robot.commands.ShootBall;
import frc.robot.commands.SpinThreeTimes;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.ColorWheel;
import frc.robot.subsystems.ColorWheelArm;
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
  private final ColorWheel m_colorwheel = new ColorWheel();
  private final Shooter m_shooter = new Shooter();
  private final Conveyor m_conveyor = new Conveyor();
  private final Grabber m_grabber = new Grabber();
  private final XRail m_xrail = new XRail();
  private final Winch m_winch = new Winch();
  private final Arm m_arm = new Arm();
  private final ColorWheelArm m_colorWheelArm = new ColorWheelArm();


  private final Joystick m_joystick = new Joystick(0);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    final DoubleSupplier leftSupply = () -> m_joystick.getRawAxis(1);
    final DoubleSupplier rightSupply = () -> m_joystick.getRawAxis(0);
    final DoubleSupplier slider = () -> m_joystick.getThrottle();

    m_drive.setDefaultCommand(new ArcadeDrive(m_drive, leftSupply, rightSupply, slider));
    
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
    final JoystickButton eight = new JoystickButton(m_joystick, 8);
    final JoystickButton eleven = new JoystickButton(m_joystick, 11);
    final JoystickButton ten = new JoystickButton(m_joystick, 10);
    final JoystickButton five = new JoystickButton(m_joystick, 5);
    final JoystickButton four = new JoystickButton(m_joystick, 4);
    final JoystickButton three = new JoystickButton(m_joystick, 3);
    final JoystickButton six = new JoystickButton(m_joystick, 6);
    final JoystickButton nine = new JoystickButton(m_joystick, 9);
    final JoystickButton seven = new JoystickButton(m_joystick, 7);
    final JoystickButton sixteen = new JoystickButton(m_joystick, 16);
    final JoystickButton fourteen = new JoystickButton(m_joystick, 14);
    final JoystickButton thirteen = new JoystickButton(m_joystick, 13);

    trigger.whileHeld(new ShootBall(m_shooter));
    eight.whileHeld(new RunWinch(m_winch));
    ten.whenPressed(new LowerXRailArm(m_arm));
    five.whenPressed(new LiftXRailArm(m_arm));
    four.whenPressed(new ExtendGrabber(m_grabber));
    three.whenPressed(new RetractGrabber(m_grabber)); 
    thumb.whileHeld(new Grab(m_grabber));
    six.whenPressed(new RaiseColorWheelArm(m_colorWheelArm).withTimeout(1));
    nine.whenPressed(new LowerColorWheelArm(m_colorWheelArm).withTimeout(1));
    seven.whenPressed(new SpinThreeTimes(m_colorwheel));
    eleven.whileHeld(new RaiseXRail(m_xrail));
    sixteen.whileHeld(new LowerXRail(m_xrail));
    // fourteen.whenPressed(new LiftColorWheel(m_colorWheelArm).withTimeout(3));
    thirteen.whenPressed(new SequentialRaise(m_colorWheelArm));
    fourteen.whenPressed(new SequentialLower(m_colorWheelArm));


    // sixteen.whileHeld(new ManualXRail(m_xrail));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
}
