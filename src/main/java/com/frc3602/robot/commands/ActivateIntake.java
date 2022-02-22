// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.frc3602.robot.commands;

import com.frc3602.robot.RobotContainer;
import com.frc3602.robot.subsystems.IntakeSubsystem;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ActivateIntake extends CommandBase {

  public ActivateIntake(IntakeSubsystem subsystem) {
    addRequirements(RobotContainer.intakeSubsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    RobotContainer.intakeSubsystem.SpinIntake();
    RobotContainer.intakeSubsystem.ExtendIntake();
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.intakeSubsystem.StopIntake();
    RobotContainer.intakeSubsystem.RetractIntake();
  }
}
