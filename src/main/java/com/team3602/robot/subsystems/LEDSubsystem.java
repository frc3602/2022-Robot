// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.subsystems;

import com.team3602.robot.EColorStrip;
import com.team3602.robot.RobotContainer;
import com.team3602.robot.Constants.LEDColorStrip;
import com.team3602.robot.Constants.Climber.ClimbStageEnum;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDSubsystem extends SubsystemBase
  {
    private int loopCounter = 0;

    private static com.team3602.robot.EColorStrip colorStrip = new EColorStrip(LEDColorStrip.ledPWMPort,
                                                                               LEDColorStrip.ledSegmentLength, 
                                                                               LEDColorStrip.ledSegmentSections,
                                                                               LEDColorStrip.ledDeadSpace);

    private boolean prevUpperSensor = false;
    private boolean prevLowerSensor = false;

    // private ClimbStageEnum previousStage = ClimbStageEnum.notReady;
  
    private boolean climbStarted = false;
    private boolean doRainbow = false;


  /** Creates a new LEDSubsystem. */
  public LEDSubsystem()
    {
      DriverStation.getAlliance();
    }

  public void Init()
    {
      colorStrip.SetAllColor(GetAllianceColor());
      colorStrip.start();
    }

  public static Color GetAllianceColor()
    {
      if(DriverStation.getAlliance() == Alliance.Blue)
        return Color.kBlue;
      else
        return Color.kRed;
    }

  @Override
  public void periodic()
    {
      loopCounter++;

      if(loopCounter >= 1)
      {
        loopCounter = 0;

        if(climbStarted)
        {
          if(doRainbow)
          {
            colorStrip.rainbow();
          }

        }
        else
        {
          if(RobotContainer.indexSubsystem.indexSensorTop() && !prevUpperSensor) 
            {
              colorStrip.SetSectionColor(1, Color.kGreen);
            }
          else if(!RobotContainer.indexSubsystem.indexSensorTop() && prevUpperSensor)
            {
              colorStrip.SetSectionColor(1, GetAllianceColor());
            }
      
          if(RobotContainer.indexSubsystem.indexSensorBottom() && !prevLowerSensor) 
            {
              colorStrip.SetSectionColor(0, Color.kGreen);
            }
          else if(!RobotContainer.indexSubsystem.indexSensorBottom() && prevLowerSensor)
            {
              colorStrip.SetSectionColor(0, GetAllianceColor());
            }
            prevUpperSensor = RobotContainer.indexSubsystem.indexSensorTop();
            prevLowerSensor = RobotContainer.indexSubsystem.indexSensorBottom();
      
        }

    
      }

  

      // This method will be called once per scheduler run
    }
  
    public int StageSectionIndex(ClimbStageEnum stage)
    {
      switch(stage)
      {
      default:
      case notReady : return -1; 
      case ready : return 0;
      case climbMidBar : return 1;
      case hookHighBar : return 2;
      case ClimbHighBar : return 3;
      case readyHighBar : return 4;
      case hookTravers : return 5;
      case climbTraverse : return 6;
      }

    }

  public void StartMove(ClimbStageEnum stage)
    {
    switch(stage)
      {
        case notReady : 
        {
          climbStarted = false;
          colorStrip.SetNewSectionSize(2);
          break;
        }
        case ready : 
        {
          climbStarted = true;

          colorStrip.SetAllColor(Color.kBlack);

          colorStrip.SetNewSectionSize(7);

          colorStrip.SetSectionColor(StageSectionIndex(stage), Color.kOrange);

          // for(int i=1; i < 7; i++)
          // {
          //   colorStrip.SetSectionColor(i, Color.kBlack);
          // }
          break;
        }
        case climbMidBar :
        case hookHighBar : 
        case ClimbHighBar : 
        case readyHighBar : 
        case hookTravers : 
        {
          colorStrip.SetSectionColor(StageSectionIndex(stage), Color.kOrange);
          break;
        }
        case climbTraverse : 
        {
          doRainbow = true;
          //colorStrip.SetSectionColor(StageSectionIndex(stage), Color.kOrange);
          break;
        }
  
      }
    }

  public void FinishMove(ClimbStageEnum stage)
    {
      switch(stage)
      {
        case notReady : 
        {
          colorStrip.SetAllColor(GetAllianceColor());
          break;
        }
        case ready : 
        case climbMidBar :
        case hookHighBar : 
        case ClimbHighBar : 
        case readyHighBar : 
        case hookTravers : 
        {
          colorStrip.SetSectionColor(StageSectionIndex(stage), GetAllianceColor());
          break;
        }
        case climbTraverse : 
        {
          // colorStrip.SetSectionColor(StageSectionIndex(stage), GetAllianceColor());
          break;
        }
  
      }
    }

  }
