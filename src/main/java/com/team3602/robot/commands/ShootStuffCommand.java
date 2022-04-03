/*
 * @(#)ShootStuff.java        1.0 22/03/04
 *
 * Copyright (c) 2022 Team 3602
 * 500 S Lincoln Rd, Escanaba, Michigan, 49829, U.S.A.
 * All rights reserved.
 *
 * This software is licensed under the terms of the GPLv3 license
 * found in the root directory of this project.
 */

package com.team3602.robot.commands;

import com.team3602.robot.RobotContainer;
import com.team3602.robot.subsystems.ShooterSubsystem;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * The ShootStuff class provides the execution of the shooter.
 *
 * @version 1.0 04 Mar 2022
 * @author Cody Wellman
 */
public class ShootStuffCommand extends CommandBase
{
  boolean upperBall = false;
  boolean lowerBall = false;
  int bothCount = 0;

  /**
   * Constructor for {@link ShootStuffCommand} class to set subsystem requirements.
   */
  public ShootStuffCommand(ShooterSubsystem subsystem) {
    addRequirements(RobotContainer.shooterSubsystem);
  }

  @Override
  public void initialize()
  {
    System.out.println("ShootStuffCommand init");
    RobotContainer.visionSubsystem.lightOn();
    RobotContainer.rotateToTargetSubsystem.enable();
  }

  @Override
  public void execute() {

    RobotContainer.shooterSubsystem.calculateAndSetMotorSpeeds();
    RobotContainer.shooterSubsystem.updateShooterMotorSpeed();
    RobotContainer.visionSubsystem.logDataToSmartDashboard();

    
    if(RobotContainer.visionSubsystem.validTarget() &&
      RobotContainer.rotateToTargetSubsystem.IsRunning() &&
      RobotContainer.rotateToTargetSubsystem.onTarget() &&
      RobotContainer.shooterSubsystem.IsShooterSpeedOnTarget())
      {
        RobotContainer.indexSubsystem.shoot();
        RobotContainer.visionSubsystem.takeSnapshot();
      }
    else
      RobotContainer.indexSubsystem.stopMotors();

      lowerBall = RobotContainer.indexSubsystem.indexSensorBottom();
      upperBall = RobotContainer.indexSubsystem.indexSensorTop();
  
      if(!upperBall && !lowerBall)
      {
        bothCount++;
      }
      else
      {
        bothCount = 0;
      }
  

  }

  @Override
  public void end(boolean interrupted)
  {
    System.out.println("ShootStuffCommand end");

    RobotContainer.visionSubsystem.lightOff();
    RobotContainer.rotateToTargetSubsystem.disable();
    RobotContainer.shooterSubsystem.setShooterMotorRPM(0.0);
    RobotContainer.shooterSubsystem.updateShooterMotorSpeed();
    //RobotContainer.shooterSubsystem.stopMotor();
    RobotContainer.indexSubsystem.stopMotors();
    RobotContainer.visionSubsystem.resetSnapshot();

  }

  @Override
  public boolean isFinished() {
    return bothCount > 20;
  }
}
