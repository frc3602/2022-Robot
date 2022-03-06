/*
 * @(#)RobotContainer.java        1.0 22/03/04
 *
 * Copyright (c) 2022 Team 3602
 * 500 S Lincoln Rd, Escanaba, Michigan, 49829, U.S.A.
 * All rights reserved.
 *
 * This software is licensed under the terms of the GPLv3 license
 * found in the root directory of this project.
 */

package com.team3602.robot;

import com.team3602.robot.commands.*;
import com.team3602.robot.subsystems.*;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.Command;

/**
 * The RobotContainer class provides subsytems and commands.
 *
 * @version 1.0 04 Mar 2022
 * @author Cody Wellman
 */
public class RobotContainer {
  // Subsystems
  public static DriveSubsystem driveSubsystem = new DriveSubsystem();
  public static ClimberSubsystem climberSubsystem = new ClimberSubsystem();
  public static ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  public static VisionSubsystem visionSubsystem = new VisionSubsystem();
  public static IndexSubsystem indexSubsystem = new IndexSubsystem();

  // Commands
  public static DriveCommand driveCommand = new DriveCommand(driveSubsystem);
  public static ClimberControl climberControl = new ClimberControl(climberSubsystem);
  public static ActivateIntake acticateIntake = new ActivateIntake(indexSubsystem);
  public static IndexIn indexIn = new IndexIn(indexSubsystem);
  public static IndexOut indexOut = new IndexOut(indexSubsystem);
  public static IndexStop indexStop = new IndexStop(indexSubsystem);
  public static ShootStuff shootStuff = new ShootStuff(shooterSubsystem);
  public static ShootStop shootStop = new ShootStop(shooterSubsystem);
  public static CalculateShooterSpeedCommand calculateShooterSpeedCommand = new CalculateShooterSpeedCommand();

  // Operator interfaces
  public static OI oi;

  /**
   * Constructor for {@link RobotContainer} class to configure the button bindings.
   */
  public RobotContainer() {
    configureButtonBindings();
  }

  /**
   * Method to define button -> command mappings.
   */
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

  /**
   * Method to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return driveCommand;
  }
}
