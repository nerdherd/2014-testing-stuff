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


public class NerdyAccel extends SensorBase {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    private I2C accel;
    private int xAxisAccel, yAxisAccel, zAxisAccel;
    
    public NerdyAccel() {
        DigitalModule module = DigitalModule.getInstance(1);
        accel = module.getI2C(83);
    }
    public void initAccel(){ 
        accel.write(45, 8);
    }
    
    public void readAccel(){
        byte tempXYZ[] = new byte[6];
        accel.read(0x32, 6, tempXYZ);
        xAxisAccel = ((int)tempXYZ[1] << 8) | tempXYZ[0];  //combine 2 bytes into one integer for x;
        yAxisAccel = ((int)tempXYZ[3] << 8) | tempXYZ[2];  //combine 2 bytes into one integer for x;
        zAxisAccel = ((int)tempXYZ[5] << 8) | tempXYZ[4];  //combine 2 bytes into one integer for x;
    }
    public int getAxisX() {
        return xAxisAccel;
    }
    public int getAxisY() {
        return yAxisAccel;
    }
    public int getAxisZ() {
        return zAxisAccel;
    }  
}