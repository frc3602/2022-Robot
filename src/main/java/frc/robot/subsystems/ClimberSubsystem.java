// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {
  public ClimberSubsystem() {
    WPI_TalonFX topLeft = new WPI_TalonFX(Constants.climbTopLeftCANID);
    WPI_TalonFX bottomLeft = new WPI_TalonFX(Constants.climbBottomLeftCANID);
    WPI_TalonFX topRight = new WPI_TalonFX(Constants.climbTopRightCANID);
    WPI_TalonFX bottomRight = new WPI_TalonFX(Constants.climbBottomRightCANID);
  }

  @Override
  public void periodic() {

  }

  @Override
  public void simulationPeriodic() {

  }
}
