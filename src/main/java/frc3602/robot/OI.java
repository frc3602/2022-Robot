// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc3602.robot;

// WPILib Imports
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj.XboxController;

import frc3602.robot.Constants.Controller;

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
