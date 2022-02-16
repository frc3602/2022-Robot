// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// WPILib Imports
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Axis;

import frc.robot.Constants.Controller;

public class OI {
    // Creates a new joystick and sets the port number
    public static Joystick joystick = new Joystick(Controller.joystickPort);
    public static XboxController xboxController = new XboxController(Controller.xboxPort);

    // Creates buttons on the joystick

    // Xbox Controller buttons and what not
    public static XboxController.Axis climberExtendLeftAxis = new XboxController.Axis(xboxController, Controller.climberExtendLeftAxis);
    public static XboxController.Axis climberRetractLeftAxis = new XboxController.Axis(xboxController, Controller.climberRetractButton);
}
