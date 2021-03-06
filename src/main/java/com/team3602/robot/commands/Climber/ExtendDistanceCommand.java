// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.commands.Climber;

import com.team3602.robot.RobotContainer;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ExtendDistanceCommand extends CommandBase {

  double distance;
  boolean isInner;
  /** Creates a new ExtendDistanceCommand. */
  public ExtendDistanceCommand(boolean isInner, double distance)
  {
    this.distance = distance;
    this.isInner = isInner;
    // Use addRequirements() here to declare subsystem dependencies.

    //addRequirements(RobotContainer.climberSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {

    if(isInner)
    {
      System.out.println("ExtendDistanceCommand inner initialize " + distance);
      RobotContainer.climberSubsystem.ExtendInner(distance);

    }
    else
    {
      System.out.println("ExtendDistanceCommand outer initialize " + distance);
      RobotContainer.climberSubsystem.ExtendOuter(distance);
      }

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.climberSubsystem.ReoportStuff();
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {

    if(isInner)
      {
      if(Math.abs( RobotContainer.climberSubsystem.GetCurrentInnerLeftLength() - distance ) < 0.5 &&
         Math.abs( RobotContainer.climberSubsystem.GetCurrentInnerRightLength() - distance ) < 0.5)
         {
          System.out.println("ExtendDistanceCommand isFinished " + distance);
          return true;

         }
      else
       return false;
      }
    else //outer
      {

        if(Math.abs( RobotContainer.climberSubsystem.GetCurrentOuterLeftLength() - distance ) < 0.5 &&
           Math.abs( RobotContainer.climberSubsystem.GetCurrentOuterRightLength() - distance ) < 0.5)
           {
            System.out.println("ExtendDistanceCommand isFinished " + distance);
            return true;
  
           }
        else
         return false;
        }
  
  }
}
