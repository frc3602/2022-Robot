// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.commands.Autonomous;

import com.team3602.robot.RobotContainer;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class SimpleTurnToAngleCommand extends CommandBase {

  double targetAngle = 0.0;
  double error = 0.0;
  double kP = 0.25;

  /** Creates a new SimpleTurnToAngleCommand. */
  public SimpleTurnToAngleCommand(double angle) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.driveSubsystem);
    targetAngle = angle;
  }

  double calculateError()
  {
    return targetAngle - RobotContainer.driveSubsystem.getGyroAngle();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    error = calculateError();

    double rotate = Math.min(0.5, error * kP); // cap at 50% rotate speed

    RobotContainer.driveSubsystem.driveCartesian(0.0, 0.0, rotate * -1.0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    if(Math.abs(error - targetAngle) < 3.0)
      return true;

    return false;
  }
}
