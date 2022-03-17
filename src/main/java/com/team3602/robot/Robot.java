/*
 * @(#)Robot.java        1.0 22/03/04
 *
 * Copyright (c) 2022 Team 3602
 * 500 S Lincoln Rd, Escanaba, Michigan, 49829, U.S.A.
 * All rights reserved.
 *
 * This software is licensed under the terms of the GPLv3 license
 * found in the root directory of this project.
 */

package com.team3602.robot;

import com.team3602.robot.subsystems.ClimberSubsystem;

// WPILib Imports
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The Robot class provides the RobotContainer.
 *
 * @version 1.0 04 Mar 2022
 * @author Cody Wellman
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  // Creates a robot container to contain all of the subsystems and commands
  private RobotContainer robotContainer;

  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();
    //robotContainer.Init();

    // Sets the default command to the drive command
    RobotContainer.driveSubsystem.setDefaultCommand(RobotContainer.driveCommand);
    //RobotContainer.climberSubsystem.setDefaultCommand(RobotContainer.reportCommand);
    RobotContainer.climberSubsystem.ResetEncoders();
    RobotContainer.climberSubsystem.InitPositions();
    RobotContainer.climberSubsystem.setDefaultCommand(RobotContainer.climberControl);

    RobotContainer.shooterSubsystem.InitShooter();
   // RobotContainer.shooterSubsystem.setDefaultCommand(RobotContainer.calculateShooterSpeedCommand);

   RobotContainer.visionSubsystem.init();

   robotContainer.ClearStickeyFaults();
 }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit()
  {

    RobotContainer.climberSubsystem.BrakeAllTheMotors();

  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();

    }
    RobotContainer.climberSubsystem.ResetTheClimb();

    robotContainer.ClearStickeyFaults();

  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }
}
