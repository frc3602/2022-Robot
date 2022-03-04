/*
 * @(#)Constants.java        1.0 22/03/03
 *
 * Copyright (c) 2022 Team 3602
 * 500 S Lincoln Rd, Escanaba, Michigan, 49829, U.S.A.
 * All rights reserved.
 *
 * This software is licensed under the terms of the GPLv3 license
 * found in the root directory of this project.
 */

package com.team3602.robot;

/**
 * The Constants interface provides globally-accessible robot constants.
 *
 * @version 1.0 04 Mar 2022
 * @author Cody Wellman
 */
public interface Constants {

  /**
   * The Drivetrain interface provides drive subsystem CAN IDs.
   */
  public interface Drivetrain {
    int driveFrontLeftCANID = 0;
    int driveBackLeftCANID = 1;
    int driveFrontRightCANID = 2;
    int driveBackRightCANID = 3;
  }

  /**
   * The Climber interface provides climber subsystem CAN IDs.
   */
  public interface Climber {
    int climbSupportOneLeftCANID = 4;
    int climbArmOneLeftCANID = 5;
    int climbSupportOneRightCANID = 6;
    int climbArmOneRightCANID = 7;

    int climbSupportTwoLeftCANID = 8;
    int climbArmTwoLeftCANID = 9;
    int climbSupportTwoRightCANID = 10;
    int climbArmTwoRightCANID = 11;
  }

  /**
   * The Shooter interface provides shooter subsystem CAN IDs, gear ratios, and
   * limelight information.
   */
  public interface Shooter {
    int shooterMotorCANID = 12;

    double shooterGearRatio = (0 / 0);

    double limelightHeight = 25; // Height of the limelight from ground
    double limelightAngleCorrection = 27; // Angle of the limelight in relation to the ground
    double targetHeight = 104 - limelightHeight; // Height of the target from ground to top minus the height of the
                                                 // limelight on the robot
  }

  /**
   * The Index interface provides index subsystem CAN IDs, maximum balls, motor
   * speeds, and sensor IDs.
   */
  public interface Index {
    int indexMotorTopCANID = 15;
    int indexMotorBottomCANID = 16;

    int maxBalls = 2;

    double motorTopSpeed = 0.5;
    double motorBottomSpeed = 0.5;

    int indexSensorTopID = 0;
    int indexSensorBottomID = 1;
  }

  /**
   * The Controller interface provides controller port IDs, xbox controller axes,
   * and button IDs.
   */
  public interface Controller {
    int joystickPort = 0;
    int xboxControllerPort = 1;

    double leftStickX = OI.xboxController.getRawAxis(0) / 4;
    double leftStickY = OI.xboxController.getRawAxis(1) / 4;
    double rightStickX = OI.xboxController.getRawAxis(4) / 4;
    double rightStickY = OI.xboxController.getRawAxis(5) / 4;

    int indexInButton = 3;
    int indexOutButton = 5;
    int shooterButton = 1;
  }
}
