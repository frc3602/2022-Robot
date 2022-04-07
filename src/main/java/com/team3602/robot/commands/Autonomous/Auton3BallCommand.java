// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.commands.Autonomous;

import com.team3602.robot.RobotContainer;
import com.team3602.robot.commands.Index.IndexInCommand;
import com.team3602.robot.commands.Shooter.ShootStuffCommand;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Auton3BallCommand extends SequentialCommandGroup {
  /** Creates a new Auton3BallCommand. */
  public Auton3BallCommand() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ResetDriveEncodersCommand(),
      new ResetGyroCommand(),
      //new AutonDrivePIDCommand(5.0),
      new ParallelCommandGroup(
        new IndexInCommand(0.25).withTimeout(0.25),
        new AutonDrivePIDCommand(15.0)
      ),
      new FindCargoCommand().withTimeout(5),
      new SimpleTurnToAngleCommand(180.0, false),
      new ResetDriveEncodersCommand(),
      new AutonDrivePIDCommand(-15.0),
      new ShootStuffCommand(RobotContainer.shooterSubsystem).withTimeout(5.0),
      new ResetGyroCommand(),
      new SimpleTurnToAngleCommand(-65, false),
      new ResetDriveEncodersCommand(),
      new AutonDrivePIDCommand(70.0),
      // new ResetGyroCommand(),
      new FindCargoCommand(true).withTimeout(4),
      new SimpleTurnToAngleCommand(75.0, false),
      new ShootStuffCommand(RobotContainer.shooterSubsystem).withTimeout(5.0)
    );
   // withTimeout(18);
  }
}
