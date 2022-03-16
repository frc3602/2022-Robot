/*
 * @(#)ShooterSubsystem.java        1.0 22/03/04
 *
 * Copyright (c) 2022 Team 3602
 * 500 S Lincoln Rd, Escanaba, Michigan, 49829, U.S.A.
 * All rights reserved.
 *
 * This software is licensed under the terms of the GPLv3 license
 * found in the root directory of this project.
 */

package com.team3602.robot.subsystems;

import com.team3602.robot.Constants;
import com.team3602.robot.RobotContainer;
import com.team3602.robot.Constants.Shooter;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// WPILib Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The ShooterSubsystem class provides all methods and functionality of the
 * robot's shooter.
 *
 * @version 1.0 04 Mar 2022
 * @author Cody Wellman
 */
public class ShooterSubsystem extends SubsystemBase {
  // Creates the motors & controllers and sets the CAN IDs for each one
  WPI_TalonFX shooterMotor = new WPI_TalonFX(Shooter.shooterMotorCANID);

  // SparkMaxPIDController shooterPIDController = shooterMotor.getPIDController();
  
  // private RelativeEncoder shooterEncoder = shooterMotor.getEncoder();

  int count = 0;
  double targetShooterMotorRPM = 0.0;

  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;

  /**
   * Constructor for {@link ShooterSubsystem} class to run the
   * {@link #configureMotors()} method.
   */
  public ShooterSubsystem() {
    //configureMotors();
  }

  /**
   * Method to log information to the Smart Dashboard.
   */
  public void logDataToSmartDashboard() {
  }

  public void SetTestMotorSpeed(double percent)
  {

  }

  /**
   * Method to stop the shooter motor.
   */
  public void stopMotor() {
    shooterMotor.set(0.0);
  }

  /**
   * Method to set the rpm of the shooter.
   * 
   * @param rpm the rpm of the shooter
   */
  public void setShooterMotorRPM(double rpm) {
    System.out.println("SetShooterMotorRPM " + rpm);
    targetShooterMotorRPM = rpm;
  }

  /**
   * Method to get the speed of the shooter motor.
   */
  public void getShooterMotorSpeed() {
  }

  private double Speed2RPM(double speed_)
    {
    double rpm = 0.0;

    rpm = speed_ * (600.0 / 2048.0) * Shooter.shooterGearRatio;

    return rpm;
    }
    
  private double RPM2Speed(double rpm_)
    {
    double speed = 0.0;

    speed = (rpm_ * ( 2048.0 / 600.0)) / Shooter.shooterGearRatio;

    return speed;
    }

  public boolean IsShooterSpeedOnTarget()
  {
    double delta = Math.abs(Speed2RPM(shooterMotor.getSelectedSensorVelocity()) - targetShooterMotorRPM);

    System.out.println("IsShooterSpeedOnTarget Delta: " + delta);

    return (Math.abs(delta) < 25.0);
  }

  /**
   * Method to update the speed of the shooter motor.
   */
  public void updateShooterMotorSpeed() {


    // double p = SmartDashboard.getNumber("P Gain", 0);
    // double i = SmartDashboard.getNumber("I Gain", 0);
    // double d = SmartDashboard.getNumber("D Gain", 0);
    // double iz = SmartDashboard.getNumber("I Zone", 0);
    // double ff = SmartDashboard.getNumber("Feed Forward", 0);
    // double max = SmartDashboard.getNumber("Max Output", 0);
    // double min = SmartDashboard.getNumber("Min Output", 0);

    // if PID coefficients on SmartDashboard have changed, write new values to controller
    // if((p != kP)) { shooterPIDController.setP(p); kP = p; }
    // if((i != kI)) { shooterPIDController.setI(i); kI = i; }
    // if((d != kD)) { shooterPIDController.setD(d); kD = d; }
    // if((iz != kIz)) { shooterPIDController.setIZone(iz); kIz = iz; }
    // if((ff != kFF)) { shooterPIDController.setFF(ff); kFF = ff; }
    // if((max != kMaxOutput) || (min != kMinOutput)) { 
    //   shooterPIDController.setOutputRange(min, max); 
    //   kMinOutput = min; kMaxOutput = max; 
    // }

    /**
     * PIDController objects are commanded to a set point using the 
     * SetReference() method.
     * 
     * The first parameter is the value of the set point, whose units vary
     * depending on the control type set in the second parameter.
     * 
     * The second parameter is the control type can be set to one of four 
     * parameters:
     *  com.revrobotics.CANSparkMax.ControlType.kDutyCycle
     *  com.revrobotics.CANSparkMax.ControlType.kPosition
     *  com.revrobotics.CANSparkMax.ControlType.kVelocity
     *  com.revrobotics.CANSparkMax.ControlType.kVoltage
     */
    // double setPoint = m_stick.getY()*maxRPM;
    // shooterPIDController.setReference(targetShooterMotorRPM, CANSparkMax.ControlType.kVelocity);

    SmartDashboard.putNumber("SetPoint Ticks",  RPM2Speed(targetShooterMotorRPM));

    shooterMotor.set(TalonFXControlMode.Velocity, RPM2Speed(targetShooterMotorRPM));
    
    SmartDashboard.putNumber("SetPoint", targetShooterMotorRPM);
    SmartDashboard.putNumber("ProcessVariable", shooterMotor.getSelectedSensorVelocity());


  }

