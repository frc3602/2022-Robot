// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.OI;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.IndexSubsystem;

import frc.robot.commands.DriveOrientation;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ClimbExtend;
import frc.robot.commands.ClimbRetract;
import frc.robot.commands.ClimbPivot;
import frc.robot.commands.ClimbControl;

public class RobotContainer {
  public static DriveSubsystem driveSubsystem = new DriveSubsystem();
  public static ClimberSubsystem climberSubsystem = new ClimberSubsystem();
  public static ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  public static IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  public static LimelightSubsystem visionSubsystem = new LimelightSubsystem();
  public static IndexSubsystem indexSubsystem = new IndexSubsystem();

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
    // OI.climberRetractButton.whileHeld(new ClimbRetract());
  }

  public Command getAutonomousCommand() {
    return driveCommand;
  }
}
