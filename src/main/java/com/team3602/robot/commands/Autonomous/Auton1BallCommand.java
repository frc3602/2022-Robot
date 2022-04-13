// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.commands.Autonomous;

import com.team3602.robot.RobotContainer;
import com.team3602.robot.commands.Shooter.ShootStuffCommand;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Auton1BallCommand extends SequentialCommandGroup {
  /** Creates a new Auton1BallCommand. */
  public Auton1BallCommand() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ResetDriveEncodersCommand(),
      new ResetGyroCommand(),
      new AutonDrivePIDCommand(-90.0),
      new ShootStuffCommand(RobotContainer.shooterSubsystem).withTimeout(5.0)
      // new ResetDriveEncodersCommand(),
      // new AutonDrivePIDCommand(-30.0)

    );
  }

}
