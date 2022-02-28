/* Copyright (C) 2022 Team 3602. All rights reserved. */
/* This work is licensed under the terms of the MIT license */
/* found in the root directory of this project. */

package com.team3602.robot;

import com.team3602.robot.commands.*;
import com.team3602.robot.subsystems.*;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
  // Subsystems
  public static DriveSubsystem driveSubsystem = new DriveSubsystem();
  public static ClimberSubsystem climberSubsystem = new ClimberSubsystem();
  public static ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  public static LimelightSubsystem visionSubsystem = new LimelightSubsystem();
  public static IndexSubsystem indexSubsystem = new IndexSubsystem();

  // Commands
  public static DriveOrientation driveOrientation = new DriveOrientation(driveSubsystem);
  public static DriveCommand driveCommand = new DriveCommand(driveSubsystem);
  public static ClimbOneExtend climbOneExtend = new ClimbOneExtend(climberSubsystem);
  public static ClimbOneRetract climbOneRetract = new ClimbOneRetract(climberSubsystem);
  public static ClimbTwoExtend climbTwoExtend = new ClimbTwoExtend(climberSubsystem);
  public static ClimbTwoRetract climbTwoRetract = new ClimbTwoRetract(climberSubsystem);
  public static ClimbPivot climbPivot = new ClimbPivot(climberSubsystem);
  public static ActivateIntake acticateIntake = new ActivateIntake(indexSubsystem);
  public static IndexIn indexIn = new IndexIn(indexSubsystem);
  public static IndexOut indexOut = new IndexOut(indexSubsystem);
  public static ShootStuff shootStuff = new ShootStuff(shooterSubsystem);

  // Operator interfaces
  public static OI oi;

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
  }

  public Command getAutonomousCommand() {
    return driveCommand;
  }
}
