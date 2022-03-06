/*
 * @(#)ShooterSubsystem.java        1.0 22/03/04
 *
 * Copyright (c) 2022 Team 3602
 * 500 S Lincoln Rd, Escanaba, Michigan, 49829, U.S.A.
 * All rights reserved.
 *
 * This software is licensed under the terms of the GPLv3 license
 * found in the root directory of this project.
 */

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

/**
 * The ShooterSubsystem class provides all methods and functionality of the
 * robot's shooter.
 *
 * @version 1.0 04 Mar 2022
 * @author Cody Wellman
 */
public class ShooterSubsystem extends SubsystemBase {
  // Creates the motors & controllers and sets the CAN IDs for each one
  CANSparkMax shooterMotor = new CANSparkMax(Shooter.shooterMotorCANID, MotorType.kBrushless);

  SparkMaxPIDController shooterPIDController = shooterMotor.getPIDController();

  int count = 0;
  double targetShooterMotorRPM = 0.0;

  /**
   * Constructor for {@link ShooterSubsystem} class to run the
   * {@link #configureMotors()} method.
   */
  public ShooterSubsystem() {
    configureMotors();
  }

  /**
   * Method to log information to the Smart Dashboard.
   */
  public void logDataToSmartDashboard() {
  }

  // /**
  //  * Method to convert speed to RPM.
  //  * 
  //  * @param speedTo
  //  * @return the current rpm
  //  */
  // public double speedToRPM(double speedTo) {
  //   double rpm = 0.0;
  //   rpm = speedTo * (600.0 / 42.0) * Shooter.shooterGearRatio;
  //   return rpm;
  // }

  // /**
  //  * Method to convert RPM to speed.
  //  * 
  //  * @param RPMTo
  //  * @return the current speed
  //  */
  // public double rpmToSpeed(double RPMTo) {
  //   double speed = 0.0;
  //   speed = (RPMTo * (42.0 / 600.0)) / Shooter.shooterGearRatio;
  //   return speed;
  // }

  /**
   * Method to stop the shooter motor.
   */
  public void stopMotor() {
    shooterMotor.set(0.0);
  }

  /**
   * Method to set the rpm of the shooter.
   * 
   * @param rpm the rpm of the shooter
   */
  public void setShooterMotorRPM(double rpm) {
    System.out.println("SetShooterMotorRPM " + rpm);
    targetShooterMotorRPM = rpm;
  }

  /**
   * Method to get the speed of the shooter motor.
   */
  public void getShooterMotorSpeed() {
  }

  /**
   * Method to update the speed of the shooter motor.
   */
  public void updateShooterMotorSpeed() {
    shooterPIDController.setReference(targetShooterMotorRPM, ControlType.kVelocity);
  }

  /**
   * Method to calculate the distance from the limelight to the target taking in
   * account of the angle that the limelight is at.
   * 
   * @return the calculated double for the shooter
   */
  public double calculateDistance() {
    double a = RobotContainer.visionSubsystem.getTY() + Shooter.limelightAngleCorrection;
    double answer = Shooter.targetHeight / (Math.tan(Math.toRadians(a)));

    return answer;
  }

  /**
   * Method to caclulate and set the shooter motor speeds accordingly.
   * 
   * @return if there is a valid target
   */
  public void calculateAndSetMotorSpeeds()
  {
    if (RobotContainer.visionSubsystem.noValidTarget())
    {
      return;
    }

    double distance = calculateDistance();

    System.out.println("CalculateAndSetMotorSpeeds Distance: " + distance);

    //some magic decimal crazyness going on

    double newTargetShooterMotorRPM = RobotContainer.shooterSubsystem.targetShooterMotorRPM;

    RobotContainer.shooterSubsystem.setShooterMotorRPM(newTargetShooterMotorRPM);
    RobotContainer.shooterSubsystem.logDataToSmartDashboard();
  }

  /**
   * Method to set the shooter motor to factory defaults and coast mode.
   */
  private void configureMotors()
  {
    shooterMotor.restoreFactoryDefaults();

    shooterMotor.setIdleMode(IdleMode.kCoast);
  }

  /**
   * Method to set the shooter motor to 75% for testing.
   */
  public void shootStuff() {
    shooterMotor.set(-0.75);
  }
}
