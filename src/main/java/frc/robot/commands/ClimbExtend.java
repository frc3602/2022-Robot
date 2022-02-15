// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimbExtend extends CommandBase {
  private final ClimberSubsystem climberSubsystem;

  public ClimbExtend(ClimberSubsystem subsystem) {
    climberSubsystem = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    RobotContainer.climberSubsystem.ClimberExtend();
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
