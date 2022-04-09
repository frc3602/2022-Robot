/*
 * @(#)DriveSubsystem.java        1.0 22/03/04
 *
 * Copyright (c) 2022 Team 3602
 * 500 S Lincoln Rd, Escanaba, Michigan, 49829, U.S.A.
 * All rights reserved.
 *
 * This software is licensed under the terms of the GPLv3 license
 * found in the root directory of this project.
 */

package com.team3602.robot.subsystems;

import com.team3602.robot.CTREUpdates;
import com.team3602.robot.Constants;
import com.team3602.robot.RobotContainer;
import com.team3602.robot.Constants.Drivetrain;
// Phoenix & navX Imports
import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;

/**
 * The DriveSubsystem class provides all methods and functionality of the
 * robot's drivetrain.
 *
 * @version 1.0 04 Mar 2022
 * @author Cody Wellman
 */
public class DriveSubsystem extends SubsystemBase {
  // Creates the motors & controllers and sets the CAN IDs for each one
  WPI_TalonFX frontLeft = new WPI_TalonFX(Drivetrain.driveFrontLeftCANID);
  WPI_TalonFX backLeft = new WPI_TalonFX(Drivetrain.driveBackLeftCANID);
  WPI_TalonFX frontRight = new WPI_TalonFX(Drivetrain.driveFrontRightCANID);
  WPI_TalonFX backRight = new WPI_TalonFX(Drivetrain.driveBackRightCANID);

  // NavX for Gyro
  private AHRS navX;

  // MecanumDrive Information
  private MecanumDrive mecanumDrive;


  /**
   * Constructor for {@link DriveSubsystem} class to run the
   * {@link #configureMotors()} method, set the motors for the mecanum drive, and
   * to initialize NavX and check to make sure its working.
   */
  public DriveSubsystem() {
    //configureMotors();

    mecanumDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

    try {
      navX = new AHRS(SPI.Port.kMXP);
    } catch (RuntimeException ex) {
      System.out.println("ERROR: Unable to instantiate navX" + ex.getMessage());
    }
  }

  @Override
  public void periodic()
    {
      logDataToSmartDashboard();
    }

  /**
   * Method to log information to the Smart Dashboard.
   */
  public void logDataToSmartDashboard()
  {
    if(!DriverStation.isFMSAttached())
    {
      // SmartDashboard.putNumber("Front Left distance reading:", GetEncoderDistance(frontLeft));
      // SmartDashboard.putNumber("frontLeft.getSelectedSensorPosition reading:", frontLeft.getSelectedSensorPosition());
      // SmartDashboard.putNumber("Back Left distance reading:", GetEncoderDistance(backLeft));
      // SmartDashboard.putNumber("Front Right distance reading:", GetEncoderDistance(frontRight));
      // SmartDashboard.putNumber("Back Right distance reading:", GetEncoderDistance(backRight));

      // SmartDashboard.putNumber("GetAverageDistance reading:", GetAverageDistance());

      // SmartDashboard.putNumber("gyro roll", navX.getRoll());
      // SmartDashboard.putNumber("gyro Yaw", navX.getYaw());
      // SmartDashboard.putNumber("gyro pitch", navX.getPitch());
      // SmartDashboard.putNumber("gyro getGyroAngle", getGyroAngle());
      // SmartDashboard.putNumber("GetAverageSpeed reading:", GetAverageSpeed());

  }
    /*
     * SmartDashboard.putNumber("Front Left RPM reading:" + frontLeftRPM);
     * SmartDashboard.putNumber("Back Left RPM reading:" + backLeftRPM);
     * SmartDashboard.putNumber("Front Right RPM reading:" + frontRightRPM);
     * SmartDashboard.putNumber("Back Right RPM reading:" + backRightRPM);
     */
  }

  public double GetEncoderDistance(WPI_TalonFX motor)
  {
    double dist = (motor.getSelectedSensorPosition() / Constants.falconTicksPerRotation) * Constants.Drivetrain.distancePerMotorRev;
    return dist;
  }


  public double GetAverageDistance()
  {

    double total = GetEncoderDistance(frontLeft) + GetEncoderDistance(backLeft) + GetEncoderDistance(frontRight) + GetEncoderDistance(backRight);

    return total / 4.0;

  }

  public double GetEncoderSpeed(WPI_TalonFX motor)
  {
    return ((motor.getSelectedSensorVelocity() / Constants.falconTicksPerRotation) * 10.0) * Constants.Drivetrain.distancePerMotorRev;
  }

  public double GetAverageSpeed()
    {
      double total = GetEncoderSpeed(frontLeft) + GetEncoderSpeed(backLeft) + GetEncoderSpeed(frontRight) + GetEncoderSpeed(backRight);

      return total / 4.0;
      }

  /**
   * Method to get the current angle of the navX gyro.
   */
  public double getGyroAngle() {
    return navX.getAngle();
  }

  /**
   * Method to reset the value of the navX gyro.
   */
  public void resetGyro() {
    navX.reset();
  }

  /**
   * Method to create the cartesian drive.
   */
  public void driveCartesian(double y, double x, double z)
  {

      double maxSpeed = 1.0;

      if(RobotContainer.climberSubsystem.ClimberActive())
        maxSpeed = 0.5;

      double xSpeed = x * maxSpeed;

      double ySpeed = y *  maxSpeed;

      double rot = z * maxSpeed;

    if(!DriverStation.isFMSAttached())
    {
      // SmartDashboard.putNumber("Drive cart Speed", xSpeed);
      // SmartDashboard.putNumber("Drive cart turn", ySpeed);
      // SmartDashboard.putNumber("Drive cart rotate", rot);
    }
  
      RobotContainer.driveSubsystem.mecanumDrive.driveCartesian(ySpeed, xSpeed, rot);
  }

  public void ResetEncoders()
  {
    System.out.println("ResetEncoders drivetrain");

    frontLeft.setSelectedSensorPosition(0.0);
    backLeft.setSelectedSensorPosition(0.0);
    frontRight.setSelectedSensorPosition(0.0);
    backRight.setSelectedSensorPosition(0.0);

  }

  /**
   * Method to set the drivetrain motors to factory defaults and to invert the
   * right side.
   */
  public void configureMotors() {
    // frontLeft.configFactoryDefault();
    frontLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    backLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    frontRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    backRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

    CTREUpdates.UpdateMotorValues(frontLeft);
    CTREUpdates.UpdateMotorValues(backLeft);
    CTREUpdates.UpdateMotorValues(frontRight);
    CTREUpdates.UpdateMotorValues(backRight);

    // // backLeft.configFactoryDefault();
    // frontRight.configFactoryDefault();
    // backRight.configFactoryDefault();

    frontLeft.setInverted(false);
    backLeft.setInverted(false);
    frontRight.setInverted(true);
    backRight.setInverted(true);
  }
}
