/* Copyright (C) 2022 Team 3602. All rights reserved. */
/* This work is licensed under the terms of the MIT license */
/* found in the root directory of this project. */

package com.team3602.robot.subsystems;

import com.team3602.robot.RobotContainer;
import com.team3602.robot.Constants.Shooter;

// REV Imports
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  // Creates the motors & controllers and sets the CAN IDs for each one
  CANSparkMax shooterMotor = new CANSparkMax(Shooter.shooterMotorCANID, MotorType.kBrushless);

  SparkMaxPIDController shooterPIDController = shooterMotor.getPIDController();

  int count = 0;
  double targetShooterMotorRPM = 0.0;

  public ShooterSubsystem() {
    configureMotors();
  }

  // Smart Dashboard information
  public void logDataToSmartDashboard() {
  }

  // Converts speed to RPM
  public double speedToRPM(double speedTo) {
    double rpm = 0.0;
    rpm = speedTo * (600.0 / 42.0) * Shooter.shooterGearRatio;
    return rpm;
  }

  // Converts RPM to speed
  public double rpmToSpeed(double RPMTo) {
    double speed = 0.0;
    speed = (RPMTo * (42.0 / 600.0)) / Shooter.shooterGearRatio;
    return speed;
  }

  // Stops all the motors
  public void stopMotors() {
    shooterMotor.set(0.0);
  }

  // Sets the shooter motors RPM
  public void setShooterMotorRPM(double rpm) {
    System.out.println("SetShooterMotorRPM " + rpm);
    targetShooterMotorRPM = rpm;
  }

  public void getShooterMotorSpeed() {
  }

  // Updates the shooter motors RPM
  public void updateShooterMotorSpeed() {
    shooterPIDController.setReference(rpmToSpeed(targetShooterMotorRPM), ControlType.kVelocity);
  }

  public double calculateDistance() {
    double a = RobotContainer.visionSubsystem.getTY() + Shooter.limelightAngleCorrection;
    double answer = Shooter.targetHeight / (Math.tan(Math.toRadians(a)));

    return answer;
  }

  public void calculateAndSetMotorSpeeds() {
    if (RobotContainer.visionSubsystem.noValidTarget()) {
      return;
    }

    double distance = calculateDistance();

    System.out.println("CalculateAndSetMotorSpeeds Distance: " + distance);

    double newTargetShooterMotorRPM = RobotContainer.shooterSubsystem.targetShooterMotorRPM;

    RobotContainer.shooterSubsystem.setShooterMotorRPM(newTargetShooterMotorRPM);
    RobotContainer.shooterSubsystem.logDataToSmartDashboard();
  }

  // Sets the motors to factory default and sets them to coast mode
  private void configureMotors() {
    shooterMotor.restoreFactoryDefaults();

    shooterMotor.setIdleMode(IdleMode.kCoast);
  }

  public void shootStuff() {
    shooterMotor.set(-0.75);
  }
}
