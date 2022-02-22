// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.frc3602.robot.subsystems;

// WPILib Imports
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightSubsystem extends SubsystemBase {
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

  public LimelightSubsystem() {
    Init();
  }

  public void Init() {
    LightOff();
    SetPiP();
  }

  public boolean OverrideLight() {
    return overrideLight;
  }

  public void SetOverrideLight(boolean overrideLight) {
    this.overrideLight = overrideLight;
  }

  public void TakeSnapshot() {
    snapshot.setNumber(1);
  }

  // Set camera to vision processing mode
  public void VisionMode() {
    camMode.setNumber(0);
  }

  // Set camera to driving mode
  public void DriverMode() {
    camMode.setNumber(1);
  }

  public void SetPiP() {
    stream.setNumber(0);
  }

  public void SetPipline(int pipe) {
    pipeline.setNumber(pipe);
  }

  public int GetPipeline() {
    return (int) pipeline.getDouble(0.0);
  }

  public double GetTX() {
    return x;
  }

  public double GetTY() {
    return y;
  }

  public void AutoZoom() {
    if (NoValidTarget()) {
      SetPipline(0);
      return;
    }

    int currentPipeline = GetPipeline();
    double angle = y;

    switch (currentPipeline) {
      case 0: {
        if (angle < 6.0) {
          SetPipline(2);
        } else if (angle >= 6.0 && angle < 11.0) {
          SetPipline(1);
        }
        break;
      }
      case 1: {
        if (angle < 8.0) {
          SetPipline(2);
        } else if (angle >= 13.0) {
          SetPipline(0);
        }
        break;
      }
      case 2: {
        if (angle > 7) {
          SetPipline(1);
        }
        break;
      }
    }
  }

  // Forces light on
  public void LightOn() {
    ledMode.setNumber(3);
  }

  // Forces light off
  public void LightOff() {
    ledMode.setNumber(1);
  }

  // Changes light settings based on how vision pipeline is set
  public void LightAuto() {
    ledMode.setNumber(0);
  }

  // Checks if there are no valid targets, which is then sent to isFinished()
  public boolean ValidTarget() {
    validTarget = tv.getDouble(0.0);
    return validTarget != 0.0;
  }

  // Returns an error if there are no valid targets
  public boolean NoValidTarget() {
    validTarget = tv.getDouble(0.0);
    if (validTarget == 0) {
      System.out.println("ERROR: Vision Target was lost");
      return true;
    } else {
      return false;
    }
  }

  // Smart Dashboard information
  public void LogDataToSmartDashboard() {
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
  }

}
