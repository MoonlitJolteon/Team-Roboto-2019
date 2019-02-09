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
      arduino = new SerialPort(115200,Port.kUSB);
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
        s = arduino.readString(); //Attempts to read serial 
      } catch(Exception e) {
        setNull();
        success=false; //Stop rest of code to prevent errors
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
          if (testMode){
            System.out.println(temp.get("cargo-x"));
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

  public double[] aimLeft(){
    final int[] target = {90,110};
    final double speed = 0.5;
    if (Robot.visionSub.visionData.get("tape-x")!="none"){
      int temp = Integer.parseInt(Robot.visionSub.visionData.get("tape-x"));
      if (temp < target[0] ){
        return new double[]{-speed, speed};
      } else if (temp > target[1]){
        return new double[]{speed, -speed};
      } else {
        return new double[]{0, 0};
      }
    } else {
      return null;
    }
  }
  public double[] aimRight(){
    final int[] target = {90,110};
    final double speed = 0.5;
    if (Robot.visionSub.visionData.get("tape-x")!="none"){
      int temp = Integer.parseInt(Robot.visionSub.visionData.get("tape-x"));
      if (temp < target[0] ){
        return new double[]{-speed, speed};
      } else if (temp > target[1]){
        return new double[]{speed, -speed};
      } else {
        return new double[]{0, 0};
      }
    } else {
      return null;
    }
}
public double[] aimFront(){
  final int[] target = {90,110};
    final double speed = 0.5;
    if (Robot.visionSub.visionData.get("tape-x")!="none"){
      int temp = Integer.parseInt(Robot.visionSub.visionData.get("tape-x"));
      if (temp < target[0] ){
        return new double[]{-speed, speed};
      } else if (temp > target[1]){
        return new double[]{speed, -speed};
      } else {
        return new double[]{0, 0};
      }
    } else {
      return null;
    }
  }
}
