/* Copyright (C) 2022 Team 3602. All rights reserved. */
/* This work is licensed under the terms of the MIT license */
/* found in the root directory of this project. */

package com.team3602.robot.commands;

import com.team3602.robot.RobotContainer;
import com.team3602.robot.subsystems.ShooterSubsystem;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShootStuff extends CommandBase {

  public ShootStuff(ShooterSubsystem subsystem) {
    addRequirements(RobotContainer.shooterSubsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    RobotContainer.shooterSubsystem.shootStuff();
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}