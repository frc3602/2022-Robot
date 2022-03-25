// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ClimberSetLocationCoordiatedCommandGroup extends ParallelCommandGroup {
  /** Creates a new ClimberSetLocationCoordiatedCommandGroup. */
  public ClimberSetLocationCoordiatedCommandGroup(double innerAngle, double outerAngle, double innderLength, double outerLength)
  {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new PivotAngleCommand(true, innerAngle),
      new PivotAngleCommand(false, outerAngle),
      new ExtendDistanceCommand(true, innderLength),
      new ExtendDistanceCommand(false, outerLength)
    );
  }
}
