// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// Phoenix Imports
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.Index;

public class IndexSubsystem extends SubsystemBase {
  public IndexSubsystem() {
    // Creates the motors & controllers and sets the CAN IDs for each one
    WPI_VictorSPX motorTop = new WPI_VictorSPX(Index.indexMotorTopCANID);
    WPI_VictorSPX motorBottom = new WPI_VictorSPX(Index.indexMotorBottomCANID);
    WPI_VictorSPX motorExtra = new WPI_VictorSPX(Index.indexMotorExtraCANID);
  }

  @Override
  public void periodic() {
  }
}
