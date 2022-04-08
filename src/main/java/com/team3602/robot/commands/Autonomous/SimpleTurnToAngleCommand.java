// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.commands.Autonomous;

import com.team3602.robot.RobotContainer;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SimpleTurnToAngleCommand extends CommandBase {

  double targetAngle = 0.0;
  double error = 0.0;
  double kP = 0.02;

  int finishCount = 0;

  boolean seekHub = false;
  /** Creates a new SimpleTurnToAngleCommand. */
  public SimpleTurnToAngleCommand(double angle, boolean seekHub) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.driveSubsystem);
    targetAngle = angle;

    this.seekHub = seekHub;
  }

  double calculateError()
  {
    return targetAngle - RobotContainer.driveSubsystem.getGyroAngle();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    // if(seekHub)
    //   RobotContainer.visionSubsystem.lightOn();


    error = calculateError();
    finishCount = 0;

    if(!DriverStation.isFMSAttached())
    {
    // System.out.println("SimpleTurnToAngleCommand init " + targetAngle);
    // SmartDashboard.putBoolean("Turn Angle Active", true);

    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    error = calculateError();


    double rotate = Math.min(0.45, error * kP); // cap at 50% rotate speed
    
    if(!DriverStation.isFMSAttached())
    {
    // System.out.println("SimpleTurnToAngleCommand execute " + targetAngle + " error " + error);

    // SmartDashboard.putNumber("Turn Angle Error", error);
    // SmartDashboard.putNumber("Turn Angle rotate", rotate);

    }


    RobotContainer.driveSubsystem.driveCartesian(0.0, 0.0, rotate /* * -1.0*/);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    if(!DriverStation.isFMSAttached())
    {
    // System.out.println("SimpleTurnToAngleCommand end " + targetAngle);
    // SmartDashboard.putBoolean("Turn Angle Active", false);
    }

    // RobotContainer.visionSubsystem.lightOff();

    RobotContainer.driveSubsystem.driveCartesian(0.0, 0.0, 0.0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {

    // if(seekHub && RobotContainer.visionSubsystem.validTarget() )
    // {

    // }
    boolean onTarget = Math.abs(error /*- targetAngle*/) < 4.0;
    boolean hasTarget = RobotContainer.visionSubsystem.validTarget();
    
    // if(Math.abs(error /*- targetAngle*/) < 3.0 || (seekHub && RobotContainer.visionSubsystem.validTarget()))
    if((hasTarget && seekHub) || onTarget)
    {
      finishCount++;
    }
    else
    {
      finishCount = 0;
    }

    if(!DriverStation.isFMSAttached())
    {
    // System.out.println("SimpleTurnToAngleCommand isfinished " + targetAngle + " error " + error);

    // System.out.println("SimpleTurnToAngleCommand isfinished" + targetAngle + " finishCount " + finishCount);
    }
  
  

    if(finishCount > 10)
        return true;

    return false;
  }
}
