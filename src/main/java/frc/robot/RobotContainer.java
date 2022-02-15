// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.*;
import frc.robot.commands.*;
import frc.robot.OI;

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
  public static ClimbExtend climbExtend = new ClimbExtend(climberSubsystem);
  public static ClimbRetract climbRetract = new ClimbRetract(climberSubsystem);
  public static ClimbPivot climbPivot = new ClimbPivot(climberSubsystem);
  public static ClimbControl climbControl = new ClimbControl(climberSubsystem);

  public static OI m_oi;

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    // Climber buttons
    OI.climberRetractButton.whileHeld(new ClimbRetract(climberSubsystem));
    OI.climberExtendButton.whileHeld(new ClimbExtend(climberSubsystem));
  }

  public Command getAutonomousCommand() {
    return driveCommand;
  }
}
