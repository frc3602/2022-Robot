// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc3602.robot.commands;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc3602.robot.RobotContainer;
import frc3602.robot.subsystems.DriveSubsystem;

public class DriveCommand extends CommandBase {

  public DriveCommand(DriveSubsystem subsystem) {
    addRequirements(RobotContainer.driveSubsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    RobotContainer.driveSubsystem.DriveCartesian();
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
