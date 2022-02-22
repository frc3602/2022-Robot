/* Copyright (C) 2022 Team 3602. All rights reserved. */
/* This work is licensed under the terms of the MIT license */
/* found in the root directory of this project. */

package com.frc3602.robot.commands;

import com.frc3602.robot.RobotContainer;
import com.frc3602.robot.subsystems.DriveSubsystem;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveOrientation extends CommandBase {

  public DriveOrientation(DriveSubsystem subsystem) {
    addRequirements(RobotContainer.driveSubsystem);
  }

  @Override
  public void initialize() {
    // RobotContainer.driveSubsystem.ToggleOrientation();
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
