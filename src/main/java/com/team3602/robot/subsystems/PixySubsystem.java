// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.links.SPILink;

// https://github.com/PseudoResonance/Pixy2JavaAPI/wiki/Using-the-API

public class PixySubsystem extends SubsystemBase {

	private static final Pixy2 pixy = Pixy2.createInstance(new SPILink());  

  /** Creates a new PixySubsystem. */
  public PixySubsystem() {

    pixy.init();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
