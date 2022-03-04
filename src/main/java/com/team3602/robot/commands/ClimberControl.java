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
public class ClimberControl extends CommandBase {
  /**
   * Constructor for {@link ClimberControl} class to set subsystem requirements.
   */
  public ClimberControl(ClimberSubsystem subsystem) {
    addRequirements(RobotContainer.climberSubsystem);
  }

  @Override
  public void initialize() {
    RobotContainer.climberSubsystem.climberOneExtend();
    RobotContainer.climberSubsystem.climberOneRetract();
    RobotContainer.climberSubsystem.climberTwoExtend();
    RobotContainer.climberSubsystem.climberTwoRetract();

    RobotContainer.climberSubsystem.climberOneForwards();
    RobotContainer.climberSubsystem.climberOneBackwards();
    RobotContainer.climberSubsystem.climberTwoForwards();
    RobotContainer.climberSubsystem.climberTwoBackwards();
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
