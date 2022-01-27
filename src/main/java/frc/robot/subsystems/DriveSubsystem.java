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

public class DriveSubsystem extends SubsystemBase {
  public AHRS ahrs;

  public DriveSubsystem() {
    WPI_TalonFX frontLeft = new WPI_TalonFX(Constants.driveFrontLeftCANID);
    WPI_TalonFX backLeft = new WPI_TalonFX(Constants.driveBackLeftCANID);
    WPI_TalonFX frontRight = new WPI_TalonFX(Constants.driveFrontRightCANID);
    WPI_TalonFX backRight = new WPI_TalonFX(Constants.driveBackRightCANID);

    // Invert the right side motors.
    frontRight.setInverted(true);
    backRight.setInverted(true);

    MecanumDrive mecanumDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

    try {
       AHRS ahrs = new AHRS(SPI.Port.kMXP);
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

  @Override
  public void periodic() {

  }

  @Override
  public void simulationPeriodic() {

  }
}
