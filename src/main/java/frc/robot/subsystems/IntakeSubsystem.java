// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// Phoenix Imports
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.Intake;

public class IntakeSubsystem extends SubsystemBase {
  public IntakeSubsystem() {
    // Creates the motors & controllers and sets the CAN IDs for each one
    WPI_TalonFX intakeSpinMotor = new WPI_TalonFX(Intake.intakeSpinMotorCANID);
    WPI_TalonFX intakePivotMotor = new WPI_TalonFX(Intake.intakePivotMotorCANID);
  }

  public void ExtendIntake() {
  }

  public void RetractIntake() {
  }

  public void SpinIntake() {
  }

  public void StopIntake() {
  }
}
