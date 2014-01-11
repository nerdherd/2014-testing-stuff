/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package NerdHerd.Sensor;


import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.DigitalModule;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SensorBase;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */


public class NerdyCompass extends SensorBase {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    private I2C compass;
    private int xAxisCompass, yAxisCompass, zAxisCompass;
    private double heading, error;
    private double headingOffset = 0;
    
    public NerdyCompass() {
        DigitalModule module = DigitalModule.getInstance(1);
        compass = module.getI2C(30);
    }
    public void initCompass(){   
        compass.write(02, 00);
    }
    public void readCompass(){
        byte tempXYZ[] = new byte[6];
        compass.read(0x03, 6, tempXYZ);
        xAxisCompass = ((int)tempXYZ[0] << 8) | tempXYZ[1];  //combine 2 bytes into one integer for x;
        yAxisCompass = ((int)tempXYZ[2] << 8) | tempXYZ[3];  //combine 2 bytes into one integer for x;
        zAxisCompass = ((int)tempXYZ[4] << 8) | tempXYZ[5];  //combine 2 bytes into one integer for x; 
    }
    public int getAxisX() {
        return xAxisCompass;
    }
    public int getAxisY() {
        return yAxisCompass;
    }
    public int getAxisZ() {
        return zAxisCompass;
    }
   public boolean IsCWShorter(double desiredAngle){
        double CWDistance = Math.abs(heading - desiredAngle);
        double CCWDistance = 360 - Math.abs(heading - desiredAngle);
        if (CWDistance <= CCWDistance){
            error = CWDistance;
           return true;
        }else{
            error = -CCWDistance;
            return false;
        }
    }
    public double getBearing(){//Non-Tilt Compensated
        heading = MathUtils.atan(yAxisCompass/xAxisCompass) - headingOffset;
        return heading;
    }
}