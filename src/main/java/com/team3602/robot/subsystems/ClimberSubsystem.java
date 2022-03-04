/*
 * @(#)ClimberSubsystem.java        1.0 22/03/04
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
import com.team3602.robot.Constants.Climber;
import com.team3602.robot.Constants.Controller;

// Phoenix Imports
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The ClimberSubsystem class provides all methods and functionality of the
 * robot's climber.
 *
 * @version 1.0 04 Mar 2022
 * @author Cody Wellman
 */
public class ClimberSubsystem extends SubsystemBase {
  // Creates the motors & controllers and sets the CAN IDs for each one
  WPI_TalonFX supportOneLeft = new WPI_TalonFX(Climber.climbSupportOneLeftCANID);
  WPI_TalonFX armOneLeft = new WPI_TalonFX(Climber.climbArmOneLeftCANID);
  WPI_TalonFX supportOneRight = new WPI_TalonFX(Climber.climbSupportOneRightCANID);
  WPI_TalonFX armOneRight = new WPI_TalonFX(Climber.climbArmOneRightCANID);

  WPI_TalonFX supportTwoLeft = new WPI_TalonFX(Climber.climbSupportTwoLeftCANID);
  WPI_TalonFX armTwoLeft = new WPI_TalonFX(Climber.climbArmTwoLeftCANID);
  WPI_TalonFX supportTwoRight = new WPI_TalonFX(Climber.climbSupportTwoRightCANID);
  WPI_TalonFX armTwoRight = new WPI_TalonFX(Climber.climbArmTwoRightCANID);

  /**
   * Constructor for {@link ClimberSubsystem} class to run the
   * {@link #configureMotors()} method.
   */
  public ClimberSubsystem() {
    configureMotors();
  }

  public void climberOneExtend() {
    armOneLeft.set(ControlMode.PercentOutput, -Controller.leftStickX);
    armOneRight.set(ControlMode.PercentOutput, -Controller.leftStickX);
  }

  public void climberOneRetract() {
    armOneLeft.set(ControlMode.PercentOutput, Controller.leftStickX);
    armOneRight.set(ControlMode.PercentOutput, Controller.leftStickX);
  }

  public void climberTwoExtend() {
    armTwoLeft.set(ControlMode.PercentOutput, -Controller.rightStickX);
    armTwoRight.set(ControlMode.PercentOutput, -Controller.rightStickX);
  }

  public void climberTwoRetract() {
    armTwoLeft.set(ControlMode.PercentOutput, Controller.rightStickX);
    armTwoRight.set(ControlMode.PercentOutput, Controller.rightStickX);
  }

  public void climberOneForwards() {
    supportOneLeft.set(ControlMode.PercentOutput, -Controller.leftStickY);
    supportOneRight.set(ControlMode.PercentOutput, -Controller.leftStickY);
  }

  public void climberOneBackwards() {
    supportOneLeft.set(ControlMode.PercentOutput, Controller.leftStickY);
    supportOneRight.set(ControlMode.PercentOutput, Controller.leftStickY);
  }

  public void climberTwoForwards() {
    supportTwoLeft.set(ControlMode.PercentOutput, -Controller.rightStickY);
    supportTwoRight.set(ControlMode.PercentOutput, -Controller.rightStickY);
  }

  public void climberTwoBackwards() {
    supportTwoLeft.set(ControlMode.PercentOutput, Controller.rightStickY);
    supportTwoRight.set(ControlMode.PercentOutput, Controller.rightStickY);
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(RobotContainer.climberControl);
  }

  /**
   * Method to set the climber motors to factory defaults and brake mode.
   */
  private void configureMotors() {
    supportOneLeft.setNeutralMode(NeutralMode.Brake);
    armOneLeft.setNeutralMode(NeutralMode.Brake);
    supportOneRight.setNeutralMode(NeutralMode.Brake);
    armOneRight.setNeutralMode(NeutralMode.Brake);

    supportTwoLeft.setNeutralMode(NeutralMode.Brake);
    armTwoLeft.setNeutralMode(NeutralMode.Brake);
    supportTwoRight.setNeutralMode(NeutralMode.Brake);
    armTwoRight.setNeutralMode(NeutralMode.Brake);
  }
}
