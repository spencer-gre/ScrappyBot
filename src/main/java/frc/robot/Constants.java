// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.I2C;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final int LEFT_MASTER = 5;
    public static final int LEFT_SLAVE = 6; // ! Currently using an SRX as slave.
    public static final int RIGHT_MASTER = 2;
    public static final int RIGHT_SLAVE = 3;

    public static final int SPARK_SHOOTER_MASTER = 40;
    public static final int SPARK_SHOOTER_SLAVE = 41;

    public static final int SPX_GRABBER = 1;
    public static final int SRX_CONVEYOR = 34;
    public static final int SRX_WINCH_MASTER = 7;
    public static final int SPX_WINCH_SLAVE = 9;
    
    public static final Gains kGains = new Gains(0.2, 0.0, 0.0, 0.2, 0, 1.0);

    public static final class XRailConstants {
        public static final int[] kEncoderPorts = new int[] {4, 5};
        public static final boolean kEncoderReversed = false;
        public static final int kEncoderCPR = 1024;
        public static final double kEncoderDistancePerPulse =
            // Distance units will be rotations
            1.0 / (double) kEncoderCPR;
    
        public static final int kShooterMotorPort = 4;
        public static final int kFeederMotorPort = 5;
    
        public static final double kShooterFreeRPS = 5300;
        public static final double kShooterTargetRPS = -4000;
        public static final double kShooterToleranceRPS = 50;
    
        // These are not real PID gains, and will have to be tuned for your specific robot.
        public static final double kP = 1.87;
        public static final double kI = 0;
        public static final double kD = 0.36;
    
        // On a real robot the feedforward constants should be empirically determined; these are
        // reasonable guesses.
        public static final double kSVolts = 0.05;
        public static final double kVVoltSecondsPerRotation =
            // Should have value 12V at free speed...
            12.0 / kShooterFreeRPS;
    
        public static final double kFeederSpeed = 0.5;
      }



      
}


