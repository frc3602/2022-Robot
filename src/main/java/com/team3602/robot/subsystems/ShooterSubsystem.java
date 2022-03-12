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
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  
  private RelativeEncoder shooterEncoder = shooterMotor.getEncoder();

  int count = 0;
  double targetShooterMotorRPM = 0.0;

  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;

  /**
   * Constructor for {@link ShooterSubsystem} class to run the
   * {@link #configureMotors()} method.
   */
  public ShooterSubsystem() {
    //configureMotors();
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


    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);

    // if PID coefficients on SmartDashboard have changed, write new values to controller
    if((p != kP)) { shooterPIDController.setP(p); kP = p; }
    if((i != kI)) { shooterPIDController.setI(i); kI = i; }
    if((d != kD)) { shooterPIDController.setD(d); kD = d; }
    if((iz != kIz)) { shooterPIDController.setIZone(iz); kIz = iz; }
    if((ff != kFF)) { shooterPIDController.setFF(ff); kFF = ff; }
    if((max != kMaxOutput) || (min != kMinOutput)) { 
      shooterPIDController.setOutputRange(min, max); 
      kMinOutput = min; kMaxOutput = max; 
    }

    /**
     * PIDController objects are commanded to a set point using the 
     * SetReference() method.
     * 
     * The first parameter is the value of the set point, whose units vary
     * depending on the control type set in the second parameter.
     * 
     * The second parameter is the control type can be set to one of four 
     * parameters:
     *  com.revrobotics.CANSparkMax.ControlType.kDutyCycle
     *  com.revrobotics.CANSparkMax.ControlType.kPosition
     *  com.revrobotics.CANSparkMax.ControlType.kVelocity
     *  com.revrobotics.CANSparkMax.ControlType.kVoltage
     */
    // double setPoint = m_stick.getY()*maxRPM;
    shooterPIDController.setReference(targetShooterMotorRPM, CANSparkMax.ControlType.kVelocity);
    
    SmartDashboard.putNumber("SetPoint", targetShooterMotorRPM);
    SmartDashboard.putNumber("ProcessVariable", shooterEncoder.getVelocity());


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

    setShooterMotorRPM(newTargetShooterMotorRPM);
    logDataToSmartDashboard();
  }

  /**
   * Method to set the shooter motor to factory defaults and coast mode.
   */
  public void InitShooter()
  {
    shooterMotor.restoreFactoryDefaults();

    //shooterMotor.setIdleMode(IdleMode.kCoast);


    // PID coefficients
    kP = 6e-5; 
    kI = 0;
    kD = 0; 
    kIz = 0; 
    kFF = 0.000015; 
    kMaxOutput = 1; 
    kMinOutput = -1;
    maxRPM = 5700;

    // set PID coefficients
    shooterPIDController.setP(kP);
    shooterPIDController.setI(kI);
    shooterPIDController.setD(kD);
    shooterPIDController.setIZone(kIz);
    shooterPIDController.setFF(kFF);
    shooterPIDController.setOutputRange(kMinOutput, kMaxOutput);

    // display PID coefficients on SmartDashboard
    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);

  }

  /**
   * Method to set the shooter motor to 75% for testing.
   */
  public void shootStuff() {
    shooterMotor.set(-0.75);
  }
}
