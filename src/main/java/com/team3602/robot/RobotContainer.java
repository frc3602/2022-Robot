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

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
// WPILib Imports
import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

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
  public static RotateToTargetSubsystem rotateToTargetSubsystem = new RotateToTargetSubsystem();
  public static AutonRotatePIDSubsystem autonRotatePIDSubsystem = new AutonRotatePIDSubsystem();

  // Commands
  public static DriveCommandCommand driveCommand = new DriveCommandCommand(driveSubsystem);
  public static ClimberControlCommand climberControl = new ClimberControlCommand(climberSubsystem);
  // public static IndexInCommand indexIn = new IndexInCommand(indexSubsystem);
  // public static IndexOutCommand indexOut = new IndexOutCommand(indexSubsystem);
  public static IndexStopCommand indexStop = new IndexStopCommand(indexSubsystem);
  public static ShootStuffCommand shootStuff = new ShootStuffCommand(shooterSubsystem);
  public static ShootStopCommand shootStop = new ShootStopCommand(shooterSubsystem);
  public static CalculateShooterSpeedCommand calculateShooterSpeedCommand = new CalculateShooterSpeedCommand();

  public static ReportStuffCommand reportCommand = new ReportStuffCommand();
  public static SetClimbReadyCommand climbReadyCommand = new SetClimbReadyCommand();
  public static AutonGrabAndTurnCommandGroup autonGrabAndTurnCommandGroup = new AutonGrabAndTurnCommandGroup();
  public static AutonReverseAndShootCommandGroup autonReverseAndShootCommandGroup = new AutonReverseAndShootCommandGroup();

 // PowerDistribution powerHub = new PowerDistribution(Constants.powerDistributionHubCANID, ModuleType.kRev);

  // Operator interfaces
  public static OI oi;

  /**
   * PID Gains may have to be adjusted based on the responsiveness of control loop.
   * kF: 1023 represents output value to Talon at 100%, 7200 represents Velocity units at 100% output
   * 
   * 	                                    			  kP   kI   kD   kF          Iz    PeakOut */
  public final static Gains kGains_Velocit = new Gains( 0.25, 0.001, 20, 1023.0/7200.0,  300,  1.00);
  // public final static Gains kGains_Velocit = new Gains( 0.30, 0.0, 0, 1023.0/7200.0,  0,  1.00);

  /**
   * Constructor for {@link RobotContainer} class to configure the button bindings.
   */
  public RobotContainer() {
    configureButtonBindings();
  }

  public void ClearStickeyFaults()
  {
    //reportCommand.schedule();

   // powerHub.clearStickyFaults();
  }

  /**
   * Method to define button -> command mappings.
   */
  private void configureButtonBindings() {
    // Index commands & buttons
    // OI.indexInButton.whileHeld(new IndexInCommand(indexSubsystem));
    // OI.indexOutButton.whenPressed(new IndexOutCommand(indexSubsystem));
    //  OI.indexInButton.whenReleased(RobotContainer.indexStop);
    // OI.indexOutButton.whenReleased(RobotContainer.indexStop);

    OI.indexInButton.whileHeld(new IndexInCommand(indexSubsystem));
    OI.indexOutButton.whileHeld(new TestIntakeCommand(-1.0));

    // Shooter commands & buttons
    //OI.shooterButton.whileHeld(new TestShooterSpeedCommand());
    OI.shooterButton.whileHeld(new ShootStuffCommand(shooterSubsystem));

    OI.dunkButton.whenHeld(new SlamDunkCommand());
    //OI.shooterButton.whenReleased(RobotContainer.shootStop);

    // OI.xButton.whenPressed(new ExtendDistanceCommand(true, 0.0));
    // OI.yButton.whenPressed(new ExtendDistanceCommand(true, 22.5));
    // OI.aButton.whenPressed(new ExtendDistanceCommand(false, 0.0));
    // OI.bButton.whenPressed(new ExtendDistanceCommand(false, 15.0));

    // OI.xButton.whenPressed(new PivotAngleCommand(true, 5));
    // OI.yButton.whenPressed(new PivotAngleCommand(true, -5.0));
    // OI.aButton.whenPressed(new PivotAngleCommand(false, 10.0));
    // OI.bButton.whenPressed(new PivotAngleCommand(false, -10.0));

    // OI.leftBumperButton.whenPressed(new PivotScissorCommandGroup(15.0));
    // OI.rightBumperButton.whenPressed(new PivotScissorCommandGroup(-15.0));

    // OI.startButton.whenPressed(
    //   new ParallelCommandGroup(
    //     new PivotAngleCommand(true, 0.0),
    //     new PivotAngleCommand(false, 0.0),
    //     new ExtendDistanceCommand(true, 0.0),
    //     new ExtendDistanceCommand(false, 0.0)
    //   ));
      
    OI.menuButton.whenPressed(climbReadyCommand);

}

  /**
   * Method to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
  {
    return autonReverseAndShootCommandGroup;
    //return driveCommand;
  }
}
