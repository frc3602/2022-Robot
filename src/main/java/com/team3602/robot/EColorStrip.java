// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3602.robot;

import com.team3602.robot.Constants.LEDColorStrip;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;


/**
 * Add your docs here.
 */
public class EColorStrip
  {
  AddressableLED led;
  AddressableLEDBuffer ledBuffer;
  
  private int length = 0;
  private int sections = 1;
  private int sectionSize = 1;
  private int deadSpace = 1;

  private int rainbowFirstPixelHue = 0;


  public EColorStrip(int port, int length_, int sections_, int deadSpace)
    {
    this.length = length_;
    this.deadSpace = deadSpace;

    SetNewSectionSize(sections_);

    led = new AddressableLED(port);
    int buffersize = (length + this.deadSpace) * LEDColorStrip.repeatSegmentCount;
    ledBuffer = new AddressableLEDBuffer(buffersize);
    led.setLength(ledBuffer.getLength());
    SetAllColor(Color.kWhite);

    }

    public void SetNewSectionSize(int sections)
    {
      this.sections = sections;
      sectionSize = length / sections;
    }

    public void start()
      {
      led.setData(ledBuffer);
      led.start();
      }

    public int Sections()
      {
      return sections;
      }

    public static double[] RGBtoHSV(double r, double g, double b)
      {

      double h, s, v;
  
      double min, max, delta;
  
      min = Math.min(Math.min(r, g), b);
      max = Math.max(Math.max(r, g), b);
      
      // V
      v = max;
  
        delta = max - min;
  
      // S
        if( max != 0 )
          s = delta / max;
        else {
          s = 0;
          h = -1;
          return new double[]{h,s,v};
        }
  
      // H
        if( r == max )
          h = ( g - b ) / delta; // between yellow & magenta
        else if( g == max )
          h = 2 + ( b - r ) / delta; // between cyan & yellow
        else
          h = 4 + ( r - g ) / delta; // between magenta & cyan
  
        h *= 60;    // degrees
  
      if( h < 0 )
          h += 360;
  
      return new double[]{h,s,v};
    }

    public static Color CapColorBrightness(Color startColor, double brightnessPercent)
    {
      double[] hsv = EColorStrip.RGBtoHSV(startColor.red, startColor.green, startColor.blue);

      int newH = (int)(hsv[0] / 2.0);
      int newS = (int)(hsv[1] * 255.0);
      int newV = (int)(Math.min(hsv[2], brightnessPercent) * 255.0);

      return Color.fromHSV(newH, newS, newV);
    }

    public void SetAllColor(Color color)
      {
      //Color newColor = CapColorBrightness(color, LEDColorStrip.brightnessPercentage);

      for (var index = 0; index < sections; index++)
      {
        SetSectionColor(index, color);
      }

      // for(var j = 0; j < LEDColorStrip.repeatSegmentCount; j++)
      //   {
      //     for (var i = 0; i < length; i++)
      //     {
      //     ledBuffer.setLED(i + (length * j), newColor);
      //     }
    
      //   }

      System.out.println("SetAllColor");          

      led.setData(ledBuffer);
      } 

    public void SetSectionColor(int index, Color color)
      {
      int firstIndex = sectionSize * index;

      int lastIndex = firstIndex + sectionSize;
      Color newColor = CapColorBrightness(color, LEDColorStrip.brightnessPercentage);

      if(firstIndex > ledBuffer.getLength())
        {
        System.out.println("ERROR: color index out of bounds");          
        return;
        }

      if(lastIndex > ledBuffer.getLength())
        {
        System.out.println("ERROR: color index out of bounds");          
        return;
        }

        int fudgeFactor = 0;
      if(index > 0)
        {
          fudgeFactor = 6;
  
        }
  

      for(var j = 0; j < LEDColorStrip.repeatSegmentCount; j++)
      {
        for (var i = firstIndex - fudgeFactor; i < lastIndex ; i++)
        {
        ledBuffer.setLED(i + (length * j) - fudgeFactor, newColor);
        }
      }
  
      led.setData(ledBuffer);
      } 

    public void rainbow()
    {
        // For every pixel
        for (var i = 0; i < ledBuffer.getLength(); i++) {
          // Calculate the hue - hue is easier for rainbows because the color
          // shape is a circle so only one value needs to precess
          final var hue = (rainbowFirstPixelHue + (i * 180 / ledBuffer.getLength())) % 180;
          // Set the value
          ledBuffer.setHSV(i, hue, 255, 128);
        }
        // Increase by to make the rainbow "move"
        rainbowFirstPixelHue += 3;
        // Check bounds
        rainbowFirstPixelHue %= 180;
        led.setData(ledBuffer);
    }
    

  }

