// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.commands.Autonomous;

import com.team3602.robot.RobotContainer;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutonRotateDegreesCommand extends CommandBase {

  double angle = 0.0;
  boolean seekHub = false;
  /** Creates a new AutonRotateDegreesCommand. */
  public AutonRotateDegreesCommand(double angle, boolean seekHub) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.driveSubsystem);

    this.angle = angle;
    this.seekHub = seekHub;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    if(seekHub)
      RobotContainer.visionSubsystem.lightOn();
    RobotContainer.autonRotatePIDSubsystem.setSetpoint(angle);
    RobotContainer.autonRotatePIDSubsystem.enable();
    
    System.out.println("AutonRotateDegreesCommand init " + angle);




  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    RobotContainer.autonRotatePIDSubsystem.disable();
    RobotContainer.visionSubsystem.lightOff();

    System.out.println("AutonRotateDegreesCommand end " + angle + " " + interrupted);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    boolean hasTarget = RobotContainer.visionSubsystem.validTarget();
    boolean onTarget = RobotContainer.autonRotatePIDSubsystem.onTarget();
    return (hasTarget && seekHub) || onTarget;
  }
}
