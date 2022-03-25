// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.commands;

import com.team3602.robot.RobotContainer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TestShooterSpeedCommand extends CommandBase {
  /** Creates a new TestShooterSpeedCommand. */
  public TestShooterSpeedCommand() {
    addRequirements(); // here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    //SmartDashboard.putNumber("TestShooterSpeed", 150.0);
    //RobotContainer.shooterSubsystem.GetPIDValuesFromDash();
    RobotContainer.visionSubsystem.lightOn();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    double speed = SmartDashboard.getNumber("TestShooterSpeed", 150.0);
    RobotContainer.visionSubsystem.logDataToSmartDashboard();
    
    RobotContainer.shooterSubsystem.setShooterMotorRPM(speed);

    RobotContainer.shooterSubsystem.updateShooterMotorSpeed();

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
    RobotContainer.visionSubsystem.lightOff();

    RobotContainer.shooterSubsystem.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}