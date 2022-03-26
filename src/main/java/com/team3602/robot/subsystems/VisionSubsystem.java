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

import com.team3602.robot.Constants;

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
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-robomos");

  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  NetworkTableEntry tv = table.getEntry("tv");

  NetworkTableEntry ledMode = table.getEntry("ledMode");
  NetworkTableEntry camMode = table.getEntry("camMode");
  NetworkTableEntry pipeline = table.getEntry("pipeline");
  NetworkTableEntry stream = table.getEntry("stream");
  NetworkTableEntry snapshot = table.getEntry("snapshot");

  // double x = tx.getDouble(0.0);
  // double y = ty.getDouble(0.0);
  // double area = ta.getDouble(0.0);
  // double validTarget = tv.getDouble(0.0);

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
    //setPipline(0);
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

  public void resetSnapshot() {
    snapshot.setNumber(0);
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

  public double GetTX()
    {
    return tx.getDouble(0.0);
    }

  public double GetTY()
    {
    return ty.getDouble(0.0);
    }

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
    //ledMode.setNumber(1);
    ledMode.setNumber(3);
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
    double validTarget = tv.getDouble(0.0);
    return validTarget != 0.0;
  }

  /**
   * @return a boolean if there is no valid target
   */
  public boolean noValidTarget() {
    double validTarget = tv.getDouble(0.0);
    if (validTarget == 0) {
      if(Constants.testingEnabled)
      {
        System.out.println("ERROR: Vision Target was lost");
      }
      return true;
    } else {
      return false;
    }
  }

  /**
   * Method to log information to the Smart Dashboard.
   */
  public void logDataToSmartDashboard() {
    if(Constants.testingEnabled)
    {
    SmartDashboard.putNumber("LimelightX", GetTX());
    SmartDashboard.putNumber("LimelightY", GetTY());
    SmartDashboard.putNumber("LimelightArea", ta.getDouble(0.0));
    SmartDashboard.putBoolean("ValidTargetightArea", validTarget());

    }


  }
}
