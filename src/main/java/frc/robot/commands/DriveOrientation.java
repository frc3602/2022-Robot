// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveOrientation extends CommandBase {

  public DriveOrientation(DriveSubsystem subsystem) {
    addRequirements(RobotContainer.driveSubsystem);
  }

  @Override
  public void initialize() {
    RobotContainer.driveSubsystem.ToggleOrientation();
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
