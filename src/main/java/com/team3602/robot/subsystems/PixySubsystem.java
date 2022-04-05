// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.subsystems;

import java.util.ArrayList;

import com.team3602.robot.Constants;
import com.team3602.robot.RobotContainer;
import com.team3602.robot.Constants.PixeyCam;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import io.github.pseudoresonance.pixy2api.links.SPILink;

// https://github.com/PseudoResonance/Pixy2JavaAPI/wiki/Using-the-API

public class PixySubsystem extends SubsystemBase {

	private static final Pixy2 pixyCamera = Pixy2.createInstance(new SPILink());

  private int blockCount = 0;
  private int largestBlockSignature = -1;
  private int largestBlockX = (int)Constants.PixeyCam.targetX;
  private int largestBlockY = -1;
  private int loopCounter = 0;


  /** Creates a new PixySubsystem. */
  public PixySubsystem()
  {
    pixyCamera.init();
  }

  public void logDataToSmartDashboard()
  {
    SmartDashboard.putNumber("Block Signature", largestBlockSignature);
    SmartDashboard.putNumber("Block X", largestBlockX);
    SmartDashboard.putNumber("Block Y", largestBlockY);
  }

  public int getBlockCount()
  {
    return blockCount;
  }

  public int getLargestBlockX()
  {
    return largestBlockX;
  }


  @Override
  public void periodic()
    {
    //logDataToSmartDashboard();
    loopCounter++;
    if(loopCounter>=1)
      {
        if(RobotContainer.pixyRotatePIDSubsystem.IsRunning())
        {
          GetBlockInfo();

        }
        loopCounter=0;
      }
    }

  public int GetAllianceSignature()
    {
    if(DriverStation.getAlliance() == Alliance.Blue)
      return PixeyCam.Signature_BLUE_BALL;
    else
      return PixeyCam.Signature_RED_BALL;
    }

  public void GetBlockInfo()
  {
    int getBlocksError = PixySubsystem.pixyCamera.getCCC().getBlocks(false, GetAllianceSignature(), 25);

    //System.out.println("ERROR: getBlocksError " + getBlocksError);          


    ArrayList<Block> blocks = PixySubsystem.pixyCamera.getCCC().getBlockCache(); // Gets a list of all blocks found

    blockCount = blocks.size();
    largestBlockX = (int)PixeyCam.targetX;

    if(blockCount > 0)
    {
      Block largestBlock = null;
      for (Block block : blocks)
        { // Loops through all blocks and finds the widest one
          if (largestBlock == null)
          {
            largestBlock = block;
          }
          else // if (block.() > largestBlock.getWidth())
          {
            double lBlockArea = largestBlock.getHeight() * largestBlock.getWidth();
            double blockArea = block.getHeight() * block.getWidth();

            if(blockArea >= lBlockArea)
            {
            largestBlock = block;
            }
          }
        }

        if(largestBlock!= null)
        {
          largestBlockSignature   = largestBlock.getSignature();
          largestBlockX           = largestBlock.getX();
          largestBlockY           = largestBlock.getY();
        }

    }


    blocks.clear();
  }
}
