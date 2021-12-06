// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.pseudoresonance.pixy2api.Pixy2;

public class PixyCam extends SubsystemBase {
  /** Creates a new PixyCam. */
  private Pixy2 pixy;
  public PixyCam() {
    pixy = Pixy2.createInstance(Pixy2.LinkType.SPI);
    pixy.init();
  }

  public void turnOn() {
    pixy.setLamp((byte) 1, (byte) 1);
    pixy.setLED(200, 30, 255);
  }

  public void turnOff() {
    pixy.setLamp((byte) 0, (byte) 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("pixyWidth", pixy.getFrameWidth());
  }
}
