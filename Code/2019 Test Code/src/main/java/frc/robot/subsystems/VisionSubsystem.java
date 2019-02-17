/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import java.util.HashMap;
import java.util.Map;
import edu.wpi.first.wpilibj.Servo;

import frc.robot.*;

/**
 * Add your docs here.
 */
public class VisionSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private SerialPort arduino;

  private Servo pixyServo;

  public Map<String, String> visionData;

  public boolean hasArduino;
  public boolean testMode;
  

  public VisionSubsystem() {
    visionData = new HashMap<String,String>();
    hasArduino = false;

    pixyServo = new Servo(RobotMap.pixyServo);

    testMode = false;
  }

  public void init() {
    arduinoSetup();
  }
  
  public void arduinoSetup(){
    try{
      arduino = new SerialPort(115200,Port.kUSB1);
      hasArduino = true;
    } catch (Exception e){
      if (testMode){
        System.err.println(e);
      }
      hasArduino = false;
    }
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    //setDefaultCommand(new visionCommand());
  }

  public Map<String,String> getData(){
    update();
    return(visionData);
  }
  public void update(){
    if (hasArduino){
      Map<String,String> temp = new HashMap<String,String>();
      boolean success = true;
      String s = "";
      try{
        s = arduino.readString(); //Attempts to connect to serial 
      } catch(Exception e) {
        setNull();
        success=false; //Stop rest of code to prevent errors when fails to conect
        if (testMode){
          System.err.println(e);
        }
      } finally {
        if (success){
          String[] pairs = s.split(",");
          for (int i=0;i<pairs.length;i++){
            String pair = pairs[i];
            String[] keyValue = pair.split(":");
            if(keyValue.length>1){
              temp.put(keyValue[0],keyValue[1]);
            }
          }
          visionData=temp;
          if (true){
            System.out.println(temp.get("tape-x"));
          }
        }
      }
    }
  }

  private void setNull(){
    Map<String,String> temp = new HashMap<String,String>();
    String[] data = new String[]{"tape-x","tape-y","tape-h","tape-w","tape-o","tape-d","cargo-x","cargo-y"};
    for (String item : data) {
      temp.put(item,"none");
    }
    visionData=temp;
  }

  public void setServo(double position) {
    pixyServo.set(position);
  }
  public double[] aim(int[] target, double lSpeed, double hSpeed){
    if (Robot.visionSub.visionData.get("tape-x")!="none"){
      boolean success = true;
      int temp = 0;
      try{
        temp = Integer.parseInt(Robot.visionSub.visionData.get("tape-x"));
        //System.out.println(temp);
      } catch (Exception e){
        success=false;
      }
      if (success){
        if (temp < target[0] ){
          return new double[]{-hSpeed, hSpeed};
        } else if (temp > target[1]){
          return new double[]{hSpeed, -hSpeed};
        } else {
          if (temp < target[2]){
            return new double[]{-lSpeed, lSpeed};
          }else if (temp>target[3]){
            return new double[]{lSpeed,-lSpeed};
          }else{
            return new double[]{0,0};
          }
        }
      }else{
        return null;
      }
    }else{
      return null;
    } 
  }
  public double[] aimPC(double[] target, double[] motorRange){
    double[] visionRange={0,target[0],target[1],316};

    double tape = getValue("tape-x");
    double speed = 0;
    double motorConstant = (motorRange[0]-motorRange[1])/(visionRange[1]-visionRange[0]);
    if (tape>visionRange[0]&&tape<visionRange[1]) {
      speed=motorConstant*(tape-visionRange[0])+motorRange[1];
    } else if (tape>visionRange[2]&&tape<visionRange[3]){
      speed=motorConstant*(tape-visionRange[3])-motorRange[1];
    }
    return(new double[]{-speed,speed});
  }
  public double[] aimLeft(){
    return(aimPC(new double[]{143,173},new double[]{0.15,0.5}));
    //return aim(new int[]{128,188,148,168},0.15,0.3);
  }
  public double[] aimRight(){
    return(aimPC(new double[]{143,173},new double[]{0.15,0.5}));
    //return aim(new int[]{128,188,148,168},0.15,0.3);
  }
  public double[] aimFront(){
    return(aimPC(new double[]{143,173},new double[]{0.15,0.5}));
    //return aim(new int[]{128,188,148,168},0.15,0.3);
  }

  public double getValue(String value) {

    boolean success = true;
    double output = -1;

    if (Robot.visionSub.visionData.get(value)!="none") {
      try {
        output = Double.parseDouble(Robot.visionSub.visionData.get(value));
      } catch (Exception e) {
        success = false;
      }

      System.out.println(output);
      if(success) {
        return output;
      } else {
        return -1;
      }
    } else {
      return -1;
    }
  }
}