  /**
   * Method to calculate the distance from the limelight to the target taking in
   * account of the angle that the limelight is at.
   * 
   * @return the calculated double for the shooter
   */
  public double calculateDistance() {
    double a = RobotContainer.visionSubsystem.getTY() + Shooter.limelightAngleCorrection;
    double answer = Shooter.targetHeight / (Math.tan(Math.toRadians(a)));

    return answer;
  }

  /**
   * Method to caclulate and set the shooter motor speeds accordingly.
   * 
   * @return if there is a valid target
   */
  public void calculateAndSetMotorSpeeds()
  {
    if (RobotContainer.visionSubsystem.noValidTarget())
    {
      return;
    }

    double distance = calculateDistance();

    System.out.println("CalculateAndSetMotorSpeeds Distance: " + distance);

  //some magic decimal crazyness going on
  RobotContainer.shooterSubsystem.targetShooterMotorRPM = CalculateMagicMath(distance);

    double newTargetShooterMotorRPM = RobotContainer.shooterSubsystem.targetShooterMotorRPM;

    setShooterMotorRPM(newTargetShooterMotorRPM);
    logDataToSmartDashboard();
  }

  private double CalculateMagicMath(double distance)
  {
  double ret = 0.0;

  if(distance <= 5 )
  {
    ret = 100.0;
  }
  else if(distance <= 10 )
  {
    ret = 100.0;
  }
  else
  {
    ret = 250.0;
  }

  return ret;
  }

  /**
   * Method to set the shooter motor to factory defaults and coast mode.
   */
  public void InitShooter()
  {
    shooterMotor.configFactoryDefault();

    /* Config sensor used for Primary PID [Velocity] */
    shooterMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor,
    Constants.kPIDLoopIdx, 
    Constants.kTimeoutMs);

    shooterMotor.setNeutralMode(NeutralMode.Coast);      
        /**
     * Phase sensor accordingly. 
         * Positive Sensor Reading should match Green (blinking) Leds on Talon
         */
        //shooterMotor.setSensorPhase(true);
    // topShooter.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_10Ms, RobotMap.kTimeoutMs);

    shooterMotor.setInverted(TalonFXInvertType.Clockwise);

    /* Config the peak and nominal outputs */
    shooterMotor.configNominalOutputForward(0, Constants.kTimeoutMs);
    shooterMotor.configNominalOutputReverse(0, Constants.kTimeoutMs);
    shooterMotor.configPeakOutputForward(1, Constants.kTimeoutMs);
    shooterMotor.configPeakOutputReverse(-1, Constants.kTimeoutMs);

    /* Config the Velocity closed loop gains in slot0 */
    shooterMotor.config_kF(Constants.kPIDLoopIdx, RobotContainer.kGains_Velocit.kF, Constants.kTimeoutMs);
    shooterMotor.config_kP(Constants.kPIDLoopIdx, RobotContainer.kGains_Velocit.kP, Constants.kTimeoutMs);
    shooterMotor.config_kI(Constants.kPIDLoopIdx, RobotContainer.kGains_Velocit.kI, Constants.kTimeoutMs);
    shooterMotor.config_kD(Constants.kPIDLoopIdx, RobotContainer.kGains_Velocit.kD, Constants.kTimeoutMs);
  }

  /**
   * Method to set the shooter motor to 75% for testing.
   */
  // public void shootStuff() {
  //   shooterMotor.set(-0.75);
  // }
}
