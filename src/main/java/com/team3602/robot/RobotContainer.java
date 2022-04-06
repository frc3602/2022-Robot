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
import com.team3602.robot.commands.Autonomous.Auton2BallCommand;
import com.team3602.robot.commands.Autonomous.Auton3BallCommand;
import com.team3602.robot.commands.Autonomous.AutonGrabAndTurnCommandGroup;
import com.team3602.robot.commands.Autonomous.AutonReverseAndShootCommandGroup;
import com.team3602.robot.commands.Autonomous.FindCargoCommand;
import com.team3602.robot.commands.Autonomous.SimpleTurnToAngleCommand;
import com.team3602.robot.commands.Climber.ClimberControlCommand;
import com.team3602.robot.commands.Climber.StageClimbReadyCommandGroup;
import com.team3602.robot.commands.Climber.StageClimbResetCommandGroup;
import com.team3602.robot.commands.Index.IndexInCommand;
import com.team3602.robot.commands.Index.IndexStopCommand;
import com.team3602.robot.commands.Obsolete.TestIntakeCommand;
import com.team3602.robot.commands.Shooter.ShootStuffCommand;
import com.team3602.robot.commands.Shooter.SlamDunkCommand;
import com.team3602.robot.subsystems.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  public static LEDSubsystem ledSubsystem = new LEDSubsystem();
  public static PixySubsystem pixySubsystem = new PixySubsystem();
  public static PixyRotatePIDSubsystem pixyRotatePIDSubsystem = new PixyRotatePIDSubsystem();
  public static DriveSpeedPIDSubsystem driveSpeedPIDSubsystem = new DriveSpeedPIDSubsystem();

  // Commands
  public static DriveCommand driveCommand = new DriveCommand(driveSubsystem);
  public static ClimberControlCommand climberControl = new ClimberControlCommand(climberSubsystem);
  public static IndexStopCommand indexStop = new IndexStopCommand(indexSubsystem);
  public static ShootStuffCommand shootStuff = new ShootStuffCommand(shooterSubsystem);

  public static StageClimbReadyCommandGroup climbReadyCommand = new StageClimbReadyCommandGroup();
  public static StageClimbResetCommandGroup climbResetCommand = new StageClimbResetCommandGroup();


  public static AutonGrabAndTurnCommandGroup autonGrabAndTurnCommandGroup = new AutonGrabAndTurnCommandGroup();
  public static AutonReverseAndShootCommandGroup autonReverseAndShootCommandGroup = new AutonReverseAndShootCommandGroup();
  public static Auton3BallCommand auton3BallCommand = new Auton3BallCommand();
  public static Auton2BallCommand auton2BallCommand = new Auton2BallCommand();
  public SendableChooser<Command> autoChooser = new SendableChooser<>();

  // Operator interfaces
  public static OI oi;

  /**
   * PID Gains may have to be adjusted based on the responsiveness of control loop.
   * kF: 1023 represents output value to Talon at 100%, 7200 represents Velocity units at 100% output
   * 
   * 	                                    			          kP   kI   kD   kF              Iz    PeakOut */
  public final static Gains kGains_Velocit = new Gains( 0.25, 0.001, 20, 1023.0/7200.0,  300,  1.00);
  //public final static Gains kGains_Velocit = new Gains( 0.30, 0.0, 0, 1023.0/7200.0,  0,  1.00);

  /**
   * Constructor for {@link RobotContainer} class to configure the button bindings.
   */
  public RobotContainer() {
    configureButtonBindings();

    autoChooser.setDefaultOption("3 ball", auton3BallCommand);
    autoChooser.addOption("2 ball", auton2BallCommand);

    if(!DriverStation.isFMSAttached())
    {
      autoChooser.addOption("testAuto 90", new SimpleTurnToAngleCommand(90));
      autoChooser.addOption("testAuto 45", new SimpleTurnToAngleCommand(45));
      autoChooser.addOption("testAuto -45", new SimpleTurnToAngleCommand(-45));
      autoChooser.addOption("testAuto -90", new SimpleTurnToAngleCommand(-90));
      autoChooser.addOption("testAuto 180", new SimpleTurnToAngleCommand(180));
    }
    
    // Put the chooser on the dashboard
    SmartDashboard.putData(autoChooser);    
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

    OI.indexInButton.whileHeld(new IndexInCommand(1.0));
    OI.indexOutButton.whileHeld(new TestIntakeCommand(-1.0));

    // Shooter commands & buttons
    //OI.shooterButton.whileHeld(new TestShooterSpeedCommand());
    OI.shooterButton.whileHeld(new ShootStuffCommand(shooterSubsystem));

    OI.dunkButton.whenHeld(new SlamDunkCommand());

    
    OI.startButton.whenPressed(climbResetCommand);
    OI.menuButton.whenPressed(climbReadyCommand);

    OI.button11.whileHeld(new DriveWithPixyCommand());
    OI.button12.whileHeld(new FindCargoCommand());

}

  /**
   * Method to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
    {
      return autoChooser.getSelected();
    }
}
