// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {
  public ClimberSubsystem() {
    WPI_TalonSRX topLeft = new WPI_TalonSRX(Constants.climbTopLeftCANID);
    WPI_TalonSRX bottomLeft = new WPI_TalonSRX(Constants.climbBottomLeftCANID);
    WPI_TalonSRX topRight = new WPI_TalonSRX(Constants.climbTopRightCANID);
    WPI_TalonSRX bottomRight = new WPI_TalonSRX(Constants.climbBottomRightCANID);

  }

  @Override
  public void periodic() {

  }

  @Override
  public void simulationPeriodic() {

  }
}
