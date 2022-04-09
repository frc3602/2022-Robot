// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot;

import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.team3602.robot.Constants.Controller;

/** Add your docs here. */
public class CTREUpdates

{

    public static void UpdateMotorValues(com.ctre.phoenix.motorcontrol.can.BaseTalon controller)
    {
        controller.setStatusFramePeriod(StatusFrameEnhanced.Status_1_General        , 255);
        controller.setStatusFramePeriod(StatusFrameEnhanced.Status_4_AinTempVbat    , 255);
        controller.setStatusFramePeriod(StatusFrameEnhanced.Status_6_Misc           , 255);
        controller.setStatusFramePeriod(StatusFrameEnhanced.Status_7_CommStatus     , 255);
        controller.setStatusFramePeriod(StatusFrameEnhanced.Status_8_PulseWidth, 255);
        controller.setStatusFramePeriod(StatusFrameEnhanced.Status_9_MotProfBuffer, 255);
        controller.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 255);
        controller.setStatusFramePeriod(StatusFrameEnhanced.Status_10_Targets, 255);
        controller.setStatusFramePeriod(StatusFrameEnhanced.Status_11_UartGadgeteer, 255);
        controller.setStatusFramePeriod(StatusFrameEnhanced.Status_12_Feedback1, 255);
        controller.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 255);
        controller.setStatusFramePeriod(StatusFrameEnhanced.Status_14_Turn_PIDF1, 255);
        controller.setStatusFramePeriod(StatusFrameEnhanced.Status_15_FirmwareApiStatus, 255);
        controller.setStatusFramePeriod(StatusFrameEnhanced.Status_Brushless_Current, 255);

    }


}
