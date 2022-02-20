// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// Phoenix & REV Imports
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// WPILib Imports
import frc.robot.OI;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.Shooter;

public class ShooterSubsystem extends SubsystemBase {
  // Creates the motors & controllers and sets the CAN IDs for each one
  CANSparkMax shooterMotor = new CANSparkMax(Shooter.shooterMotorCANID, MotorType.kBrushless);

  public ShooterSubsystem() {
  }

  public void ShootTest() {
    shooterMotor.set(OI.joystick.getRawAxis(3));
  }

  @Override
  public void periodic() {
  }
}
