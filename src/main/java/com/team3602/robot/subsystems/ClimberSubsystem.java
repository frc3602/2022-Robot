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
  WPI_TalonFX supportInnerLeft = new WPI_TalonFX(Climber.climbSupportInnerLeftCANID);
  WPI_TalonFX armInnerLeft = new WPI_TalonFX(Climber.climbArmInnerLeftCANID);
  WPI_TalonFX supportInnerRight = new WPI_TalonFX(Climber.climbSupportInnerRightCANID);
  WPI_TalonFX armInnerRight = new WPI_TalonFX(Climber.climbArmInnerRightCANID);

  WPI_TalonFX supportOuterLeft = new WPI_TalonFX(Climber.climbSupportOuterLeftCANID);
  WPI_TalonFX armOuterLeft = new WPI_TalonFX(Climber.climbArmOuterLeftCANID);
  WPI_TalonFX supportOuterRight = new WPI_TalonFX(Climber.climbSupportOuterRightCANID);
  WPI_TalonFX armOuterRight = new WPI_TalonFX(Climber.climbArmOuterRightCANID);

  /**
   * Constructor for {@link ClimberSubsystem} class to run the
   * {@link #configureMotors()} method.
   */
  public ClimberSubsystem() {
    configureMotors();
  }

  public void climberOneArm(double speed) {
    armInnerLeft.set(ControlMode.PercentOutput, speed);
    armInnerRight.set(ControlMode.PercentOutput, speed);
  }

  public void climberTwoArm(double speed) {
    armOuterLeft.set(ControlMode.PercentOutput, speed);
    armOuterRight.set(ControlMode.PercentOutput, speed);
  }

  public void climberOneSupport(double speed) {
    supportInnerLeft.set(ControlMode.PercentOutput, speed);
    supportInnerRight.set(ControlMode.PercentOutput, speed);
  }

  public void climberTwoSupport(double speed) {
    supportOuterLeft.set(ControlMode.PercentOutput, speed);
    supportOuterRight.set(ControlMode.PercentOutput, speed);
  }


  /**
   * Method to set the default command for the {@link ClimberSubsystem}.
   */
  // public void initDefaultCommand() {
  //   setDefaultCommand(RobotContainer.climberControl);
  // }

  /**
   * Method to set the climber motors to factory defaults and brake mode.
   */
  private void configureMotors() {
    supportInnerLeft.setNeutralMode(NeutralMode.Brake);
    armInnerLeft.setNeutralMode(NeutralMode.Brake);
    supportInnerRight.setNeutralMode(NeutralMode.Brake);
    armInnerRight.setNeutralMode(NeutralMode.Brake);

    supportOuterLeft.setNeutralMode(NeutralMode.Brake);
    armOuterLeft.setNeutralMode(NeutralMode.Brake);
    supportOuterRight.setNeutralMode(NeutralMode.Brake);
    armOuterRight.setNeutralMode(NeutralMode.Brake);

    supportOuterRight.setInverted(true);
    supportInnerRight.setInverted(true);
  }
}
