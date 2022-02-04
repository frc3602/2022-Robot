// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.RobotContainer;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveBackwardsCommand extends CommandBase {

  public DriveBackwardsCommand(DriveSubsystem subsystem) {
    addRequirements(RobotContainer.m_driveSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    RobotContainer.m_driveSubsystem.DriveBackwards();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}