/*
 * @(#)ClimberSubsystem.java        1.0 22/03/04
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
import com.team3602.robot.Constants.Climber;
import com.team3602.robot.Constants.Controller;

// Phoenix Imports
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

// WPILib Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The ClimberSubsystem class provides all methods and functionality of the
 * robot's climber.
 *
 * @version 1.0 04 Mar 2022
 * @author Cody Wellman
 */
public class ClimberSubsystem extends SubsystemBase {
  // Creates the motors & controllers and sets the CAN IDs for each one
  WPI_TalonFX pivotInnerLeft = new WPI_TalonFX(Climber.climbPivotInnerLeftCANID);
  WPI_TalonFX extendInnerLeft = new WPI_TalonFX(Climber.climbExtendInnerLeftCANID);
  WPI_TalonFX pivotInnerRight = new WPI_TalonFX(Climber.climbPivotInnerRightCANID);
  WPI_TalonFX extendInnerRight = new WPI_TalonFX(Climber.climbExtendInnerRightCANID);

  WPI_TalonFX pivotOuterLeft = new WPI_TalonFX(Climber.climbPivotOuterLeftCANID);
  WPI_TalonFX extendOuterLeft = new WPI_TalonFX(Climber.climbExtendOuterLeftCANID);
  WPI_TalonFX pivotOuterRight = new WPI_TalonFX(Climber.climbPivotOuterRightCANID);
  WPI_TalonFX extendOuterRight = new WPI_TalonFX(Climber.climbExtendOuterRightCANID);

  public ClimberSubsystem() {
    configureMotors();
  }

  public void Pivot(WPI_TalonFX motor1, WPI_TalonFX motor2, double ticks)
    {
      motor1.set(TalonFXControlMode.MotionMagic,  ticks);
      motor2.set(TalonFXControlMode.MotionMagic,  ticks);
    }

  public void Extend(WPI_TalonFX motor1, WPI_TalonFX motor2, double ticks)
    {
      motor1.set(TalonFXControlMode.MotionMagic,  ticks);
      motor2.set(TalonFXControlMode.MotionMagic,  ticks);
    }


  private void configurePivotMotor(WPI_TalonFX motor) {

    motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
      // motor.setSensorPhase(true);
    // motor.setInverted(false);
    /* Set relevant frame periods to be at least as fast as periodic rate*/
    motor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);

    motor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);
    /* set the peak and nominal outputs */
    motor.configNominalOutputForward(0, Constants.kTimeoutMs);
    motor.configNominalOutputReverse(0, Constants.kTimeoutMs);
    motor.configPeakOutputForward(1, Constants.kTimeoutMs);
    motor.configPeakOutputReverse(-1, Constants.kTimeoutMs);
    // motor.configPeakOutputForward(0.5, Constants.kTimeoutMs);
    // motor.configPeakOutputReverse(-0.5, Constants.kTimeoutMs);

    // motor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, Constants.kTimeoutMs);
    // motor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, Constants.kTimeoutMs);
    
    /* set closed loop gains in slot0 - see documentation */
    motor.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
    motor.config_kF(0, (1023.0/1200), Constants.kTimeoutMs);
    motor.config_kP(0, 0.9, Constants.kTimeoutMs);
    motor.config_kI(0, 0, Constants.kTimeoutMs);
    motor.config_kD(0, 0, Constants.kTimeoutMs);
    /* set acceleration and vcruise velocity - see documentation */
    // motor.configMotionCruiseVelocity(1150, Constants.kTimeoutMs);
    // motor.configMotionAcceleration(990, Constants.kTimeoutMs);
    motor.configMotionCruiseVelocity(1800, Constants.kTimeoutMs);
    motor.configMotionAcceleration(1500, Constants.kTimeoutMs);

    motor.configForwardSoftLimitThreshold(Climber.pivotSoftLimitTicks);
    motor.configReverseSoftLimitThreshold(Climber.pivotSoftLimitTicks * -1.0);
    
    // motor.current(40, Constants.kTimeoutMs);
    // motor.configContinuousCurrentLimit(40, Constants.kTimeoutMs);

  }

  private void configureExtendMotor(WPI_TalonFX motor) {

    motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
      // motor.setSensorPhase(true);
    // motor.setInverted(false);
    /* Set relevant frame periods to be at least as fast as periodic rate*/
    motor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);

    motor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);
    /* set the peak and nominal outputs */
    motor.configNominalOutputForward(0, Constants.kTimeoutMs);
    motor.configNominalOutputReverse(0, Constants.kTimeoutMs);
    motor.configPeakOutputForward(1, Constants.kTimeoutMs);
    motor.configPeakOutputReverse(-1, Constants.kTimeoutMs);
    // motor.configPeakOutputForward(0.5, Constants.kTimeoutMs);
    // motor.configPeakOutputReverse(-0.5, Constants.kTimeoutMs);

    // motor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, Constants.kTimeoutMs);
    // motor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, Constants.kTimeoutMs);
    
    /* set closed loop gains in slot0 - see documentation */
    motor.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
    motor.config_kF(0, (1023.0/1200), Constants.kTimeoutMs);
    motor.config_kP(0, 0.9, Constants.kTimeoutMs);
    motor.config_kI(0, 0, Constants.kTimeoutMs);
    motor.config_kD(0, 0, Constants.kTimeoutMs);
    /* set acceleration and vcruise velocity - see documentation */
    // motor.configMotionCruiseVelocity(1150, Constants.kTimeoutMs);
    // motor.configMotionAcceleration(990, Constants.kTimeoutMs);
    motor.configMotionCruiseVelocity(1800, Constants.kTimeoutMs);
    motor.configMotionAcceleration(1500, Constants.kTimeoutMs);

    motor.configForwardSoftLimitThreshold(Climber.extendSoftLimitTicks);
    motor.configReverseSoftLimitThreshold(0.0);

  }

  private void configureMotors()
  {
    configurePivotMotor(pivotInnerLeft);
    configurePivotMotor(pivotInnerRight);
    configurePivotMotor(pivotOuterLeft);
    configurePivotMotor(pivotOuterRight);

    configureExtendMotor(extendInnerLeft);
    configureExtendMotor(extendInnerRight);
    configureExtendMotor(extendOuterLeft);
    configureExtendMotor(extendOuterRight);

    // supportOuterRight.setInverted(true);
    // supportInnerRight.setInverted(true);
  }
}
