/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import java.util.HashMap;
import java.util.Map;
import frc.robot.commands.visionCommand;

/**
 * Add your docs here.
 */
public class vision extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private SerialPort arduino;
  public Map<String, String> visionData=new HashMap<String,String>();
  public void arduinoSettup(){
    try{
      arduino = new SerialPort(115200,Port.kUSB);
    }catch (Exception e){
      System.out.println(e);
    }
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new visionCommand());
  }
  public Map<String,String> getData(){
    Map<String,String> temp = new HashMap<String,String>();
    String s = arduino.readString();
    String[] pairs = s.split(",");
    for (int i=0;i<pairs.length;i++){
      String pair = pairs[i];
      String[] keyValue = pair.split(":");
      temp.put(keyValue[0],keyValue[1]);
    }
    visionData=temp;
    return(visionData);
  }
  public void update(){
    Map<String,String> temp = new HashMap<String,String>();
    boolean success = true;
    String s = "";
    try{
      s = arduino.readString();
    } catch(Exception e) {
      visionData=temp;
      success=false;
    }
    if (success){
      System.out.println(s);
      String[] pairs = s.split(",");
      for (int i=0;i<pairs.length;i++){
        String pair = pairs[i];
        String[] keyValue = pair.split(":");
        if(keyValue.length>1){
          temp.put(keyValue[0],keyValue[1]);
        }
      }
      visionData=temp;
    }
  }
}
