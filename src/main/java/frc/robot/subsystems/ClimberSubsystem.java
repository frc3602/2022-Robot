// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {
  public ClimberSubsystem() {
    // Creates the motors & controllers and sets the CAN IDs for each one
    WPI_TalonFX supportLeft = new WPI_TalonFX(Constants.climbSupportLeftCANID);
    WPI_TalonFX armLeft = new WPI_TalonFX(Constants.climbArmLeftCANID);
    WPI_TalonFX supportRight = new WPI_TalonFX(Constants.climbSupportRightCANID);
    WPI_TalonFX armRight = new WPI_TalonFX(Constants.climbArmRightCANID);
  }

  @Override
  public void periodic() {
  }
}
