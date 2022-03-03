/* Copyright (C) 2022 Team 3602. All rights reserved. */
/* This work is licensed under the terms of the MIT license */
/* found in the root directory of this project. */

package com.team3602.robot.subsystems;

// WPILib Imports
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionSubsystem extends SubsystemBase {
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  NetworkTableEntry tv = table.getEntry("tv");

  NetworkTableEntry ledMode = table.getEntry("ledMode");
  NetworkTableEntry camMode = table.getEntry("camMode");
  NetworkTableEntry pipeline = table.getEntry("pipeline");
  NetworkTableEntry stream = table.getEntry("stream");
  NetworkTableEntry snapshot = table.getEntry("snapshot");

  double x = tx.getDouble(0.0);
  double y = ty.getDouble(0.0);
  double area = ta.getDouble(0.0);
  double validTarget = tv.getDouble(0.0);

  boolean overrideLight = false;

  public VisionSubsystem() {
    init();
  }

  public void init() {
    lightOff();
    setPiP();
  }

  public boolean overrideLight() {
    return overrideLight;
  }

  public void setOverrideLight(boolean overrideLight) {
    this.overrideLight = overrideLight;
  }

  public void takeSnapshot() {
    snapshot.setNumber(1);
  }

  // Set camera to vision processing mode
  public void visionMode() {
    camMode.setNumber(0);
  }

  // Set camera to driving mode
  public void driverMode() {
    camMode.setNumber(1);
  }

  public void setPiP() {
    stream.setNumber(0);
  }

  public void setPipline(int pipe) {
    pipeline.setNumber(pipe);
  }

  public int getPipeline() {
    return (int) pipeline.getDouble(0.0);
  }

  public double getTX() {
    return x;
  }

  public double getTY() {
    return y;
  }

  public void autoZoom() {
    if (noValidTarget()) {
      setPipline(0);
      return;
    }

    int currentPipeline = getPipeline();
    double angle = y;

    switch (currentPipeline) {
      case 0: {
        if (angle < 6.0) {
          setPipline(2);
        } else if (angle >= 6.0 && angle < 11.0) {
          setPipline(1);
        }
        break;
      }
      case 1: {
        if (angle < 8.0) {
          setPipline(2);
        } else if (angle >= 13.0) {
          setPipline(0);
        }
        break;
      }
      case 2: {
        if (angle > 7) {
          setPipline(1);
        }
        break;
      }
    }
  }

  // Forces light on
  public void lightOn() {
    ledMode.setNumber(3);
  }

  // Forces light off
  public void lightOff() {
    ledMode.setNumber(1);
  }

  // Changes light settings based on how vision pipeline is set
  public void lightAuto() {
    ledMode.setNumber(0);
  }

  // Checks if there are no valid targets, which is then sent to isFinished()
  public boolean validTarget() {
    validTarget = tv.getDouble(0.0);
    return validTarget != 0.0;
  }

  // Returns an error if there are no valid targets
  public boolean noValidTarget() {
    validTarget = tv.getDouble(0.0);
    if (validTarget == 0) {
      System.out.println("ERROR: Vision Target was lost");
      return true;
    } else {
      return false;
    }
  }

  // Smart Dashboard information
  public void logDataToSmartDashboard() {
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
  }

}
