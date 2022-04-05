// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.commands.Obsolete;

import com.team3602.robot.RobotContainer;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class TestIntakeCommand extends CommandBase {

  double setSpeed = 0.0;
  /** Creates a new TestIntakeCommand. */
  public TestIntakeCommand(double speed)
  
  {
    addRequirements(RobotContainer.indexSubsystem);// here to declare subsystem dependencies.

    setSpeed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  
  {
    RobotContainer.indexSubsystem.SpinIntakeMotors(setSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  
  {
    RobotContainer.indexSubsystem.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
