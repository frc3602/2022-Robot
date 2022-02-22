// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.frc3602.robot.subsystems;

// Phoenix Imports
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.frc3602.robot.Constants.Index;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IndexSubsystem extends SubsystemBase {
  public IndexSubsystem() {
    // Creates the motors & controllers and sets the CAN IDs for each one
    WPI_TalonSRX indexMotorTop = new WPI_TalonSRX(Index.indexMotorTopCANID);
    WPI_TalonSRX indexMotorBottom = new WPI_TalonSRX(Index.indexMotorBottomCANID);
    WPI_TalonSRX indexMotorExtra = new WPI_TalonSRX(Index.indexMotorExtraCANID);
  }

  @Override
  public void periodic() {
  }
}
