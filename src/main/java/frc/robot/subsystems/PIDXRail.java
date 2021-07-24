// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants.XRailConstants;

public class PIDXRail extends PIDSubsystem {
  /** Creates a new PIDXRail. */
  private final WPI_TalonSRX tal = new WPI_TalonSRX(8);
  private final SimpleMotorFeedforward m_shooterFeedForward = new SimpleMotorFeedforward(XRailConstants.kSVolts, XRailConstants.kVVoltSecondsPerRotation);
  
  public PIDXRail() {  
    super(
        // The PIDController used by the subsystem
        new PIDController(XRailConstants.kP, XRailConstants.kI, XRailConstants.kD));
        getController().setTolerance(XRailConstants.kShooterToleranceRPS);  
        setSetpoint(XRailConstants.kShooterTargetRPS);
        tal.configFactoryDefault();
        tal.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
        tal.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
        tal.setNeutralMode(NeutralMode.Brake);
        tal.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);
  }
  

  @Override
  public void useOutput(double output, double setpoint) {
    // Use the output here
    tal.setVoltage(-output + m_shooterFeedForward.calculate(setpoint));
  }

  @Override
  public double getMeasurement() {
    // Return the process variable measurement here
    return tal.getSelectedSensorPosition();
  }
}
