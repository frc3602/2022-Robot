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

// WPILib Imports
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * The DriveCommand class provides the execution of the drivetrain.
 *
 * @version 1.0 04 Mar 2022
 * @author Cody Wellman
 */
public class DriveCommandCommand extends CommandBase {
  /**
   * Constructor for {@link DriveCommandCommand} class to set subsystem requirements.
   */
  public DriveCommandCommand(DriveSubsystem subsystem) {
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
    // double rightTrigger = OI.joystick.getRawAxis(3);

    // double rotate = 0.0;

    // if (leftTrigger > 0.0)
    //   rotate = leftTrigger * -1.0;
    // else if (rightTrigger > 0.0)
    //   rotate = rightTrigger;
    // else
    //   rotate = 0.0;

    if(!RobotContainer.rotateToTargetSubsystem.IsRunning())
      RobotContainer.driveSubsystem.driveCartesian(speed, turn, rotate);

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
