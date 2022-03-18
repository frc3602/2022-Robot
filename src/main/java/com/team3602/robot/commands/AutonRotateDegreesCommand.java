// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.commands;

import com.team3602.robot.RobotContainer;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutonRotateDegreesCommand extends CommandBase {

  double angle = 0.0;
  /** Creates a new AutonRotateDegreesCommand. */
  public AutonRotateDegreesCommand(double angle) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.driveSubsystem);

    this.angle = angle;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    RobotContainer.autonRotatePIDSubsystem.setSetpoint(angle);
    RobotContainer.autonRotatePIDSubsystem.enable();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    RobotContainer.autonRotatePIDSubsystem.disable();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return RobotContainer.autonRotatePIDSubsystem.onTarget();
  }
}
