// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

// Phoenix & REV Imports
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// WPILib Imports
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.Constants.Shooter;

public class ShooterSubsystem extends SubsystemBase {
  // Creates the motors & controllers and sets the CAN IDs for each one
  CANSparkMax shooterMotor = new CANSparkMax(Shooter.shooterMotorCANID, MotorType.kBrushless);

  SparkMaxPIDController shooterPIDController = shooterMotor.getPIDController();

  int count = 0;
  double targetShooterMotorRPM = 0.0;

  public ShooterSubsystem() {
    ConfigShooterMotor();
  }

  // Smart Dashboard information
  public void LogDataToSmartDashboard() {
  }

  public double SpeedToRPM(double speedTo) {
    double rpm = 0.0;
    rpm = speedTo * (600.0 / 2048.0) * Shooter.shooterGearRatio;
    return rpm;
  }

  public double RPMToSpeed(double RPMTo) {
    double speed = 0.0;
    speed = (RPMTo * (2048.0 / 600.0)) / Shooter.shooterGearRatio;
    return speed;
  }

  public void StopMotors() {
    shooterMotor.set(0.0);
  }

  public void SetShooterMotorRPM(double rpm) {
    System.out.println("SetShooterMotorRPM " + rpm);
    targetShooterMotorRPM = rpm;
  }

  public void GetShooterMotorSpeed() {
  }

  public void UpdateShooterMotorSpeed() {
    shooterPIDController.setReference(RPMToSpeed(targetShooterMotorRPM), ControlType.kVelocity);
  }

  public double CalculateDistance() {
    double a = RobotContainer.visionSubsystem.GetTY() + 10.6;
    double answer = Shooter.targetHeight / (Math.tan(Math.toRadians(a)));

    return answer;
  }

  public void CalculateAndSetMotorSpeeds() {
    if (RobotContainer.visionSubsystem.NoValidTarget()) {
      return;
    }

    double distance = CalculateDistance();

    System.out.println("CalculateAndSetMotorSpeeds Distance: " + distance);

    double newTargetShooterMotorRPM = RobotContainer.shooterSubsystem.targetShooterMotorRPM;

    RobotContainer.shooterSubsystem.SetShooterMotorRPM(newTargetShooterMotorRPM);
    RobotContainer.shooterSubsystem.LogDataToSmartDashboard();
  }

  private void ConfigShooterMotor() {
    shooterMotor.restoreFactoryDefaults();

    shooterMotor.setIdleMode(IdleMode.kCoast);
  }
}
