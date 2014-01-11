/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package NerdHerd.Sensor;


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


public class NerdyGyro extends SensorBase {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    private I2C gyro;
    private int xAxisGyro, yAxisGyro, zAxisGyro;
    
    public NerdyGyro() {
        DigitalModule module = DigitalModule.getInstance(1);
        gyro = module.getI2C(104);
    }
    public void initGyro(){     
        gyro.write(62, 01);
    } 
    public void readGyro(){
        byte tempXYZ[] = new byte[6];
        gyro.read(0x1D, 6, tempXYZ);
        xAxisGyro = ((int)tempXYZ[0] << 8) | tempXYZ[1];  //combine 2 bytes into one integer for x;
        yAxisGyro = ((int)tempXYZ[2] << 8) | tempXYZ[3];  //combine 2 bytes into one integer for x;
        zAxisGyro = ((int)tempXYZ[4] << 8) | tempXYZ[5];  //combine 2 bytes into one integer for x; 
    }
    public int getAxisX() {
        return xAxisGyro;
    }
    public int getAxisY() {
        return yAxisGyro;
    }
    public int getAxisZ() {
        return zAxisGyro;
    }  
}