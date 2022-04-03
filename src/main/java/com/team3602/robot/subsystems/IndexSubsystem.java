/*
 * @(#)IndexSubsystem.java        1.0 22/03/04
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
import com.team3602.robot.Constants.Index;

// Phoenix Imports
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

// WPILib Imports
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  WPI_TalonSRX indexLift = new WPI_TalonSRX(Index.indexLiftCANID);

  DigitalInput indexSensorTop = new DigitalInput(Index.indexSensorTopID);
  DigitalInput indexSensorBottom = new DigitalInput(Index.indexSensorBottomID);

  int ballCount = 0;
  int count = 0;

  boolean previousTopState = false;
  boolean previousBottomState = false;

  /**
   * Constructor for {@link IndexSubsystem} class to run the
   * {@link #configureMotors()} method.
   */
  public IndexSubsystem() {
    configureMotors();
  }

  /**
   * Initializes the sensors
   */
  public void initSensors() {
  }

  /**
   * @return how many balls are in the index / magazine
   */
  public int ballCount() {
    return ballCount;
  }

  /**
   * @return the value of the top index sensor
   */
  public boolean indexSensorTop() {
    return indexSensorTop.get();
  }

  /**
   * @return the value of the bottom index sensor
   */
  public boolean indexSensorBottom() {
    return indexSensorBottom.get();
  }

  /**
   * Method to check if the index is full or not.
   * 
   * @return if the index is full or not
   */
  public boolean isIndexFull() {
    return (ballCount >= Index.maxBalls);
  }

  /**
   * Method to check the sensors.
   */
  public void checkSensors() {
    // count++;
    // if (count > 500) {

      if(Constants.testingEnabled)
      {
      System.out.println("index checkSensors");
      
      SmartDashboard.putBoolean("Limit FWD", (indexLift.isFwdLimitSwitchClosed() != 0 ));
      SmartDashboard.putBoolean("Limit REV", (indexLift.isRevLimitSwitchClosed() != 0 ));

      }


      SmartDashboard.putBoolean("TopSensor", indexSensorTop());
      SmartDashboard.putBoolean("BottomSensor", indexSensorBottom());
    //   count = 0;
    // }
  }

  /**
   * Method to run the magazine / index motors inward.
   */
  public void shoot() {
    indexMotorTop.set(ControlMode.PercentOutput, 1.0);
    indexMotorBottom.set(ControlMode.PercentOutput, -1.0);
  }

  public void indexIn()
  {
    DropIntake();
    checkSensors();

    if(indexSensorTop() && indexSensorBottom())
    {
      stopMotors();
    }
    else if(indexSensorTop())
    {
      indexMotorTop.set(ControlMode.PercentOutput, 0.0);
      indexMotorBottom.set(ControlMode.PercentOutput, -0.5);
    }
    else
    {
      indexMotorTop.set(ControlMode.PercentOutput, 0.8);
      indexMotorBottom.set(ControlMode.PercentOutput, -0.5);
    }


  }

  /**
   * Method to run the magazine / index motors outward.
   */
  public void indexOut() {
    indexMotorTop.set(ControlMode.PercentOutput, -1.0);
    indexMotorBottom.set(ControlMode.PercentOutput, 0.50);
  }

  /**
   * Method to stop the magazine / index motors.
   */
  public void stopMotors() {
    LiftIntake();
    indexMotorTop.set(ControlMode.PercentOutput, 0.0);
    indexMotorBottom.set(ControlMode.PercentOutput, 0.0);
  }

  public void SpinIntakeMotors(double speed)
  {
    indexMotorTop.set(ControlMode.PercentOutput, speed);
    indexMotorBottom.set(ControlMode.PercentOutput, -1.0 * speed);
  }

  public void LiftIntake()
  {
    indexLift.set(ControlMode.PercentOutput, 0.60);
  }

  public void DropIntake()
  {
    indexLift.set(ControlMode.PercentOutput, -0.5);
  }


  /**
   * Method to set the shooter motor to factory defaults and coast mode
   */
  private void configureMotors() {
    indexMotorTop.configFactoryDefault();
    indexMotorBottom.configFactoryDefault();

    indexMotorTop.setNeutralMode(NeutralMode.Brake);
    indexMotorBottom.setNeutralMode(NeutralMode.Brake);


    indexLift.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, Constants.kTimeoutMs);
    indexLift.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, Constants.kTimeoutMs);
  }
}
