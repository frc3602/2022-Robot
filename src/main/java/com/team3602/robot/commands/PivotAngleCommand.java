// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.commands;

import com.team3602.robot.RobotContainer;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class PivotAngleCommand extends CommandBase {
  double angle;
  boolean isInner;

  /** Creates a new PivotAngleCommand. */
  public PivotAngleCommand(boolean isInner, double angle)
  {
    this.angle = angle;
    this.isInner = isInner;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    System.out.println("PivotAngleCommand initialize");
    if(isInner)
      RobotContainer.climberSubsystem.PivotInner(angle);
    else
      RobotContainer.climberSubsystem.PivotOuter(angle);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
   {
    RobotContainer.climberSubsystem.ReoportStuff();
  
   }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    //System.out.println("PivotAngleCommand isFinished");
    if(isInner)
      {
      if(Math.abs( RobotContainer.climberSubsystem.GetCurrentInnerLeftAngle() - angle ) < 1.0 &&
         Math.abs( RobotContainer.climberSubsystem.GetCurrentInnerRightAngle() - angle ) < 1.0)
         {
          System.out.println("PivotAngleCommand isFinished");
          return true;

         }
      else
       return false;
      }
    else //outer
      {
      if(Math.abs( RobotContainer.climberSubsystem.GetCurrentOuterLeftAngle() - angle ) < 1.0 &&
         Math.abs( RobotContainer.climberSubsystem.GetCurrentOuterRightAngle() - angle ) < 1.0)
         {
          System.out.println("PivotAngleCommand isFinished");
          return true;

         }
      else
       return false;
      }
  }
}
