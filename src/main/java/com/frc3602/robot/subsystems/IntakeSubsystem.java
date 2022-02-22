// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.frc3602.robot.subsystems;

// Phoenix Imports
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.frc3602.robot.Constants.Intake;
import com.ctre.phoenix.motorcontrol.ControlMode;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  // Creates the motors & controllers and sets the CAN IDs for each one
  WPI_TalonSRX intakeSpinMotor = new WPI_TalonSRX(Intake.intakeSpinMotorCANID);
  WPI_TalonSRX intakePivotMotor = new WPI_TalonSRX(Intake.intakePivotMotorCANID);

  public IntakeSubsystem() {
  }

  public void ExtendIntake() {
  }

  public void RetractIntake() {
  }

  public void SpinIntake() {
  }

  public void StopIntake() {
    intakeSpinMotor.set(ControlMode.PercentOutput, 0.0);
    intakePivotMotor.set(ControlMode.PercentOutput, 0.0);

  }
}
