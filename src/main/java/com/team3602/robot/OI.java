/*
 * @(#)OI.java        1.0 22/03/03
 *
 * Copyright (c) 2022 Team 3602
 * 500 S Lincoln Rd, Escanaba, Michigan, 49829, U.S.A.
 * All rights reserved.
 *
 * This software is licensed under the terms of the GPLv3 license
 * found in the root directory of this project.
 */

package com.team3602.robot;

import com.team3602.robot.Constants.Controller;

// WPILib Imports
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * The OI class is provides controllers and buttons for the controllers.
 *
 * @version 1.0 04 Mar 2022
 * @author Cody Wellman
 */
public class OI {
    // Creates a new joystick and a xbox controller and sets the port number
    public static Joystick joystick = new Joystick(Controller.joystickPort);
    public static XboxController xboxController = new XboxController(Controller.xboxControllerPort);

    // Creates all the buttons for the joystick
    public static JoystickButton indexInButton = new JoystickButton(joystick, Controller.indexInButton);
    public static JoystickButton indexOutButton = new JoystickButton(joystick, Controller.indexOutButton);
    public static JoystickButton shooterButton = new JoystickButton(joystick, Controller.shooterButton);

    public OI() {
    }
}
