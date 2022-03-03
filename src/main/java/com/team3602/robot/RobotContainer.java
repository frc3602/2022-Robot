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
  public static VisionSubsystem visionSubsystem = new VisionSubsystem();
  public static IndexSubsystem indexSubsystem = new IndexSubsystem();

  // Commands
  public static DriveOrientation driveOrientation = new DriveOrientation(driveSubsystem);
  public static DriveCommand driveCommand = new DriveCommand(driveSubsystem);
  public static ClimberControl climberControl = new ClimberControl(climberSubsystem);
  public static ActivateIntake acticateIntake = new ActivateIntake(indexSubsystem);
  public static IndexIn indexIn = new IndexIn(indexSubsystem);
  public static IndexOut indexOut = new IndexOut(indexSubsystem);
  public static IndexStop indexStop = new IndexStop(indexSubsystem);
  public static ShootStuff shootStuff = new ShootStuff(shooterSubsystem);
  public static ShootStop shootStop = new ShootStop(shooterSubsystem);

  // Operator interfaces
  public static OI oi;

  public RobotContainer() {
    configureButtonBindings();
  }

  // Binds the commands to the buttons and stuff
  private void configureButtonBindings() {
    // Index commands & buttons
    OI.indexInButton.whenPressed(RobotContainer.indexIn);
    OI.indexOutButton.whenPressed(RobotContainer.indexOut);
    OI.indexInButton.whenReleased(RobotContainer.indexStop);
    OI.indexOutButton.whenReleased(RobotContainer.indexStop);

    // Shooter commands & buttons
    OI.shooterButton.whenPressed(RobotContainer.shootStuff);
    OI.shooterButton.whenReleased(RobotContainer.shootStop);
  }

  public Command getAutonomousCommand() {
    return driveCommand;
  }
}
