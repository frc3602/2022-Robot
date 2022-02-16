// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// Phoenix & REV Imports
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.Shooter;

public class ShooterSubsystem extends SubsystemBase {
  public ShooterSubsystem() {
    // Creates the motors & controllers and sets the CAN IDs for each one
    CANSparkMax motorTop = new CANSparkMax(Shooter.shooterMotorTopCANID, MotorType.kBrushless);
    CANSparkMax motorBottom = new CANSparkMax(Shooter.shooterMotorBottomCANID, MotorType.kBrushless);
  }

  @Override
  public void periodic() {
  }
}
