// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// Phoenix Imports
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.Climber;

public class ClimberSubsystem extends SubsystemBase {
  // Creates the motors & controllers and sets the CAN IDs for each one
  WPI_TalonFX supportLeft = new WPI_TalonFX(Climber.climbSupportLeftCANID);
  WPI_TalonFX armLeft = new WPI_TalonFX(Climber.climbArmLeftCANID);
  WPI_TalonFX supportRight = new WPI_TalonFX(Climber.climbSupportRightCANID);
  WPI_TalonFX armRight = new WPI_TalonFX(Climber.climbArmRightCANID);

  public ClimberSubsystem() {
    armLeft.setNeutralMode(NeutralMode.Brake);
    armRight.setNeutralMode(NeutralMode.Brake);
  }

  public void ClimberExtend() {
    armLeft.set(ControlMode.PercentOutput, -0.25);
    armRight.set(ControlMode.PercentOutput, -0.25);
  }

  public void ClimberRetract() {
    armLeft.set(ControlMode.PercentOutput, 0.25);
    armRight.set(ControlMode.PercentOutput, 0.25);
  }

  @Override
  public void periodic() {
  }
}
