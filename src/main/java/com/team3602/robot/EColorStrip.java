/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.w3c.dom.css.RGBColor;

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
  private int endIndex = 1;

  public EColorStrip(int port, int length_, int sections_, int deadSpace)
    {
    this.length = length_;
    this.sections = sections_;
    this.deadSpace = deadSpace;

    sectionSize = length / sections;

    System.out.println("section size" + sectionSize); 

    led = new AddressableLED(port);
    int buffersize = (length + this.deadSpace) * 2;
    endIndex = buffersize - 1;
    ledBuffer = new AddressableLEDBuffer(buffersize);
    led.setLength(ledBuffer.getLength());
    SetAllColor(Color.kWhite);
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

    public void SetAllColor(Color color)
      {
      SetAllColor( (int)(color.red * 255.999) , (int)(color.green * 255.999), (int)(color.blue * 255.999));
      }

    public void SetAllColor(int red, int green, int blue)
      {
      for (var i = 0; i < length; i++)
        {
        // Sets the specified LED to the RGB values for red
        ledBuffer.setRGB(i, red, green, blue);
        }

        //mirror

        for (var i = endIndex; i > (endIndex - length); i--)
        {
        // Sets the specified LED to the RGB values for red
        ledBuffer.setRGB(i, red, green, blue);
        }


      System.out.println("SetAllColor");          

      led.setData(ledBuffer);
      } 

    public void SetSectionColor(int index, Color color)
      {
      SetSectionColor(index, (int)(color.red * 255.999) , (int)(color.green * 255.999), (int)(color.blue * 255.999));
      }

    public void SetSectionColor(int index, int red, int green, int blue)
      {
      int firstIndex = sectionSize * index;
      int lastIndex = firstIndex + sectionSize;

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

      // int mirrorIndex = ledBuffer.getLength() - firstIndex;
      for (var i = firstIndex; i < lastIndex; i++)
        {
        // Sets the specified LED to the RGB values for red
        ledBuffer.setRGB(i, red, green, blue);
        }

        


        for(int j = endIndex - firstIndex; j > endIndex - lastIndex; j--)
        {
        ledBuffer.setRGB(j, red, green, blue);
        }

      //System.out.println("SetSectionColor");

      led.setData(ledBuffer);
      } 

  }
