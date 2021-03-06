// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.commands.Vision;

import com.team3602.robot.RobotContainer;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class PixyCamGetBlocksCommand extends CommandBase {
  /** Creates a new PixyCamGetBlocksCommand. */
  public PixyCamGetBlocksCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    //addRequirements(RobotContainer.pixySubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    RobotContainer.pixySubsystem.GetBlockInfo();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
