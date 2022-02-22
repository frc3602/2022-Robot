/* Copyright (C) 2022 Team 3602. All rights reserved. */
/* This work is licensed under the terms of the MIT license */
/* found in the root directory of this project. */

package com.frc3602.robot.commands;

import com.frc3602.robot.subsystems.ClimberSubsystem;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimbPivot extends CommandBase {
  private final ClimberSubsystem m_climberSubsystem;

  public ClimbPivot(ClimberSubsystem subsystem) {
    m_climberSubsystem = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
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
