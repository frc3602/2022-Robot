// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.RobotContainer;

public class DriveSubsystem extends SubsystemBase {
  // NavX for Gyro
  private AHRS navX;

  // MecanumDrive Information
  private MecanumDrive mecanumDrive;

  public DriveSubsystem() {
    // Creates the motors & controllers and sets the CAN IDs for each one
    WPI_TalonFX frontLeft = new WPI_TalonFX(Constants.driveFrontLeftCANID);
    WPI_TalonFX backLeft = new WPI_TalonFX(Constants.driveBackLeftCANID);
    WPI_TalonFX frontRight = new WPI_TalonFX(Constants.driveFrontRightCANID);
    WPI_TalonFX backRight = new WPI_TalonFX(Constants.driveBackRightCANID);

    // Invert the right side motors.
    frontRight.setInverted(true);
    backRight.setInverted(true);
    
    // Creates a new mecanum drive and sets the motors for it
    mecanumDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

    // Initialize NavX and check to make sure its working
    try {
      navX = new AHRS(SPI.Port.kMXP);
    }
    catch (RuntimeException ex) {
      System.out.println("Error instantiating navX-MXP:  " + ex.getMessage());
    }
  }
  
/*
  // Smart Dashboard Information
  public void LogDataToSmartDashboard() {
    SmartDashboard.putNumber("Front Left RPM reading:", frontLeftRPM);
    SmartDashboard.putNumber("Back Left RPM reading:", backLeftRPM);
    SmartDashboard.putNumber("Front Right RPM reading:", frontRightRPM);
    SmartDashboard.putNumber("Back Right RPM reading:", backRightRPM);
  }
*/

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
    // Sets up all the throttle stuff
    // double rawThrottle = OI.joystick.getRawAxis(3) * -1.0;
    // double throttle = (((rawThrottle + 1.0) / 2.0) * 0.6 ) + 0.4;
    // double gyroAngle = 0.0;

    // Sets up the cartesian drive for the drive subsystem
    RobotContainer.m_driveSubsystem.mecanumDrive.driveCartesian(
      -OI.joystick.getY(),
      OI.joystick.getX(),
      OI.joystick.getZ());
  }

  // Creates the method to allow the robot to drive backwards
  // public void DriveBackwards() {
  //   RobotContainer.m_driveSubsystem.mecanumDrive.driveCartesian(0.0,-0.5,0.0);
  // }

  @Override
  public void periodic() {

  }
  
}
