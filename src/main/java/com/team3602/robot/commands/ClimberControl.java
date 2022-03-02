/* Copyright (C) 2022 Team 3602. All rights reserved. */
/* This work is licensed under the terms of the MIT license */
/* found in the root directory of this project. */

package com.team3602.robot.commands;

import com.team3602.robot.RobotContainer;
import com.team3602.robot.subsystems.ClimberSubsystem;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimberControl extends CommandBase {

  public ClimberControl(ClimberSubsystem subsystem) {
    addRequirements(RobotContainer.climberSubsystem);
  }

  @Override
  public void initialize() {
    RobotContainer.climberSubsystem.climberOneExtend();
    RobotContainer.climberSubsystem.climberOneRetract();
    RobotContainer.climberSubsystem.climberTwoExtend();
    RobotContainer.climberSubsystem.climberTwoRetract();

    RobotContainer.climberSubsystem.climberOneForwards();
    RobotContainer.climberSubsystem.climberOneBackwards();
    RobotContainer.climberSubsystem.climberTwoForwards();
    RobotContainer.climberSubsystem.climberTwoBackwards();
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
