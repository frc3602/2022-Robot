/*
 * @(#)VisionSubsystem.java        1.0 22/03/04
 *
 * Copyright (c) 2022 Team 3602
 * 500 S Lincoln Rd, Escanaba, Michigan, 49829, U.S.A.
 * All rights reserved.
 *
 * This software is licensed under the terms of the GPLv3 license
 * found in the root directory of this project.
 */

package com.team3602.robot.subsystems;

// WPILib Imports
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VisionSubsystem class provides all methods and functionality of the
 * robot's vision processing.
 *
 * @version 1.0 04 Mar 2022
 * @author Cody Wellman
 */
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

  /**
   * Constructor for {@link VisionSubsystem} class to run the {@link #init()}
   * method.
   */
  public VisionSubsystem() {
    init();
  }

  /**
   * Method to set the light off and to set the stream on robot initialization.
   */
  public void init() {
    lightOff();
    setPiP();
  }

  /**
   * @return the state of light
   */
  public boolean overrideLight() {
    return overrideLight;
  }

  /**
   * Method to override the light on the lightlight.
   * 
   * @param overrideLight whether the light is overridden or not
   */
  public void setOverrideLight(boolean overrideLight) {
    this.overrideLight = overrideLight;
  }

  /**
   * Method to save a screenshot of what is currently displayed on the limelight
   * stream.
   */
  public void takeSnapshot() {
    snapshot.setNumber(1);
  }

  /**
   * Method to set the lightlight to vision processing mode.
   */
  public void visionMode() {
    camMode.setNumber(0);
  }

  /**
   * Method to set the lightlight to driving mode.
   */
  public void driverMode() {
    camMode.setNumber(1);
  }

  /**
   * Method to set the stream on the lightlight.
   */
  public void setPiP() {
    stream.setNumber(0);
  }

  /**
   * Method to set the pipeline on the lightlight.
   * 
   * @param pipe specific pipline to set
   */
  public void setPipline(int pipe) {
    pipeline.setNumber(pipe);
  }

  /**
   * @return the current pipeline value from the limelight
   */
  public int getPipeline() {
    return (int) pipeline.getDouble(0.0);
  }

  /**
   * @return the current TX value from the limelight
   */
  public double getTX() {
    return x;
  }

  /**
   * @return the current TY value from the limelight
   */
  public double getTY() {
    return y;
  }

  /**
   * Method to automatically zoom the lightlight.
   */
  // // public void autoZoom() {
  //   if (noValidTarget()) {
  //     setPipline(0);
  //     return;
  //   }

  //   int currentPipeline = getPipeline();
  //   double angle = y;

  //   switch (currentPipeline) {
  //   case 0: {
  //     if (angle < 6.0) {
  //       setPipline(2);
  //     } else if (angle >= 6.0 && angle < 11.0) {
  //       setPipline(1);
  //     }
  //     break;
  //   }
  //   case 1: {
  //     if (angle < 8.0) {
  //       setPipline(2);
  //     } else if (angle >= 13.0) {
  //       setPipline(0);
  //     }
  //     break;
  //   }
  //   case 2: {
  //     if (angle > 7) {
  //       setPipline(1);
  //     }
  //     break;
  //   }
  //   }
  // }

  /**
   * Method to force the limelight light on.
   */
  public void lightOn() {
    ledMode.setNumber(3);
  }

  /**
   * Method to force the limelight light off.
   */
  public void lightOff() {
    ledMode.setNumber(1);
  }

  /**
   * Method to change the limelight light settings based on how the pipeline is
   * set.
   */
  public void lightAuto() {
    ledMode.setNumber(0);
  }

  /**
   * Method to check if there is a valid target.
   *
   * @return validTarget as a double
   */
  public boolean validTarget() {
    validTarget = tv.getDouble(0.0);
    return validTarget != 0.0;
  }

  /**
   * @return a boolean if there is no valid target
   */
  public boolean noValidTarget() {
    validTarget = tv.getDouble(0.0);
    if (validTarget == 0) {
      System.out.println("ERROR: Vision Target was lost");
      return true;
    } else {
      return false;
    }
  }

  /**
   * Method to log information to the Smart Dashboard.
   */
  public void logDataToSmartDashboard() {
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
  }
}
