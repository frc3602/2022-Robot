// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public interface Constants {

  public interface Drivetrain {
    // Drivetrain CAN IDs
    int driveFrontLeftCANID = 1;
    int driveBackLeftCANID = 0;
    int driveFrontRightCANID = 2;
    int driveBackRightCANID = 3;
  }

  public interface Climber {
    // Climber CAN IDs
    int climbSupportLeftCANID = 4;
    int climbArmLeftCANID = 5;
    int climbSupportRightCANID = 6;
    int climbArmRightCANID = 7;
  }

  public interface Shooter {
    // Shooter CAN IDs
    int shooterMotorTopCANID = 8;
    int shooterMotorBottomCANID = 9;
  }

  public interface Intake {
    // Intake CAN IDs
    int intakeSpinMotorCANID = 10;
    int intakePivotMotorCANID = 11;
  }

  public interface Index {
    // Index CAN IDs
    int indexMotorTopCANID = 12;
    int indexMotorBottomCANID = 13;
    int indexMotorExtraCANID = 14;
  }

  public interface Controller {
    // Controller Port IDs
    int joystickPort = 0;
    int xboxControllerPort = 1;

    // Xbox controller axes
    int povUp = 0;
    int povRight = 90;
    int povLeft = 270;
    int povDown = 180;
  }

}
