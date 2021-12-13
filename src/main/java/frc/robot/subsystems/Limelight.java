// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  /** Creates a new Limelight. */
  private final NetworkTable m_limelightTable;
  private double tv, tx, ta;
  private ArrayList<Double> m_targetList;
  private final int MAX_ENTRIES = 50;
  
  public Limelight() {
    m_limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    m_targetList = new ArrayList<Double>(MAX_ENTRIES); 
  }

  @Override
  public void periodic() {
    tv = m_limelightTable.getEntry("tv").getDouble(0);
    tx = m_limelightTable.getEntry("tx").getDouble(0);
    ta = m_limelightTable.getEntry("ta").getDouble(0);

    if (m_targetList.size() >= MAX_ENTRIES) {
      m_targetList.remove(0);
    }
    m_targetList.add(ta);
  }

  public double getTX() {
    return tx;
  }

  public double getTA() {
    double sum = 0;

    for (Double num : m_targetList) { 		      
      sum += num.doubleValue();
    }
    return sum/m_targetList.size();
  }

  public boolean isTargetValid() {
    return (tv == 1.0); 
  }
}
