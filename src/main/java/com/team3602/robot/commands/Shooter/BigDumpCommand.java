// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.commands.Shooter;

import com.team3602.robot.RobotContainer;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class BigDumpCommand extends CommandBase {
  /** Creates a new BigDumpCommand. */
  public BigDumpCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    RobotContainer.shooterSubsystem.setShooterMotorRPM(2000);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    RobotContainer.shooterSubsystem.updateShooterMotorSpeed();
    RobotContainer.visionSubsystem.logDataToSmartDashboard();

    if(RobotContainer.shooterSubsystem.IsShooterSpeedOnTarget())
      {
        RobotContainer.indexSubsystem.shoot();
      }
    else
      RobotContainer.indexSubsystem.stopMotors();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    RobotContainer.shooterSubsystem.setShooterMotorRPM(0.0);
    RobotContainer.shooterSubsystem.updateShooterMotorSpeed();
    RobotContainer.shooterSubsystem.stopMotor();
    RobotContainer.indexSubsystem.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
