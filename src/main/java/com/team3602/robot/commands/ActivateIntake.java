/*
 * @(#)ActivateIntake.java        1.0 22/03/04
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
import com.team3602.robot.subsystems.IndexSubsystem;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * The ActivateIntake class provides the execution of the intake.
 *
 * @version 1.0 04 Mar 2022
 * @author Cody Wellman
 */
public class ActivateIntake extends CommandBase {
  /**
   * Constructor for {@link ActivateIntake} class to set subsystem requirements.
   */
  public ActivateIntake(IndexSubsystem subsystem) {
    addRequirements(RobotContainer.indexSubsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
  }
}
