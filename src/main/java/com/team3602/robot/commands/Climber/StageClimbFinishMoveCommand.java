// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.commands.Climber;

import com.team3602.robot.Constants;
import com.team3602.robot.RobotContainer;
import com.team3602.robot.Constants.Climber.ClimbStageEnum;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class StageClimbFinishMoveCommand extends CommandBase {

  ClimbStageEnum stage = ClimbStageEnum.notReady;

  /** Creates a new StageClimbSetCommand. */
  public StageClimbFinishMoveCommand( Constants.Climber.ClimbStageEnum stage)
  {
    // Use addRequirements() here to declare subsystem dependencies.
    //addRequirements(RobotContainer.climberSubsystem);

    this.stage = stage;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    RobotContainer.climberSubsystem.FinishMove(stage);
    System.out.println("StageClimbFinishMoveCommand stage: " + stage);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
