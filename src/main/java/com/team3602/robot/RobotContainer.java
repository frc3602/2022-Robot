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
import com.team3602.robot.commands.Autonomous.Auton1BallCommand;
import com.team3602.robot.commands.Autonomous.Auton2BallCommand;
import com.team3602.robot.commands.Autonomous.Auton3BallCommand;
import com.team3602.robot.commands.Autonomous.FindCargoCommand;
import com.team3602.robot.commands.Autonomous.SimpleTurnToAngleCommand;
import com.team3602.robot.commands.Climber.ClimberControlCommand;
import com.team3602.robot.commands.Climber.StageClimbReadyCommandGroup;
import com.team3602.robot.commands.Climber.StageClimbResetCommandGroup;
import com.team3602.robot.commands.Index.IndexInCommand;
import com.team3602.robot.commands.Index.IndexStopCommand;
import com.team3602.robot.commands.Obsolete.AutonGrabAndTurnCommandGroup;
import com.team3602.robot.commands.Obsolete.AutonReverseAndShootCommandGroup;
import com.team3602.robot.commands.Obsolete.TestIntakeCommand;
import com.team3602.robot.commands.Shooter.BigDumpCommand;
import com.team3602.robot.commands.Shooter.ShootStuffCommand;
import com.team3602.robot.commands.Shooter.SlamDunkCommand;
import com.team3602.robot.subsystems.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// WPILib Imports
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

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
  public static Auton1BallCommand auton1BallCommand = new Auton1BallCommand();
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
    autoChooser.addOption("1 ball", auton1BallCommand);

    if(!DriverStation.isFMSAttached())
    {
      autoChooser.addOption("testAuto Multiturn",
      new SequentialCommandGroup(
        new SimpleTurnToAngleCommand(45, false),
        new SimpleTurnToAngleCommand(-45, false),
        new SimpleTurnToAngleCommand(90, false),
        new SimpleTurnToAngleCommand(-90, false),
        new SimpleTurnToAngleCommand(180, false)
        )
      );

      autoChooser.addOption("testAuto 90", new SimpleTurnToAngleCommand(90, false));
      autoChooser.addOption("testAuto -90", new SimpleTurnToAngleCommand(-90, false));
      autoChooser.addOption("testAuto 180", new SimpleTurnToAngleCommand(180, false));
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
    // joystick bindings
    
    OI.joybutton1.whileHeld(new IndexInCommand(1.0));

    OI.joyButton2.whileHeld(new ShootStuffCommand(shooterSubsystem));

    OI.joyButton3.whenHeld(new SlamDunkCommand());
    OI.joyButton4.whenHeld(new BigDumpCommand());

    OI.joyButton5.whileHeld(new TestIntakeCommand(-1.0));
    

    OI.joyButton11.whileHeld(new DriveWithPixyCommand());
    OI.joyButton12.whileHeld(new FindCargoCommand());

    // xbox bindings
    OI.startButton.whenPressed(climbResetCommand);
    OI.menuButton.whenPressed(climbReadyCommand);

}

  /**
   * Method to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
    {
      System.out.println("getAutonomousCommand");

      Command aCommand =  autoChooser.getSelected();
      System.out.println("getAutonomousCommand " + aCommand);
      return aCommand;
    }
}
