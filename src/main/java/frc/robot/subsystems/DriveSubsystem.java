// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.RobotMap;


public class DriveSubsystem extends SubsystemBase {
    public DriveSubsystem() {
        WPI_TalonSRX frontLeft = new WPI_TalonSRX(RobotMap.driveFrontLeftCANID);
        WPI_TalonSRX backLeft = new WPI_TalonSRX(RobotMap.driveBackLeftCANID);
       
        WPI_TalonSRX frontRight = new WPI_TalonSRX(RobotMap.driveFrontRightCANID);
        WPI_TalonSRX backRight = new WPI_TalonSRX(RobotMap.driveBackRightCANID);
    
        MecanumDrive myDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
    
        frontRight.setInverted(true);
        backRight.setInverted(true);
    }
}
