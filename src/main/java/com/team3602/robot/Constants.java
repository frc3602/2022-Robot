/*
 * @(#)Constants.java        1.0 22/03/04
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

  //boolean testingEnabled = true;

    	/**
	 * Which PID slot to pull gains from. Starting 2018, you can choose from
	 * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
	 * configuration.
	 */
	public static final int kSlotIdx = 0;

	/*
	 * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
	 * now we just want the primary one.
	 */
	public static final int kPIDLoopIdx = 0;

	/*
	 * set to zero to skip waiting for confirmation, set to nonzero to wait and
	 * report to DS if action fails.
	 */
  public static final int kTimeoutMs = 10;

  public static final double falconTicksPerRotation = 2048.0;

  int powerDistributionHubCANID = 17;

  /**
   * The Drivetrain interface provides drive subsystem CAN IDs.
   */
  public interface Drivetrain {
    int driveFrontLeftCANID = 20;
    int driveBackLeftCANID = 1;
    int driveFrontRightCANID = 2;
    int driveBackRightCANID = 3;

    double driveRatio = 14.0/69.0;

    double driveInverseRatio = 1.0 / driveRatio;

    double distancePerWheelRev = 4.0 * Math.PI;
    double distancePerMotorRev = distancePerWheelRev * driveRatio;

  }

  /**
   * The Climber interface provides climber subsystem CAN IDs.
   */
  public interface Climber {
    int climbPivotInnerLeftCANID = 4;
    int climbExtendInnerLeftCANID = 5;
    int climbPivotInnerRightCANID = 6;
    int climbExtendInnerRightCANID = 7;

    int climbPivotOuterLeftCANID = 8;
    int climbExtendOuterLeftCANID = 9;
    int climbPivotOuterRightCANID = 10;
    int climbExtendOuterRightCANID = 11;


    //double pivotSoftLimitTicks = 23893; // 10 degrees
    double pivotSoftLimitTicks = 71000; // 30 degrees
    //double extendSoftLimitTicks = 97784; // 15 inches
    //double extendSoftLimitTicks = 150012; // 23 inches
    double extendSoftLimitTicks = 195668; // 30 inches


    //double pivotPlanetaryRatio = 1.0/60;
    double pivotPlanetaryRatio = 1.0/20;
    //double pivotChainRatio = 12.0/26.0;
    double pivotChainRatio = 12.0/54.0; //big sprockets

    double pivotGearsRatio = 20.0/64.0;

    double pivotTotalRatio = pivotPlanetaryRatio * pivotGearsRatio * pivotChainRatio;

    double pivotInverseTotalRatio = 1.0 / pivotTotalRatio;


    double extendPlanetaryRatio = 1.0/20.0;
    double extendInversePlanetaryRatio = 20.0/1.0;
    double extendDrumDiameter = 2.0;
    double extendRotationsToInches = Math.PI * extendDrumDiameter;

    public enum ClimbStageEnum
    {
      notReady,
      ready,
      climbMidBar,
      hookHighBar,
      ClimbHighBar,
      readyHighBar,
      hookTravers,
      climbTraverse
    }
  }

  /**
   * The Shooter interface provides shooter subsystem CAN IDs, gear ratios, and
   * limelight information.
   */
  public interface Shooter {
    int shooterMotorCANID = 12;

    public static double shooterGearRatio = (48.0/32.0);

    double limelightHeight = 25.5; // Height of the limelight from ground
    double limelightAngleCorrection = 34.0; // Angle of the limelight in relation to the ground
    double targetHeight = 102.0 - limelightHeight; // Height of the target from ground to top minus the height of the
                                                 // limelight on the robot

    //public static final double defaultShooterRPM = 2500.0;
    //public static final double defaultShooterRPM = 3500.0;

    public static final double rotationalErrorTolerance = 2.0f; // 0.5 degree error tolerance 
    public static final double shooterSpeedErrorTolerance = 175.1f;
                                               
  }

  /**
   * The Index interface provides index subsystem CAN IDs, maximum balls, motor
   * speeds, and sensor IDs.
   */
  public interface Index {
    int indexMotorTopCANID = 15;
    int indexMotorBottomCANID = 16;

    int indexLiftCANID = 25;

    int maxBalls = 2;

    double motorTopSpeed = 0.5;
    double motorBottomSpeed = 0.5;

    int indexSensorTopID = 1;
    int indexSensorBottomID = 0;
  }

  public interface LEDColorStrip
  {
    public static int ledPWMPort = 0;
    public static int ledSegmentLength = 72;
    public static int ledSegmentSections = 2;
    public static int ledDeadSpace = 0;
    public static int repeatSegmentCount = 4;


    public static double brightnessPercentage = 0.2;
  }

  public interface PixeyCam
  {
    public static final int Signature_RED_BALL = 2;
    public static final int Signature_BLUE_BALL = 1;

    public static final double targetX = 315.0 / 2.0;
  }

  /**
   * The Controller interface provides controller port IDs, xbox controller axes,
   * and button IDs.
   */
  public interface Controller {
    int joystickPort = 0;
    int xboxControllerPort = 1;


    int indexInButton = 1;
    int indexOutButton = 3;
    int shooterButton = 2;
  }
}
