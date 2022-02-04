// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {
  // Drivetrain CAN IDs
  public static int driveFrontLeftCANID = 1;
  public static int driveBackLeftCANID = 0;
  public static int driveFrontRightCANID = 3;
  public static int driveBackRightCANID = 4;

  // Climber CAN IDs
  public static int climbSupportLeftCANID = 2;
  public static int climbArmLeftCANID = 3;
  public static int climbSupportRightCANID = 4;
  public static int climbArmRightCANID = 1;

  // Shooter CAN IDs
  public static int shooterMotorTopCANID = 4;
  public static int shooterMotorBottomCANID = 1;

  // Intake CAN IDs
  public static int intakeMotorCANID = 2;

  // Index CAN IDs
  public static int indexMotorTopCANID = 2;
  public static int indexMotorBottomCANID = 2;
  public static int indexMotorExtraCANID = 2;

  //Controller
  public static int joystickPort = 0;
}
