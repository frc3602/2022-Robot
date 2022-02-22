/* Copyright (C) 2022 Team 3602. All rights reserved. */
/* This work is licensed under the terms of the MIT license */
/* found in the root directory of this project. */

package com.frc3602.robot.subsystems;

import com.frc3602.robot.Constants.Index;

// Phoenix Imports
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

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
