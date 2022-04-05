// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.subsystems;

import com.team3602.robot.Constants;
import com.team3602.robot.RobotContainer;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

/** Add your docs here. */
public class AutonRotatePIDSubsystem extends PIDSubsystem {

  private boolean isRunning = false;

  /** Add your docs here. */
  public AutonRotatePIDSubsystem() {
    // Intert a subsystem name and PID values here
    super( new PIDController(0.0005, 0.5, 0.0));
    setSetpoint(0); // Sets where the PID controller should move the system

    getController().enableContinuousInput(180.0, -180.0); //parameters moved to the controller. call getController() for access
    getController().setIntegratorRange(0.45, -0.45);

    getController().setTolerance(2.0);

    
    disable();
  }

  @Override
  public void useOutput(double output, double setpoint)
  {
    if(Constants.testingEnabled)
    {
    SmartDashboard.putNumber("AutonRotatePIDSubsystem PID Output", output );

    SmartDashboard.putBoolean("AutonRotatePIDSubsystem useOutput IsRunning", IsRunning() );

    }

    if(IsRunning())
      RobotContainer.driveSubsystem.driveCartesian(0.0, 0.0, output * 1.0); // If all on its own, rotate until on target
  }

  @Override
  public double getMeasurement()
  {
    double angle = RobotContainer.driveSubsystem.getGyroAngle();

    return angle;
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
