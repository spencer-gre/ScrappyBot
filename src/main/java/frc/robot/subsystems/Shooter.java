// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  private CANSparkMax shooterMaster;
  private CANSparkMax shooterSlave;

  public Shooter() {
    shooterMaster = new CANSparkMax(Constants.SPARK_SHOOTER_MASTER, MotorType.kBrushless);
    shooterSlave = new CANSparkMax(Constants.SPARK_SHOOTER_SLAVE, MotorType.kBrushless);

    shooterSlave.follow(shooterMaster, true);
  }

  public void shoot() {
    shooterMaster.set(-1.0);
  }

  public void noShoot() {
    shooterMaster.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
