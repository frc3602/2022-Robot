// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.commands.Autonomous;

import com.team3602.robot.RobotContainer;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonDrivePIDCommand extends PIDCommand {
  /** Creates a new AutonDrivePIDCommand. */
  public AutonDrivePIDCommand(double distance) {
    super(
        // The controller that the command will use
        new PIDController(0.025, 0, 0),
        // This should return the measurement
        () -> RobotContainer.driveSubsystem.GetAverageDistance(),
        // This should return the setpoint (can also be a constant)
        () -> distance,
        // This uses the output
        output -> {
          RobotContainer.driveSubsystem.driveCartesian(output, 0.0, 0.0);
          // Use the output here
        });
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.driveSubsystem);
    // Configure additional PID options by calling `getController` here.
    getController().setIntegratorRange(-0.40, 0.40);
    getController().setTolerance(1.0);
    //getController().enableContinuousInput(minimumInput, maximumInput);
    // );
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    boolean atPoint = getController().atSetpoint();

    SmartDashboard.putNumber("AutonDrivePIDCommand error",getController().getPositionError());

    SmartDashboard.putBoolean("AutonDrivePIDCommand atPoint", atPoint);
    return atPoint;
  }
}
