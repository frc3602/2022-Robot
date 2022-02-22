/* Copyright (C) 2022 Team 3602. All rights reserved. */
/* This work is licensed under the terms of the MIT license */
/* found in the root directory of this project. */

package com.frc3602.robot.commands;

import com.frc3602.robot.RobotContainer;
import com.frc3602.robot.subsystems.IntakeSubsystem;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ActivateIntake extends CommandBase {

  public ActivateIntake(IntakeSubsystem subsystem) {
    addRequirements(RobotContainer.intakeSubsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    RobotContainer.intakeSubsystem.SpinIntake();
    RobotContainer.intakeSubsystem.ExtendIntake();
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.intakeSubsystem.StopIntake();
    RobotContainer.intakeSubsystem.RetractIntake();
  }
}
