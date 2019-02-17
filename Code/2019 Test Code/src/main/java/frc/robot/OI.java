/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public static Joystick 
      leftStick = new Joystick(0),
      rightStick = new Joystick(1),
      operator = new Joystick(2),
      autoSelect = new Joystick(3);

  public static JoystickButton visionButton = new JoystickButton(operator, 9);
  public static JoystickButton driveButton = new JoystickButton(operator, 10);

  public OI() {
    visionButton.whileHeld(new VisionCommand());
    driveButton.whenPressed(new TeleopDriveToWall());
  }

}
