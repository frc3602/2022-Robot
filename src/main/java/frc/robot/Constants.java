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
    int shooterMotorTopCANID = 12;
    int shooterMotorBottomCANID = 13;
  }

  public interface Intake {
    // Intake CAN IDs
    int intakeSpinMotorCANID = 14;
    int intakePivotMotorCANID = 15;
  }

  public interface Index {
    // Index CAN IDs
    int indexMotorTopCANID = 16;
    int indexMotorBottomCANID = 17;
    int indexMotorExtraCANID = 18;
  }

  public interface Controller {
    // Controller Port IDs
    int joystickPort = 0;
    int xboxControllerPort = 1;

    // Xbox Controller Axes
    int povUp = 0;
    int povRight = 90;
    int povLeft = 270;
    int povDown = 180;
  }

}
