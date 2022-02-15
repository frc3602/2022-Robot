// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.Controller;

public class OI {
    // Creates a new joystick and sets the port number
    public static Joystick joystick = new Joystick(Controller.joystickPort);

    // Creates buttons on the joystick
    public static JoystickButton climberExtendButton = new JoystickButton(joystick, Controller.climberExtendButton);
    public static JoystickButton climberRetractButton = new JoystickButton(joystick, Controller.climberRetractButton);

}
