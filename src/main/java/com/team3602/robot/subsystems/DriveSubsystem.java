/* Copyright (C) 2022 Team 3602. All rights reserved. */
/* This work is licensed under the terms of the MIT license */
/* found in the root directory of this project. */

package com.team3602.robot.subsystems;

// Phoenix & navX Imports
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.kauailabs.navx.frc.AHRS;
import com.team3602.robot.OI;
import com.team3602.robot.RobotContainer;
import com.team3602.robot.Constants.Drivetrain;

// WPILib Imports
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.SPI;

public class DriveSubsystem extends SubsystemBase {
  // NavX for Gyro
  private AHRS navX;

  // MecanumDrive Information
  private MecanumDrive mecanumDrive;

  public DriveSubsystem() {
    // Creates the motors & controllers and sets the CAN IDs for each one
    WPI_TalonFX frontLeft = new WPI_TalonFX(Drivetrain.driveFrontLeftCANID);
    WPI_TalonFX backLeft = new WPI_TalonFX(Drivetrain.driveBackLeftCANID);
    WPI_TalonFX frontRight = new WPI_TalonFX(Drivetrain.driveFrontRightCANID);
    WPI_TalonFX backRight = new WPI_TalonFX(Drivetrain.driveBackRightCANID);

    // Sets the motors to default configuration
    frontLeft.configFactoryDefault();
    backLeft.configFactoryDefault();
    frontRight.configFactoryDefault();
    backRight.configFactoryDefault();

    // Invert the right side motors.
    frontRight.setInverted(true);
    backRight.setInverted(true);

    // Creates a new mecanum drive and sets the motors for it
    mecanumDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

    // Initialize NavX and check to make sure its working
    try {
      navX = new AHRS(SPI.Port.kMXP);
    } catch (RuntimeException ex) {
      System.out.println("ERROR: Unable to instantiate navX" + ex.getMessage());
    }
  }

  // Smart Dashboard information
  public void LogDataToSmartDashboard() {
    /*
     * SmartDashboard.putNumber("Front Left RPM reading:", frontLeftRPM);
     * SmartDashboard.putNumber("Back Left RPM reading:", backLeftRPM);
     * SmartDashboard.putNumber("Front Right RPM reading:", frontRightRPM);
     * SmartDashboard.putNumber("Back Right RPM reading:", backRightRPM);
     */
  }

  // Gets current angle of the robot as a double
  public double GetGyroAngle() {
    return navX.getAngle();
  }

  // Resets the NavX
  public void ResetGyro() {
    navX.reset();
  }

  // Creates the method to drive the drive subsystem
  public void DriveCartesian() {
    // Sets up the cartesian drive for the drive subsystem
    RobotContainer.driveSubsystem.mecanumDrive.driveCartesian(
        -OI.joystick.getY(),
        OI.joystick.getX(),
        OI.joystick.getZ());
  }

  @Override
  public void periodic() {
  }
}
