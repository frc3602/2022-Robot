// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  public ShooterSubsystem() {
    // Creates the motors & controllers and sets the CAN IDs for each one
    WPI_TalonFX motorTop = new WPI_TalonFX(Constants.shooterMotorTopCANID);
    WPI_TalonFX motorBottom = new WPI_TalonFX(Constants.shooterMotorBottomCANID);
  }

  @Override
  public void periodic() {

  }
}
