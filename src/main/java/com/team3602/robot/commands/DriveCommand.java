/*
 * @(#)DriveCommand.java        1.0 22/03/04
 *
 * Copyright (c) 2022 Team 3602
 * 500 S Lincoln Rd, Escanaba, Michigan, 49829, U.S.A.
 * All rights reserved.
 *
 * This software is licensed under the terms of the GPLv3 license
 * found in the root directory of this project.
 */

package com.team3602.robot.commands;

import com.team3602.robot.OI;
import com.team3602.robot.RobotContainer;
import com.team3602.robot.subsystems.DriveSubsystem;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// WPILib Imports
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * The DriveCommand class provides the execution of the drivetrain.
 *
 * @version 1.0 04 Mar 2022
 * @author Cody Wellman
 */
public class DriveCommand extends CommandBase {

  private final SlewRateLimiter xspeedLimiter = new SlewRateLimiter(3);
  private final SlewRateLimiter yspeedLimiter = new SlewRateLimiter(3);
  private final SlewRateLimiter rotLimiter = new SlewRateLimiter(3);  

  /**
   * Constructor for {@link DriveCommand} class to set subsystem requirements.
   */
  public DriveCommand(DriveSubsystem subsystem) {
    addRequirements(RobotContainer.driveSubsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {

    double speed = OI.joystick.getRawAxis(1) * -1.0;
    double turn = OI.joystick.getRawAxis(0);
    double rotate = OI.joystick.getRawAxis(2);

    if(!DriverStation.isFMSAttached())
    {
    SmartDashboard.putNumber("Drive Raw Speed", speed);
    SmartDashboard.putNumber("Drive Raw turn", turn);
    SmartDashboard.putNumber("Drive Raw rotate", rotate);

    }



    double rawThrottle = OI.joystick.getRawAxis(3);

    double throttle = ((1.0 - rawThrottle)  / 4.0) + 0.5;

    if(!DriverStation.isFMSAttached())
    {
      SmartDashboard.putNumber("Twist Trottle", throttle);
    }

    if(Math.abs(rotate) <= 0.2)
      rotate = 0.0;

    rotate *= throttle;

    speed = xspeedLimiter.calculate(speed);

    turn = yspeedLimiter.calculate(turn);

    rotate = rotLimiter.calculate(rotate);

    if(!RobotContainer.rotateToTargetSubsystem.IsRunning())
    {
      if(RobotContainer.pixyRotatePIDSubsystem.isEnabled())
      {
        rotate = RobotContainer.pixyRotatePIDSubsystem.GetOutputValue();
      }
      // if(RobotContainer.driveSpeedPIDSubsystem.isEnabled())
      // {
      //   speed = RobotContainer.driveSpeedPIDSubsystem.GetOutputValue();
      // }


      if(!DriverStation.isFMSAttached())
      {
      SmartDashboard.putNumber("Drive adj Speed", speed);
      SmartDashboard.putNumber("Drive adj turn", turn);
      SmartDashboard.putNumber("Drive adj rotate", rotate);
      }

    RobotContainer.driveSubsystem.driveCartesian(speed, turn, rotate);
    }

    RobotContainer.climberSubsystem.ReoportStuff();
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
