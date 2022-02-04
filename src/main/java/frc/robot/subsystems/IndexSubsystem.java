// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IndexSubsystem extends SubsystemBase {
  public IndexSubsystem() {
    // Creates the motors & controllers and sets the CAN IDs for each one
    WPI_VictorSPX motorTop = new WPI_VictorSPX(Constants.indexMotorTopCANID);
    WPI_VictorSPX motorBottom = new WPI_VictorSPX(Constants.indexMotorBottomCANID);
    WPI_VictorSPX motorExtra = new WPI_VictorSPX(Constants.indexMotorExtraCANID);
  }

  @Override
  public void periodic() {

  }

  @Override
  public void simulationPeriodic() {

  }
}
