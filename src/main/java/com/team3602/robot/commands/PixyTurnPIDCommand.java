// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.commands;

import com.team3602.robot.OI;
import com.team3602.robot.RobotContainer;
import com.team3602.robot.Constants.PixeyCam;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class PixyTurnPIDCommand extends PIDCommand {
  /** Creates a new PixyTurnPIDCommand. */
  public PixyTurnPIDCommand() {
    super(
        // The controller that the command will use
        new PIDController(0.025, 0.0, 0.0),
        // This should return the measurement
        () -> RobotContainer.pixySubsystem.getLargestBlockX(),
        // This should return the setpoint (can also be a constant)
        () -> PixeyCam.targetX,
        // This uses the output
        output -> {
          double speed = OI.joystick.getRawAxis(1) * -1.0;
          double turn = OI.joystick.getRawAxis(0);
      
          RobotContainer.driveSubsystem.driveCartesian(speed, turn, output * -1.0); // If all on its own, rotate until on target
          // Use the output here
        });
        addRequirements(RobotContainer.driveSubsystem);
        
        getController().setSetpoint(PixeyCam.targetX);
        getController().setTolerance(5.0);
        getController().setIntegratorRange(-0.10, 0.10);
        getController().enableContinuousInput(0, 315);
      }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
