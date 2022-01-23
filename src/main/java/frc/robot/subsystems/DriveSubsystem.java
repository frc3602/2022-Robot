// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  public DriveSubsystem() {
    WPI_TalonSRX frontLeft = new WPI_TalonSRX(Constants.driveFrontLeftCANID);
    WPI_TalonSRX backLeft = new WPI_TalonSRX(Constants.driveBackLeftCANID);
   
    WPI_TalonSRX frontRight = new WPI_TalonSRX(Constants.driveFrontRightCANID);
    WPI_TalonSRX backRight = new WPI_TalonSRX(Constants.driveBackRightCANID);

    MecanumDrive m_DriveSubsystem = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

    frontRight.setInverted(true);
    backRight.setInverted(true);
  }

  @Override
  public void periodic() {

  }

  @Override
  public void simulationPeriodic() {

  }
}
