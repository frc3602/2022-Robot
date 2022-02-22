// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc3602.robot;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.Command;

import frc3602.robot.commands.*;
import frc3602.robot.subsystems.*;

public class RobotContainer {
  // Subsystems
  public static DriveSubsystem driveSubsystem = new DriveSubsystem();
  public static ClimberSubsystem climberSubsystem = new ClimberSubsystem();
  public static ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  public static IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  public static LimelightSubsystem visionSubsystem = new LimelightSubsystem();
  public static IndexSubsystem indexSubsystem = new IndexSubsystem();

  // Commands
  public static DriveOrientation driveOrientation = new DriveOrientation(driveSubsystem);
  public static DriveCommand driveCommand = new DriveCommand(driveSubsystem);
  public static ClimbOneExtend climbOneExtend = new ClimbOneExtend(climberSubsystem);
  public static ClimbOneRetract climbOneRetract = new ClimbOneRetract(climberSubsystem);
  public static ClimbTwoExtend climbTwoExtend = new ClimbTwoExtend(climberSubsystem);
  public static ClimbTwoRetract climbTwoRetract = new ClimbTwoRetract(climberSubsystem);
  public static ClimbPivot climbPivot = new ClimbPivot(climberSubsystem);
  public static ActivateIntake acticateIntake = new ActivateIntake(intakeSubsystem);

  // Operator interfaces
  public static OI oi;

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
  }

  public Command getAutonomousCommand() {
    return driveCommand;
  }
}
