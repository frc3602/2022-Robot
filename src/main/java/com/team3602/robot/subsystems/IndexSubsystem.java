/*
 * @(#)IndexSubsystem.java        1.0 22/03/03
 *
 * Copyright (c) 2022 Team 3602
 * 500 S Lincoln Rd, Escanaba, Michigan, 49829, U.S.A.
 * All rights reserved.
 *
 * This software is licensed under the terms of the GPLv3 license
 * found in the root directory of this project.
 */

package com.team3602.robot.subsystems;

import com.team3602.robot.RobotContainer;
import com.team3602.robot.Constants.Index;

// Phoenix Imports
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

// WPILib Imports
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The IndexSubsystem class provides all methods and functionality of the
 * robot's magazine / indexing system and intake.
 *
 * @version 1.0 04 Mar 2022
 * @author Cody Wellman
 */
public class IndexSubsystem extends SubsystemBase {
  // Creates the motors & controllers and sets the CAN IDs for each one
  WPI_TalonSRX indexMotorTop = new WPI_TalonSRX(Index.indexMotorTopCANID);
  WPI_TalonSRX indexMotorBottom = new WPI_TalonSRX(Index.indexMotorBottomCANID);

  DigitalInput indexSensorTop = new DigitalInput(Index.indexSensorTopID);
  DigitalInput indexSensorBottom = new DigitalInput(Index.indexSensorBottomID);

  int ballCount = 0;
  int count = 0;

  boolean previousTopState = false;
  boolean previousBottomState = false;

  public IndexSubsystem() {
    // Sets the motors to default configuration
    indexMotorTop.configFactoryDefault();
    indexMotorBottom.configFactoryDefault();

    indexMotorTop.setNeutralMode(NeutralMode.Brake);
    indexMotorBottom.setNeutralMode(NeutralMode.Brake);
  }

  /*
   * public void advanceIndex(double speed) { if (speed > 1.0) { speed = 1.0; } if
   * (speed < -1.0) { speed = -1.0; }
   * 
   * indexMotorTop.set(speed * -1.0 * Index.motorTopSpeed);
   * indexMotorBottom.set(speed * -1.0 * Index.motorBottomSpeed);
   * 
   * if (speed != 0.0) { RobotContainer.indexSubsystem.spinIntake(); } else {
   * RobotContainer.indexSubsystem.stopIntake(); } }
   */

  /*
   * public void stopIndex() { advanceIndex(0.0); }
   */

  public void initSensors() {
  }

  public int ballCount() {
    return ballCount;
  }

  public boolean indexSensorTop() {
    return indexSensorTop.get();
  }

  public boolean indexSensorBottom() {
    return indexSensorBottom.get();
  }

  public boolean isIndexFull() {
    return (ballCount >= Index.maxBalls);
  }

  public void checkSensors() {
    count++;
    if (count > 500) {

    }
  }

  public void indexIn() {
    indexMotorTop.set(ControlMode.PercentOutput, -1.0);
    indexMotorBottom.set(ControlMode.PercentOutput, -1.0);
  }

  public void indexOut() {
    indexMotorTop.set(ControlMode.PercentOutput, 1.0);
    indexMotorBottom.set(ControlMode.PercentOutput, 1.0);
  }

  public void stopMotors() {
    indexMotorTop.set(ControlMode.PercentOutput, 0.0);
    indexMotorBottom.set(ControlMode.PercentOutput, 0.0);
  }

  @Override
  public void periodic() {
  }
}
