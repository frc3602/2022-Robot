/* Copyright (C) 2022 Team 3602. All rights reserved. */
/* This work is licensed under the terms of the MIT license */
/* found in the root directory of this project. */

package com.team3602.robot;

public interface Constants {

  public interface Drivetrain {
    // Drivetrain CAN IDs
    int driveFrontLeftCANID = 0;
    int driveBackLeftCANID = 1;
    int driveFrontRightCANID = 2;
    int driveBackRightCANID = 3;
  }

  public interface Climber {
    // Climber CAN IDs
    int climbSupportOneLeftCANID = 4;
    int climbArmOneLeftCANID = 5;
    int climbSupportOneRightCANID = 6;
    int climbArmOneRightCANID = 7;

    int climbSupportTwoLeftCANID = 8;
    int climbArmTwoLeftCANID = 9;
    int climbSupportTwoRightCANID = 10;
    int climbArmTwoRightCANID = 11;
  }

  public interface Shooter {
    // Shooter CAN IDs
    int shooterMotorCANID = 12;

    // Shooter gear ratio
    double shooterGearRatio = (0 / 0);

    // Limelight information
    double limelightHeight = 25; // Height of the limelight from ground
    double limelightAngleCorrection = 10.6; // Angle of the limelight in relation to the ground
    double targetHeight = 104 - limelightHeight; // Height of the target from ground to top minus the height of the
                                                   // limelight on the robot
  }

  public interface Index {
    // Index CAN IDs
    int indexMotorTopCANID = 15;
    int indexMotorBottomCANID = 16;

    // Amount of maximum balls
    int maxBalls = 2;

    // Index motor speeds
    double motorTopSpeed = 1.0;
    double motorBottomSpeed = 1.0;

    // Sensor IDs
    int indexSensorTopID = 0;
    int indexSensorBottomID = 1;
  }

  public interface Controller {
    // Controller port IDs
    int joystickPort = 0;
    int xboxControllerPort = 1;

    // Xbox controller axes
    double leftStickX = OI.xboxController.getRawAxis(0) / 4;
    double leftStickY = OI.xboxController.getRawAxis(1) / 4;

    double rightStickX = OI.xboxController.getRawAxis(4) / 4;
    double rightStickY = OI.xboxController.getRawAxis(5) / 4;

    // Joystick buttons
    int indexInButton = 3;
    int indexOutButton = 5;
    int shooterButton = 1;
  }
}
