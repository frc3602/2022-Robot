/*
 * @(#)Main.java        1.0 22/03/03
 *
 * Copyright (c) 2022 Team 3602
 * 500 S Lincoln Rd, Escanaba, Michigan, 49829, U.S.A.
 * All rights reserved.
 *
 * This software is licensed under the terms of the GPLv3 license
 * found in the root directory of this project.
 */

package com.team3602.robot;

// WPILib Imports
import edu.wpi.first.wpilibj.RobotBase;

/**
 * The Main class provides the main method where the robot is created.
 *
 * @version 1.0 04 Mar 2022
 * @author Cody Wellman
 */
public final class Main {
  /**
   * Constructor for {@link Main} class.
   */
  private Main() {
  }

  public static void main(String... args) {
    RobotBase.startRobot(Robot::new);
  }
}
