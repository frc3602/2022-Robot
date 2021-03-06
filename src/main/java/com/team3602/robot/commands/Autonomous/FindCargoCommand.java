// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.commands.Autonomous;

import com.team3602.robot.RobotContainer;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class FindCargoCommand extends CommandBase {

  boolean upperBall = false;
  boolean lowerBall = false;
  int allCount = 0;
  boolean onlyOneBall = false;
  /** Creates a new FindCargoCommand. */
  public FindCargoCommand() {
    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(RobotContainer.driveSubsystem);
  }

  public FindCargoCommand(boolean onlyOneBall) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.onlyOneBall = onlyOneBall;

    addRequirements(RobotContainer.driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    RobotContainer.driveSpeedPIDSubsystem.setSetpoint(70.0);
    RobotContainer.driveSpeedPIDSubsystem.enable();
    RobotContainer.pixyRotatePIDSubsystem.enable();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    RobotContainer.indexSubsystem.indexIn(1.0);

    lowerBall = RobotContainer.indexSubsystem.indexSensorBottom();
    upperBall = RobotContainer.indexSubsystem.indexSensorTop();

    if(onlyOneBall)
      {
        if(lowerBall)
        {
          allCount++;
        }
        else
        {
          allCount = 0;
        }
      }
    else
      {
        if(upperBall && lowerBall)
        {
          allCount++;
        }
        else
        {
          allCount = 0;
        }
      }

    if((upperBall || onlyOneBall) && lowerBall)
    {
      allCount++;
    }
    else
    {
      allCount = 0;
    }

    RobotContainer.driveSubsystem.driveCartesian(RobotContainer.driveSpeedPIDSubsystem.GetOutputValue(), 0.0, RobotContainer.pixyRotatePIDSubsystem.GetOutputValue());


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    RobotContainer.indexSubsystem.stopMotors();    
    RobotContainer.driveSpeedPIDSubsystem.disable();
    RobotContainer.pixyRotatePIDSubsystem.disable();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    return allCount >= 10;
  }
}
