/* Copyright (C) 2022 Team 3602. All rights reserved. */
/* This work is licensed under the terms of the MIT license */
/* found in the root directory of this project. */

package com.team3602.robot.subsystems;

import com.team3602.robot.RobotContainer;
import com.team3602.robot.Constants.Climber;
import com.team3602.robot.Constants.Controller;

// Phoenix Imports
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

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

  public ClimberSubsystem() {
    // Sets the motors to brake mode
    supportOneLeft.setNeutralMode(NeutralMode.Brake);
    armOneLeft.setNeutralMode(NeutralMode.Brake);
    supportOneRight.setNeutralMode(NeutralMode.Brake);
    armOneRight.setNeutralMode(NeutralMode.Brake);

    supportTwoLeft.setNeutralMode(NeutralMode.Brake);
    armTwoLeft.setNeutralMode(NeutralMode.Brake);
    supportTwoRight.setNeutralMode(NeutralMode.Brake);
    armTwoRight.setNeutralMode(NeutralMode.Brake);
  }

  public void climberOneExtend() {
    armOneLeft.set(-Controller.leftStickX);
    armOneRight.set(-Controller.leftStickX);
  }

  public void climberOneRetract() {
    armOneLeft.set(Controller.leftStickX);
    armOneRight.set(Controller.leftStickX);
  }

  public void climberTwoExtend() {
    armTwoLeft.set(-Controller.rightStickX);
    armTwoRight.set(-Controller.rightStickX);
  }

  public void climberTwoRetract() {
    armTwoLeft.set(Controller.rightStickX);
    armTwoRight.set(Controller.rightStickX);
  }

  public void climberOneForwards() {
    supportOneLeft.set(-Controller.leftStickY);
    supportOneRight.set(-Controller.leftStickY);
  }

  public void climberOneBackwards() {
    supportOneLeft.set(Controller.leftStickY);
    supportOneRight.set(Controller.leftStickY);
  }

  public void climberTwoForwards() {
    supportTwoLeft.set(-Controller.rightStickY);
    supportTwoRight.set(-Controller.rightStickY);
  }

  public void climberTwoBackwards() {
    supportTwoLeft.set(Controller.rightStickY);
    supportTwoRight.set(Controller.rightStickY);
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(RobotContainer.climberControl);
  }

  @Override
  public void periodic() {
  }
}