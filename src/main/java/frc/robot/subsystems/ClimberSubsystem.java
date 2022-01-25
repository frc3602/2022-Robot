// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {
  public ClimberSubsystem() {
    WPI_TalonSRX supportLeft = new WPI_TalonSRX(Constants.climbSupportLeftCANID);
    WPI_TalonSRX armLeft = new WPI_TalonSRX(Constants.climbArmLeftCANID);
    WPI_TalonSRX supportRight = new WPI_TalonSRX(Constants.climbSupportRightCANID);
    WPI_TalonSRX armRight = new WPI_TalonSRX(Constants.climbArmRightCANID);
  }

  @Override
  public void periodic() {

  }

  @Override
  public void simulationPeriodic() {

  }
}
