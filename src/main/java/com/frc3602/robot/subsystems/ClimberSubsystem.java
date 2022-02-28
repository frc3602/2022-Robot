/* Copyright (C) 2022 Team 3602. All rights reserved. */
/* This work is licensed under the terms of the MIT license */
/* found in the root directory of this project. */

package com.frc3602.robot.subsystems;

import com.frc3602.robot.Constants.Climber;

// Phoenix Imports
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
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

  public void ClimberOneExtend() {
    armOneLeft.set(ControlMode.PercentOutput, -0.25);
    armOneRight.set(ControlMode.PercentOutput, -0.25);
  }

  public void ClimberOneRetract() {
    armOneLeft.set(ControlMode.PercentOutput, 0.25);
    armOneRight.set(ControlMode.PercentOutput, 0.25);
  }

  public void ClimberTwoExtend() {
    armTwoLeft.set(ControlMode.PercentOutput, -0.25);
    armTwoRight.set(ControlMode.PercentOutput, -0.25);
  }

  public void ClimberTwoRetract() {
    armTwoLeft.set(ControlMode.PercentOutput, 0.25);
    armTwoRight.set(ControlMode.PercentOutput, 0.25);
  }

  @Override
  public void periodic() {
  }
}
