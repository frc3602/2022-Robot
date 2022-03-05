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

import com.team3602.robot.OI;
import com.team3602.robot.RobotContainer;
import com.team3602.robot.Constants.Drivetrain;

// Phoenix & navX Imports
import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

// WPILib Imports
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
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
    configureMotors();

    mecanumDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

    try {
      navX = new AHRS(SPI.Port.kMXP);
    } catch (RuntimeException ex) {
      System.out.println("ERROR: Unable to instantiate navX" + ex.getMessage());
    }
  }

  /**
   * Method to log information to the Smart Dashboard.
   */
  public void logDataToSmartDashboard() {
    /*
     * SmartDashboard.putNumber("Front Left RPM reading:" + frontLeftRPM);
     * SmartDashboard.putNumber("Back Left RPM reading:" + backLeftRPM);
     * SmartDashboard.putNumber("Front Right RPM reading:" + frontRightRPM);
     * SmartDashboard.putNumber("Back Right RPM reading:" + backRightRPM);
     */
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
  public void driveCartesian(double y, double x, double z) {
    // Sets up the cartesian drive for the drive subsystem
    //RobotContainer.driveSubsystem.mecanumDrive.driveCartesian(OI.joystick.getY() * -1.0, OI.joystick.getX(),OI.joystick.getZ());

    // double deadband = 0.001;

    // if(y < deadband)
    //   y = 0.0;
    // if(x < deadband)
    //   x = 0.0;
    // if(z < deadband)
    //   z = 0.0;

    RobotContainer.driveSubsystem.mecanumDrive.driveCartesian(y, x, z);
  }

  /**
   * Method to set the drivetrain motors to factory defaults and to invert the
   * right side.
   */
  private void configureMotors() {
    frontLeft.configFactoryDefault();
    backLeft.configFactoryDefault();
    frontRight.configFactoryDefault();
    backRight.configFactoryDefault();

    frontLeft.setInverted(false);
    backLeft.setInverted(false);
    frontRight.setInverted(true);
    backRight.setInverted(true);
  }
}
