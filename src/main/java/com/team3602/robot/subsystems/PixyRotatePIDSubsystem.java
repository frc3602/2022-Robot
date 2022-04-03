// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.subsystems;

import com.team3602.robot.Constants;
import com.team3602.robot.RobotContainer;
import com.team3602.robot.Constants.PixeyCam;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class PixyRotatePIDSubsystem extends PIDSubsystem
  {
  private boolean isRunning = false;

  private double outputValue = 0.0;

  /** Creates a new PixyRotatePIDSubsystem. */
  public PixyRotatePIDSubsystem() {
    super(
        // The PIDController used by the subsystem
        new PIDController(0.008, 0.5, 0.0));

        setSetpoint(PixeyCam.targetX); // Sets where the PID controller should move the system

        getController().enableContinuousInput(0, 315); //parameters moved to the controller. call getController() for access
        getController().setIntegratorRange(-0.10, 0.1);
    
        getController().setTolerance(5.0);
    
        
        disable();
      }

  public double GetOutputValue()
    {
      return outputValue;
    }

  @Override
  public void useOutput(double output, double setpoint)
  {
    if(Constants.testingEnabled)
    {
    SmartDashboard.putNumber("PixyRotatePIDSubsystem PID Output", output );

    SmartDashboard.putBoolean("PixyRotatePIDSubsystem useOutput IsRunning", IsRunning() );

    }

    outputValue = output * -1.0;

    // if(IsRunning())
    //   RobotContainer.driveSubsystem.driveCartesian(0.0, 0.0, output * 1.0); // If all on its own, rotate until on target
  }

  @Override
  public double getMeasurement()
  {
    if(RobotContainer.pixySubsystem.getBlockCount() > 0)
      return RobotContainer.pixySubsystem.getLargestBlockX();
    else
      return Constants.PixeyCam.targetX;
  }

  public boolean onTarget()
  {
    return this.getController().atSetpoint();
  }


  public boolean IsRunning()
    {
    return isRunning;
    }

  @Override
  public void enable()
    {
    if(Constants.testingEnabled)
      System.out.println("enable");
    isRunning = true;
    super.enable();
    }
  @Override

  public void disable()
    {
    if(Constants.testingEnabled)
      System.out.println("disable");
    isRunning = false;
    super.disable();
    }

}
