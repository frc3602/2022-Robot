// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.subsystems;

import com.team3602.robot.Constants;
import com.team3602.robot.RobotContainer;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class DriveSpeedPIDSubsystem extends PIDSubsystem
  {
    private boolean isRunning = false;

    private double outputValue = 0.0;

    /** Creates a new DriveSpeedPIDSubsystem. */
  public DriveSpeedPIDSubsystem() {
    super(
        // The PIDController used by the subsystem
        new PIDController(0.025, 0, 0));

        setSetpoint(0.0);

        getController().setIntegratorRange(-0.45, 0.45);
        getController().setTolerance(1.0);

        disable();
      }

  @Override
  public void useOutput(double output, double setpoint) {
    outputValue = output;
  }

  public double GetOutputValue()
    {
      return outputValue;
    }

  @Override
  public double getMeasurement()
  {
    // Return the process variable measurement here
    return RobotContainer.driveSubsystem.GetAverageSpeed();
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
