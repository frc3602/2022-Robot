/*
 * @(#)ClimberControl.java        1.0 22/03/04
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
import com.team3602.robot.subsystems.ClimberSubsystem;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * The ClimberControl class provides the execution of the climber.
 *
 * @version 1.0 04 Mar 2022
 * @author Cody Wellman
 */
public class ClimberControlCommand extends CommandBase {
  /**
   * Constructor for {@link ClimberControlCommand} class to set subsystem requirements.
   */
  public ClimberControlCommand(ClimberSubsystem subsystem) {
    addRequirements(RobotContainer.climberSubsystem);
  }

  @Override
  public void initialize() {
    System.out.println("ClimberControlCommand init");

  }

  @Override
  public void execute()
  {

    if(!RobotContainer.climberSubsystem.ClimberActive())
    {
      return;
    }
    // public enum Button {
    //     kLeftBumper(5),
    //     kRightBumper(6),
    //     kLeftStick(9),
    //     kRightStick(10),
    //     kA(1),
    //     kB(2),
    //     kX(3),
    //     kY(4),
    //     kBack(7),
    //     kStart(8);

    if(OI.xboxController.getRawButtonPressed(6)) // right bumper on xbox controller
    {
      System.out.println("ClimberControlCommand rightBumper Pressed");
      //RobotContainer.climberSubsystem.NextStageClimb();

    }
    else if(OI.xboxController.getRawButtonPressed(5)) // left bumper on xbox controller
    {
      System.out.println("ClimberControlCommand leftBumper Pressed");
      // RobotContainer.climberSubsystem.PrevStageClimb();
    }
    else if(OI.xboxController.getRawButtonPressed(2)) // B button on xbox controller
    {
      System.out.println("ClimberControlCommand B button Pressed");
      RobotContainer.climberSubsystem.NextStageClimb();
    }
    else if(OI.xboxController.getRawButtonPressed(3)) // X button on xbox controller
    {
      System.out.println("ClimberControlCommand X button Pressed");
      RobotContainer.climberSubsystem.RetryStageClimb();
    }

  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("ClimberControlCommand end");

  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
