/*
 * @(#)IndexOut.java        1.0 22/03/04
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
import com.team3602.robot.subsystems.IndexSubsystem;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * The IndexOut class provides the revered execution of the indexer / magazine.
 *
 * @version 1.0 04 Mar 2022
 * @author Cody Wellman
 */
public class IndexOutCommand extends CommandBase {
  /**
   * Constructor for {@link IndexOutCommand} class to set subsystem requirements.
   */
  public IndexOutCommand(IndexSubsystem subsystem) {
    addRequirements(RobotContainer.indexSubsystem);
  }

  @Override
  public void initialize()
  {
  }

  @Override
  public void execute()
  {
    RobotContainer.indexSubsystem.indexOut();
  }

  @Override
  public void end(boolean interrupted)
  {
    RobotContainer.shooterSubsystem.stopMotor();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
