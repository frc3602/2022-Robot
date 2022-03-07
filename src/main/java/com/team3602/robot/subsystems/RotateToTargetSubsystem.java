// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.subsystems;

import com.team3602.robot.OI;
import com.team3602.robot.Robot;
import com.team3602.robot.RobotContainer;
import com.team3602.robot.Constants.Shooter;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;

public class RotateToTargetSubsystem extends PIDSubsystem {

  double x = RobotContainer.visionSubsystem.tx.getDouble(0.0);

  public double PIDOut; 

  public boolean dependent = false;

  private boolean isRunning = false;

  /** Creates a new RotateToTargetSubsystem. */
  public RotateToTargetSubsystem()
  {
    // The PIDController used by the subsystem
    super( new PIDController(0.12, 0.0, 0.1));

    setSetpoint(0); // Sets where the PID controller should move the system


    // setInputRange(-27f, 27f); // Lowest value is -27 degrees (to the left), highest value is 27 degrees (to the right)

    // setOutputRange(-0.5, 0.5); // The outputs sent to the motors, which ranges from -1 to 1.

    getController().enableContinuousInput(-27.0, 27.0); //parameters moved to the controller. call getController() for access
    getController().setIntegratorRange(-0.5, 0.5);

    getController().setTolerance(Shooter.rotationalErrorTolerance);

    //m_controller.setTolerance(Shooter.rotationalErrorTolerance);
    //setAbsoluteTolerance(Shooter.rotationalErrorTolerance); 
    
    disable(); // - Enables the PID controller.
  }

  @Override
  public void useOutput(double output, double setpoint) {
    // turnValue = output;
    SmartDashboard.putNumber("PID Output", output );

    double speed = OI.joystick.getRawAxis(1) * -1.0;
    double turn = OI.joystick.getRawAxis(0);

    if(IsRunning())
      RobotContainer.driveSubsystem.driveCartesian(speed, turn, output); // If all on its own, rotate until on target
    // else
    //   Robot.Drive.rotate(0.0);

  }

  @Override
  public double getMeasurement() {
    x = RobotContainer.visionSubsystem.getTX(); // updates x value

    SmartDashboard.putNumber("PID Error", x );
    return x;
  }


  public boolean IsRunning()
    {
    return isRunning;
    }

  @Override
  public void enable()
    {
    System.out.println("enable");
    isRunning = true;
    super.enable();
    }
  @Override

  public void disable()
    {
    System.out.println("disable");
    isRunning = false;
    super.disable();
    }


}