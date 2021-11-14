// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class XRail extends SubsystemBase {
  private final WPI_TalonSRX tal;
  /** Creates a new XRail. */
  public XRail() {
    tal = new WPI_TalonSRX(8);
    /* Factory default hardware to prevent unexpected behavior */
		tal.configFactoryDefault();

		/* Configure Sensor Source for Pirmary PID */
		tal.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 30);

		/* set deadband to super small 0.001 (0.1 %).
			The default deadband is 0.04 (4 %) */
		tal.configNeutralDeadband(0.001, 30);

		/**
		 * Configure Talon SRX Output and Sesnor direction accordingly Invert Motor to
		 * have green LEDs when sdriving Talon Forward / Requesting Postiive Output Phase
		 * sensor to have positive increment when driving Talon Forward (Green LED)
		 */
		tal.setSensorPhase(true);
		tal.setInverted(true);

		/* Set relevant frame periods to be at least as fast as periodic rate */
		tal.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 30);
		tal.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 30);

		/* Set the peak and nominal outputs */
		tal.configNominalOutputForward(0, 30);
		tal.configNominalOutputReverse(0, 30);
		tal.configPeakOutputForward(1, 30);
		tal.configPeakOutputReverse(-1, 30);

		/* Set Motion Magic gains in slot0 - see documentation */
		tal.selectProfileSlot(0, 0);
		tal.config_kF(0, Constants.kGains.kF, 30);
		tal.config_kP(0, Constants.kGains.kP, 30);
		tal.config_kI(0, Constants.kGains.kI, 30);
		tal.config_kD(0, Constants.kGains.kD, 30);

		/* Set acceleration and vcruise velocity - see documentation */
		tal.configMotionCruiseVelocity(3000, 30);
		tal.configMotionAcceleration(3000, 30);

		/* Zero the sensor once on robot boot up */
    tal.setSelectedSensorPosition(0, 0, 30);   
    tal.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
    tal.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);

  }

  public void down() {
    tal.set(ControlMode.PercentOutput, -1);
  }

  public void up() {
    tal.set(ControlMode.PercentOutput, 1);
  }

  public void max() {
    tal.set(ControlMode.MotionMagic, -3000);
  }

  public void min() {
    tal.set(ControlMode.MotionMagic, 0);
  }

  public void zero() {
    tal.setSelectedSensorPosition(0, 0, 30);
  }

  public void stop(){
    tal.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("XRail", tal.getSelectedSensorPosition());
  }
}
