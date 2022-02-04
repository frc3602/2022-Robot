// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.RobotContainer;

public class DriveSubsystem extends SubsystemBase {
  public AHRS ahrs;
  public MecanumDrive mecanumDrive;

  public DriveSubsystem() {
    WPI_TalonFX frontLeft = new WPI_TalonFX(Constants.driveFrontLeftCANID);
    WPI_TalonFX backLeft = new WPI_TalonFX(Constants.driveBackLeftCANID);
    WPI_TalonFX frontRight = new WPI_TalonFX(Constants.driveFrontRightCANID);
    WPI_TalonFX backRight = new WPI_TalonFX(Constants.driveBackRightCANID);

    // Invert the right side motors.
    frontRight.setInverted(true);
    backRight.setInverted(true);

    mecanumDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

    try {
       ahrs = new AHRS(SPI.Port.kMXP);
    }
    catch (RuntimeException ex) {
      System.out.println("Error instantiating navX-MXP:  " + ex.getMessage());
    }
  }

  public void ResetGyro() {
    ahrs.reset();
  }

  public double GetGyroAngle() {
    return ahrs.getAngle();
  }

  public void DriveCartesian() {
    double rawThrottle = OI.joystick.getRawAxis(3) * -1.0;
    double throttle = (((rawThrottle + 1.0) /2.0) * 0.6 ) + 0.4;
    double gyroAngle = 0.0;

    RobotContainer.m_driveSubsystem.mecanumDrive.driveCartesian(
      OI.joystick.getY() * throttle,
      OI.joystick.getX() * throttle,
      OI.joystick.getZ() * throttle * -1.0,
      gyroAngle);
  }

  public void DriveBackwards() {
    RobotContainer.m_driveSubsystem.mecanumDrive.driveCartesian(0.0,0.5,0.0);
  }

  @Override
  public void periodic() {

  }

  @Override
  public void simulationPeriodic() {

  }
}
