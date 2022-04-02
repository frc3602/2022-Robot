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
import com.team3602.robot.Constants.Climber.ClimbStageEnum;
import com.team3602.robot.commands.ClimberSetLocationCoordiatedCommandGroup;
import com.team3602.robot.commands.DummyTimerCommand;
import com.team3602.robot.commands.StageClimbFinishMoveCommand;
import com.team3602.robot.commands.StageClimbStartMoveCommand;
// Phoenix Imports
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
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

  private Climber.ClimbStageEnum currentStage = Climber.ClimbStageEnum.notReady;
  boolean isMoving = false;

  // private boolean startedClimb = false;

  // double maxVoltage = 0.0;
  // double maxVelocity = 0.0;

  public ClimberSubsystem() {
    configureMotors();
  }

  public boolean ClimberActive()
  {
    return currentStage != ClimbStageEnum.notReady;
  }

  public ClimbStageEnum CurrentStage()
  {
    return currentStage;
  }

  public void StartMove(ClimbStageEnum stage)
  {
    RobotContainer.ledSubsystem.StartMove(CurrentStage());
    isMoving = true;
  }

  public void FinishMove(ClimbStageEnum stage)
  {
    currentStage = stage;
    isMoving = false;
    RobotContainer.ledSubsystem.FinishMove(CurrentStage());
  }

  public void ResetEncoders()
  {
  if(Constants.testingEnabled)
    System.out.println("ClimberSubsystem ResetEncoders");
  pivotInnerLeft.setSelectedSensorPosition(0.0);
  extendInnerLeft.setSelectedSensorPosition(0.0); 
  pivotInnerRight.setSelectedSensorPosition(0.0); 
  extendInnerRight.setSelectedSensorPosition(0.0);
  pivotOuterLeft.setSelectedSensorPosition(0.0);
  extendOuterLeft.setSelectedSensorPosition(0.0); 
  pivotOuterRight.setSelectedSensorPosition(0.0); 
  extendOuterRight.setSelectedSensorPosition(0.0);
  }

  public void InitPositions()
  {
    ExtendInner(0.0);
    ExtendOuter(0.0);
    PivotInner(0.0);
    PivotOuter(0.0);

    currentStage = ClimbStageEnum.notReady;
  }

  public void BrakeAllTheMotors()
  {
    extendInnerLeft.setNeutralMode(NeutralMode.Brake); 
    extendInnerRight.setNeutralMode(NeutralMode.Brake); 
    extendOuterLeft.setNeutralMode(NeutralMode.Brake); 
    extendOuterRight.setNeutralMode(NeutralMode.Brake); 
  }


  public void ReoportStuff()
  {
    if(Constants.testingEnabled)
    {
    SmartDashboard.putNumber("pivotInnerLeft", pivotInnerLeft.getSelectedSensorPosition());
    SmartDashboard.putNumber("pivotInnerRight", pivotInnerRight.getSelectedSensorPosition());
    SmartDashboard.putNumber("pivotOuterLeft", pivotOuterLeft.getSelectedSensorPosition());
    SmartDashboard.putNumber("pivotOuterRight", pivotOuterRight.getSelectedSensorPosition());
    SmartDashboard.putNumber("extendInnerLeft", extendInnerLeft.getSelectedSensorPosition());
    SmartDashboard.putNumber("extendInnerRight", extendInnerRight.getSelectedSensorPosition());
    SmartDashboard.putNumber("extendOuterLeft", extendOuterLeft.getSelectedSensorPosition());
    SmartDashboard.putNumber("extendOuterRight", extendOuterRight.getSelectedSensorPosition());

    SmartDashboard.putNumber("GetCurrentOuterLeftLength", GetCurrentOuterLeftLength());
    SmartDashboard.putNumber("GetCurrentInnerLeftLength", GetCurrentInnerLeftLength());
    SmartDashboard.putNumber("GetCurrentOuterRightLength", GetCurrentOuterRightLength());
    SmartDashboard.putNumber("GetCurrentInnerRightLength", GetCurrentInnerRightLength());

    SmartDashboard.putNumber("GetCurrentOuterLeftAngle", GetCurrentOuterLeftAngle());
    SmartDashboard.putNumber("GetCurrentOuterRightAngle", GetCurrentOuterRightAngle());
    SmartDashboard.putNumber("GetCurrentInnerLeftAngle", GetCurrentInnerLeftAngle());
    SmartDashboard.putNumber("GetCurrentInnerRightAngle", GetCurrentInnerRightAngle());

    }

    SmartDashboard.putString("ClimberStage", currentStage.name());

  }

  public double GetCurrentOuterLeftLength()
  {
    return ExtendTicksToLength( extendOuterLeft.getSelectedSensorPosition(Constants.kPIDLoopIdx) ) ;

  }

  public double GetCurrentOuterRightLength()
  {
    return ExtendTicksToLength( extendOuterRight.getSelectedSensorPosition(Constants.kPIDLoopIdx) ) ;

  }

  public double GetCurrentInnerLeftLength()
  {
    return ExtendTicksToLength( extendInnerLeft.getSelectedSensorPosition(Constants.kPIDLoopIdx) ) ;

  }

  public double GetCurrentInnerRightLength()
  {
    return ExtendTicksToLength( extendInnerRight.getSelectedSensorPosition(Constants.kPIDLoopIdx) ) ;

  }

  public double GetCurrentOuterLeftAngle()
  {
    return PivotTicksToDegrees( pivotOuterLeft.getSelectedSensorPosition(Constants.kPIDLoopIdx) ) ;

  }
  public double GetCurrentOuterRightAngle()
  {
    return PivotTicksToDegrees( pivotOuterRight.getSelectedSensorPosition(Constants.kPIDLoopIdx) ) ;

  }
  public double GetCurrentInnerLeftAngle()
  {
    return PivotTicksToDegrees( pivotInnerLeft.getSelectedSensorPosition(Constants.kPIDLoopIdx) ) * -1.0;

  }
  public double GetCurrentInnerRightAngle()
  {
    return PivotTicksToDegrees( pivotInnerRight.getSelectedSensorPosition(Constants.kPIDLoopIdx) ) * -1.0 ;

  }



  private double PivotDegreesToTicks(double degrees)
    {
    double ticks = 0.0;

    ticks = Climber.pivotInverseTotalRatio * Constants.falconTicksPerRotation * (degrees / 360.0);

    return ticks;
    }

  private double PivotTicksToDegrees(double ticks)
    {
    double angle = 0.0;

    angle = (ticks / (Climber.pivotInverseTotalRatio * Constants.falconTicksPerRotation)) * 360.0 ;

    return angle;
    }

  private double ExtendLengthToTicks(double length)
    {
    double ticks = 0.0;

    ticks = Climber.extendInversePlanetaryRatio * Constants.falconTicksPerRotation * ( length / Climber.extendRotationsToInches);

    return ticks;
    }

  private double ExtendTicksToLength(double ticks)
    {
    double length = 0.0;

    length = (ticks / (Climber.extendInversePlanetaryRatio * Constants.falconTicksPerRotation) ) * Climber.extendRotationsToInches;

    return length;
    }

  public void PivotInner(double angle)
    {
      double ticks = PivotDegreesToTicks(angle * -1.0);
  
      Pivot(pivotInnerLeft, pivotInnerRight, ticks);
    }
  
  public void PivotOuter(double angle)
    {
      double ticks = PivotDegreesToTicks(angle);
  
      Pivot(pivotOuterLeft, pivotOuterRight, ticks);
    }

  public void ExtendInner(double length)
  {
    double ticks = ExtendLengthToTicks(length);

    Extend(extendInnerLeft, extendInnerRight, ticks);
  }

  public void ExtendOuter(double length)
  {
    double ticks = ExtendLengthToTicks(length);

    Extend(extendOuterLeft, extendOuterRight, ticks);
  }

  private void Pivot(WPI_TalonFX motor1, WPI_TalonFX motor2, double ticks)
  {
    motor1.set(TalonFXControlMode.MotionMagic,  ticks);
    motor2.set(TalonFXControlMode.MotionMagic,  ticks);
  }

  private void Extend(WPI_TalonFX motor1, WPI_TalonFX motor2, double ticks)
  {
    motor1.set(TalonFXControlMode.MotionMagic,  ticks);
    motor2.set(TalonFXControlMode.MotionMagic,  ticks);
  }

  private void PivotSingle(WPI_TalonFX motor, double ticks)
  {
    motor.set(TalonFXControlMode.MotionMagic,  ticks);
  }

  private void PivotSingleAngle(WPI_TalonFX motor, double angle)
  {
    double ticks = PivotDegreesToTicks(angle);

    PivotSingle(motor, ticks);
  }

  private void ExtendSingle(WPI_TalonFX motor, double ticks)
  {
    motor.set(TalonFXControlMode.MotionMagic,  ticks);
  }

  private void ExtendSingleLength(WPI_TalonFX motor, double length)
  {
  double ticks = ExtendLengthToTicks(length);

  ExtendSingle(motor, ticks);
  }

  public void ExtendOuterLeft(double length)
  {
    ExtendSingleLength(extendOuterLeft, length);
  }

  public void ExtendInnerLeft(double length)
  {
    ExtendSingleLength(extendInnerLeft, length);
  }

  public void ExtendOuterRight(double length)
  {
    ExtendSingleLength(extendOuterRight, length);
  }

  public void ExtendInnerRight(double length)
  {
    ExtendSingleLength(extendInnerRight, length);
  }

  public void PivotOuterLeft(double angle)
  {
  PivotSingleAngle(pivotOuterLeft, angle);
  }

  public void PivotInnerLeft(double angle)
  {
  PivotSingleAngle(pivotInnerLeft, angle);
  }

  public void PivotOuterRight(double angle)
  {
  PivotSingleAngle(pivotOuterRight, angle);
  }

  public void PivotInnerRight(double angle)
  {
  PivotSingleAngle(pivotInnerRight, angle);
  }

  private void configurePivotMotor(WPI_TalonFX motor)
    {

    motor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
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
    // motor.config_kF(0, (1023.0/410.0), Constants.kTimeoutMs);
    motor.config_kF(0, (1023.0/7200.0), Constants.kTimeoutMs);
    motor.config_kP(0, 0.25, Constants.kTimeoutMs);
    motor.config_kI(0, 0, Constants.kTimeoutMs);
    motor.config_kD(0, 0, Constants.kTimeoutMs);
    /* set acceleration and vcruise velocity - see documentation */
    // motor.configMotionCruiseVelocity(1150, Constants.kTimeoutMs);
    // motor.configMotionAcceleration(990, Constants.kTimeoutMs);
    // motor.configMotionCruiseVelocity(1800, Constants.kTimeoutMs);
    // motor.configMotionAcceleration(1500, Constants.kTimeoutMs);
    // motor.configMotionCruiseVelocity(21777.0, Constants.kTimeoutMs);
    // motor.configMotionAcceleration(10000.0, Constants.kTimeoutMs);
    motor.configMotionCruiseVelocity(10000.0, Constants.kTimeoutMs);
    motor.configMotionAcceleration(5000.0, Constants.kTimeoutMs);

    motor.configForwardSoftLimitEnable(true, Constants.kTimeoutMs);

    motor.configForwardSoftLimitThreshold(Climber.pivotSoftLimitTicks, Constants.kTimeoutMs);
    motor.configReverseSoftLimitThreshold(Climber.pivotSoftLimitTicks * -1.0, Constants.kTimeoutMs);
    
    // motor.configForwardSoftLimitEnable(true,)
    // motor.current(40, Constants.kTimeoutMs);
    // motor.configContinuousCurrentLimit(40, Constants.kTimeoutMs);

  }

  private void configureExtendMotor(WPI_TalonFX motor) {



    motor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
      // motor.setSensorPhase(true);
    motor.setInverted(false);
    /* Set relevant frame periods to be at least as fast as periodic rate*/
    motor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);

    motor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);
    /* set the peak and nominal outputs */
    motor.configNominalOutputForward(0, Constants.kTimeoutMs);
    motor.configNominalOutputReverse(0, Constants.kTimeoutMs);
    motor.configPeakOutputForward(1.0, Constants.kTimeoutMs);
    motor.configPeakOutputReverse(-1.0, Constants.kTimeoutMs);
    // motor.configPeakOutputForward(0.5, Constants.kTimeoutMs);
    // motor.configPeakOutputReverse(-0.5, Constants.kTimeoutMs);

    // motor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, Constants.kTimeoutMs);
    // motor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, Constants.kTimeoutMs);
    
    /* set closed loop gains in slot0 - see documentation */
    motor.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
    //motor.config_kF(0, (1023.0/1200), Constants.kTimeoutMs);
    motor.config_kF(0, (1023.0/7200.0), Constants.kTimeoutMs);
    motor.config_kP(0, 0.9, Constants.kTimeoutMs);
    motor.config_kI(0, 0, Constants.kTimeoutMs);
    motor.config_kD(0, 0, Constants.kTimeoutMs);
    /* set acceleration and vcruise velocity - see documentation */
    // motor.configMotionCruiseVelocity(1150, Constants.kTimeoutMs);
    // motor.configMotionAcceleration(990, Constants.kTimeoutMs);
    // motor.configMotionCruiseVelocity(30000, Constants.kTimeoutMs);
    // motor.configMotionAcceleration(30000, Constants.kTimeoutMs);
    motor.configMotionCruiseVelocity(19000.0, Constants.kTimeoutMs);
    motor.configMotionAcceleration(19000.0, Constants.kTimeoutMs);

    motor.configForwardSoftLimitEnable(true, Constants.kTimeoutMs);

    motor.configForwardSoftLimitThreshold(Climber.extendSoftLimitTicks, Constants.kTimeoutMs);
    motor.configReverseSoftLimitThreshold(0.0, Constants.kTimeoutMs);

  }

  private void configureMotors()
  {
    configurePivotMotor(pivotInnerLeft);
    pivotInnerLeft.setInverted(true);

    configurePivotMotor(pivotInnerRight);

    configurePivotMotor(pivotOuterLeft);
    pivotOuterLeft.setInverted(true);

    configurePivotMotor(pivotOuterRight);

    configureExtendMotor(extendInnerLeft);
    configureExtendMotor(extendInnerRight);
    
    configureExtendMotor(extendOuterLeft);
    configureExtendMotor(extendOuterRight);

  }

  public void NextStageClimb()
  {

    if(isMoving)
    {
      System.out.println("NextStageClimb climber isMoving cant stage.");

      return;
    }

    System.out.println("climber NextStageClimb");
    System.out.print("currentStage: ");
    System.out.println(currentStage);


    switch(currentStage)
    {
      case notReady : 
      {

        break;
      }
      case ready : 
      {
        // StartMove();
        SequentialCommandGroup temp = new SequentialCommandGroup
        (
        new StageClimbStartMoveCommand(ClimbStageEnum.climbMidBar),
        new ClimberSetLocationCoordiatedCommandGroup(-29.0, 0.0, 13.5, 0.0 ),
        new StageClimbFinishMoveCommand(ClimbStageEnum.climbMidBar)
        );

        temp.schedule();
        break;
      }
      case climbMidBar : 
      {
        SequentialCommandGroup temp = new SequentialCommandGroup
        (
        new StageClimbStartMoveCommand(ClimbStageEnum.hookHighBar),
        new ClimberSetLocationCoordiatedCommandGroup(-27.0, 0.0, 25.5, 0.0 ),
        new ClimberSetLocationCoordiatedCommandGroup(-20.0, 0.0, 25.5, 0.0 ),
        new DummyTimerCommand().withTimeout(0.5),
        new ClimberSetLocationCoordiatedCommandGroup(-20.0, 0.0, 19.0, 0.0 ),
        new ClimberSetLocationCoordiatedCommandGroup(-20.0, 0.0, 19.0, 1.5 ),
        new StageClimbFinishMoveCommand(Climber.ClimbStageEnum.hookHighBar)
        );
        temp.schedule();

        break;
      }
      case hookHighBar : 
      {
        SequentialCommandGroup temp = new SequentialCommandGroup
        (
        new StageClimbStartMoveCommand(ClimbStageEnum.ClimbHighBar),
        new ClimberSetLocationCoordiatedCommandGroup(-20.0, 0.0, 19.0, 15.0 ),
        new ClimberSetLocationCoordiatedCommandGroup(-20.0, 0.0, 15.0, 15.0 ),
        new StageClimbFinishMoveCommand(Climber.ClimbStageEnum.ClimbHighBar)
        );
        temp.schedule();

        break;
      }
      case ClimbHighBar : 
      {
        SequentialCommandGroup temp = new SequentialCommandGroup
        (
        new StageClimbStartMoveCommand(ClimbStageEnum.readyHighBar),
        new ClimberSetLocationCoordiatedCommandGroup(0.0, -29.0, 15.0,  5.0 ),
        new ClimberSetLocationCoordiatedCommandGroup(0.0, -29.0,  0.0, 13.5 ),
        new StageClimbFinishMoveCommand(Climber.ClimbStageEnum.readyHighBar)
        );
        temp.schedule();

        break;
      }
      case readyHighBar : 
      {
        SequentialCommandGroup temp = new SequentialCommandGroup
        (
        new StageClimbStartMoveCommand(ClimbStageEnum.hookTravers),
        new ClimberSetLocationCoordiatedCommandGroup(0.0, -29.0, 0.0, 25.5 ),
        new ClimberSetLocationCoordiatedCommandGroup(0.0, -20.0, 0.0, 25.5 ),
        new DummyTimerCommand().withTimeout(0.5),
        new ClimberSetLocationCoordiatedCommandGroup(0.0, -20.0, 0.0, 19.0 ),
        new ClimberSetLocationCoordiatedCommandGroup(0.0, -20.0, 1.5, 19.0 ),
        new StageClimbFinishMoveCommand(Climber.ClimbStageEnum.hookTravers)
        );
        temp.schedule();

        break;
      }
      case hookTravers : 
      {
        SequentialCommandGroup temp = new SequentialCommandGroup
        (
        new StageClimbStartMoveCommand(ClimbStageEnum.climbTraverse),
        new ClimberSetLocationCoordiatedCommandGroup(0.0, -20.0, 15.0,  19.0 ),
        new ClimberSetLocationCoordiatedCommandGroup(0.0, -20.0, 15.0,  15.0 ),
        new ClimberSetLocationCoordiatedCommandGroup(-15.0, 0.0,  5.0,  15.0 ),
        new ClimberSetLocationCoordiatedCommandGroup(-15.0, 0.0,  5.0,  0.0 ),
        new ClimberSetLocationCoordiatedCommandGroup(  0.0, 0.0,  5.0,  0.0 ),
        new ClimberSetLocationCoordiatedCommandGroup(  0.0, 0.0,  0.0,  0.0 ),
        new StageClimbFinishMoveCommand(Climber.ClimbStageEnum.climbTraverse)
        );
        temp.schedule();

        break;
      }
      case climbTraverse : 
      {
        break;
      }

    }

  }

  public void RetryStageClimb()
  {
    if(isMoving)
    {
      System.out.println("RetryStageClimb climber isMoving cant stage.");

      return;
    }

    System.out.println("climber RetryStageClimb");
    
    System.out.print("currentStage: ");
    System.out.println(currentStage);

    switch(currentStage)
      {
      case notReady : 
      case ready : 
      case climbMidBar : 
      case ClimbHighBar : 
      case readyHighBar : 
      case climbTraverse : 
      {
        break;
      }
      case hookHighBar : 
      {
        SequentialCommandGroup temp = new SequentialCommandGroup
        (
        new StageClimbStartMoveCommand(ClimbStageEnum.hookHighBar),
        new ClimberSetLocationCoordiatedCommandGroup(-29.0, 0.0, 19.0, 1.5 ),
        new ClimberSetLocationCoordiatedCommandGroup(-29.0, 0.0, 25.5, 0.0 ),
        new ClimberSetLocationCoordiatedCommandGroup(-20.0, 0.0, 25.5, 0.0 ),
        new ClimberSetLocationCoordiatedCommandGroup(-20.0, 0.0, 19.0, 0.0 ),
        new ClimberSetLocationCoordiatedCommandGroup(-20.0, 0.0, 19.0, 1.5 ),
        new StageClimbFinishMoveCommand(Climber.ClimbStageEnum.hookHighBar)
        );
        temp.schedule();
        break;
      }
      case hookTravers : 
      {
        SequentialCommandGroup temp = new SequentialCommandGroup
        (
        new StageClimbStartMoveCommand(ClimbStageEnum.hookTravers),
        new ClimberSetLocationCoordiatedCommandGroup(0.0, -29.0, 1.5, 19.0 ),
        new ClimberSetLocationCoordiatedCommandGroup(0.0, -29.0, 0.0, 25.5 ),
        new ClimberSetLocationCoordiatedCommandGroup(0.0, -20.0, 0.0, 25.5 ),
        new ClimberSetLocationCoordiatedCommandGroup(0.0, -20.0, 0.0, 19.0 ),
        new ClimberSetLocationCoordiatedCommandGroup(0.0, -20.0, 1.5, 19.0 ),
        new StageClimbFinishMoveCommand(Climber.ClimbStageEnum.hookTravers)
        );
        temp.schedule();
        break;
      }
    }


  }

}
