// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.commands;

import com.team3602.robot.RobotContainer;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonReverseAndShootCommandGroup extends SequentialCommandGroup {
  /** Creates a new AutonReverseAndShootCommandGroup. */
  public AutonReverseAndShootCommandGroup() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new SequentialCommandGroup(
      new AutonDriveCommand(-30.0).withTimeout(1.5)
      ,
      new ShootStuffCommand(RobotContainer.shooterSubsystem).withTimeout(5.0)
      ,
      new AutonDriveCommand(-30.0).withTimeout(1.5)
      )

    );
  }
}
