// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.commands.Autonomous;

import com.team3602.robot.Constants;
import com.team3602.robot.RobotContainer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutonDriveCommand extends CommandBase {

  double distance = 0.0;
  double speed = 0.2;
  /** Creates a new AutonDriveCommand. */
  public AutonDriveCommand(double distance) {
    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(RobotContainer.driveSubsystem);

    if(distance < 0.0)
      speed = speed * -1.0;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    //RobotContainer.driveSubsystem.resetGyro();
    //RobotContainer.driveSubsystem.ResetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    if(Constants.testingEnabled)
    {
      SmartDashboard.putNumber("Average Wheels", RobotContainer.driveSubsystem.GetAverageDistance());
    }
    RobotContainer.driveSubsystem.driveCartesian(speed, 0.0, 0.0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    RobotContainer.driveSubsystem.driveCartesian(0.0, 0.0, 0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    if(Constants.testingEnabled)
    {
      SmartDashboard.putNumber("AutonDriveCommand difference", RobotContainer.driveSubsystem.GetAverageDistance() - distance);
    }

    // if( Math.abs(RobotContainer.driveSubsystem.GetAverageDistance() - distance) < 5.0)
    // //if( RobotContainer.driveSubsystem.GetAverageDistance() >= distance) 
    //   return true;
    // else
      return false;
  }
}
