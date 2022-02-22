/* Copyright (C) 2022 Team 3602. All rights reserved. */
/* This work is licensed under the terms of the MIT license */
/* found in the root directory of this project. */

package com.frc3602.robot;

import com.frc3602.robot.Constants.Controller;

// WPILib Imports
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj.XboxController;

public class OI {
    // Creates a new joystick and a xbox controller and sets the port number
    public static Joystick joystick = new Joystick(Controller.joystickPort);
    public static XboxController xboxController = new XboxController(Controller.xboxControllerPort);

    // Creates all the axes for the xbox controller
    public static POVButton povUp = new POVButton(xboxController, Controller.povUp);
    public static POVButton povRight = new POVButton(xboxController, Controller.povRight);
    public static POVButton povLeft = new POVButton(xboxController, Controller.povLeft);
    public static POVButton povDown = new POVButton(xboxController, Controller.povDown);

    // Binds the commands to the buttons and stuff
    OI() {
        povUp.whenPressed(RobotContainer.climbOneExtend);
        povDown.whenPressed(RobotContainer.climbOneRetract);
    }
}
