/*
 * @(#)IndexIn.java        1.0 22/03/04
 *
 * Copyright (c) 2022 Team 3602
 * 500 S Lincoln Rd, Escanaba, Michigan, 49829, U.S.A.
 * All rights reserved.
 *
 * This software is licensed under the terms of the GPLv3 license
 * found in the root directory of this project.
 */

package com.team3602.robot.commands.Index;

import com.team3602.robot.RobotContainer;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * The IndexIn class provides the execution of the indexer / magazine.
 *
 * @version 1.0 04 Mar 2022
 * @author Cody Wellman
 */
public class IndexInCommand extends CommandBase {
  double speed = 0.0;
  /**
   * Constructor for {@link IndexInCommand} class to set subsystem requirements.
   */
  public IndexInCommand(double speed) {
    addRequirements(RobotContainer.indexSubsystem);
    this.speed = speed;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    RobotContainer.indexSubsystem.indexIn(speed);
  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.indexSubsystem.stopMotors();    
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
