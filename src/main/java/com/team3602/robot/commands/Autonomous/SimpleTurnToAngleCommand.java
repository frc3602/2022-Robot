// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.commands.Autonomous;

import com.team3602.robot.RobotContainer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SimpleTurnToAngleCommand extends CommandBase {

  double targetAngle = 0.0;
  double error = 0.0;
  double kP = 0.025;

  int finishCount = 0;

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
    SmartDashboard.putBoolean("Turn Angle Active", true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    error = calculateError();

    SmartDashboard.putNumber("Turn Angle Error", error);

    double rotate = Math.min(0.25, error * kP); // cap at 50% rotate speed
    
    SmartDashboard.putNumber("Turn Angle rotate", rotate);


    RobotContainer.driveSubsystem.driveCartesian(0.0, 0.0, rotate /* * -1.0*/);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    SmartDashboard.putBoolean("Turn Angle Active", false);
    RobotContainer.driveSubsystem.driveCartesian(0.0, 0.0, 0.0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    if(Math.abs(error /*- targetAngle*/) < 3.0)
      finishCount++;

      if(finishCount > 20)
        return true;

    return false;
  }
}
