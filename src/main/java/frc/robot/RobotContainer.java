// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.IndexSubsystem;

import frc.robot.commands.DriveCommand;
import frc.robot.commands.ClimbExtend;
import frc.robot.commands.ClimbRetract;
import frc.robot.commands.ClimbPivot;
import frc.robot.commands.ClimbControl;

public class RobotContainer {
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final ClimberSubsystem m_climberSubsystem = new ClimberSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final LimelightSubsystem m_visionSubsystem = new LimelightSubsystem();
  private final IndexSubsystem m_indexSubsystem = new IndexSubsystem();

  private final DriveCommand m_driveCommand = new DriveCommand(m_driveSubsystem);
  private final ClimbExtend m_climbExtend = new ClimbExtend(m_climberSubsystem);
  private final ClimbRetract m_climbRetract = new ClimbRetract(m_climberSubsystem);
  private final ClimbPivot m_climbPivot = new ClimbPivot(m_climberSubsystem);
  private final ClimbControl m_climbControl = new ClimbControl(m_climberSubsystem);

  public static OI m_oi;

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {}

  public Command getAutonomousCommand() {
    return m_driveCommand;
  }
}
