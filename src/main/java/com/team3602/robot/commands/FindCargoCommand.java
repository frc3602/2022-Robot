// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.commands;

import com.team3602.robot.RobotContainer;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class FindCargoCommand extends CommandBase {

  boolean upperBall = false;
  boolean lowerBall = false;
  int bothCount = 0;
  /** Creates a new FindCargoCommand. */
  public FindCargoCommand() {
    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(RobotContainer.driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    RobotContainer.driveSpeedPIDSubsystem.setSetpoint(65.0);
    RobotContainer.driveSpeedPIDSubsystem.enable();
    RobotContainer.pixyRotatePIDSubsystem.enable();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    RobotContainer.indexSubsystem.indexIn();

    lowerBall = RobotContainer.indexSubsystem.indexSensorBottom();
    upperBall = RobotContainer.indexSubsystem.indexSensorTop();

    if(upperBall && lowerBall)
    {
      bothCount++;
    }
    else
    {
      bothCount = 0;
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
    return bothCount >= 25;
  }
}
