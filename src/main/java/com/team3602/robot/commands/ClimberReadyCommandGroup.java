// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class ClimberReadyCommandGroup extends ParallelCommandGroup {
  /** Creates a new ClimberReadyCommand. */
  public ClimberReadyCommandGroup()
  {
    addCommands(
      new PivotAngleCommand(true, -27.0),
      new PivotAngleCommand(false, 0.0),
      new ExtendDistanceCommand(true, 0.0),
      new ExtendDistanceCommand(false, 19.0));
  }

}
