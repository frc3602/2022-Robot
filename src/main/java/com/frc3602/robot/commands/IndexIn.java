/* Copyright (C) 2022 Team 3602. All rights reserved. */
/* This work is licensed under the terms of the MIT license */
/* found in the root directory of this project. */

package com.frc3602.robot.commands;

import com.frc3602.robot.RobotContainer;
import com.frc3602.robot.subsystems.IndexSubsystem;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IndexIn extends CommandBase {

  public IndexIn(IndexSubsystem subsystem) {
    addRequirements(RobotContainer.indexSubsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    RobotContainer.indexSubsystem.IndexIn();
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
