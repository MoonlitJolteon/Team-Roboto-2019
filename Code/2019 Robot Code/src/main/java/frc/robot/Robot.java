/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.*;

import edu.wpi.cscore.*;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;

import frc.robot.utils.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  
  //public static DriveTrainSubSystem driveTrainSub = new DriveTrainSubSystem();
  public static SparkMaxDriveTrain driveTrainSub = new SparkMaxDriveTrain();
  public static VisionSubsystem visionSub = new VisionSubsystem();
  public static BallSubsystem ballSub = new BallSubsystem();
  public static ElevatorSubsystem elevatorSub = new ElevatorSubsystem();
  public static HatchSubsystem hatchSub = new HatchSubsystem();

  public static double matchTime;

  public static Scalar alertColor = new Scalar(0, 0, 255);
  public static boolean alertEnabled = false;
  public static OI m_oi;
  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    // chooser.addOption("My Auto", new MyAutoCommand());
    // SmartDashboard.putData("Auto mode", m_chooser);

    Thread cameraThread = new Thread(() -> {

      UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
      camera.setResolution(160, 120);
      
      CvSink cvSink = CameraServer.getInstance().getVideo();
      CvSource outputStream = CameraServer.getInstance().putVideo("Processed", 160, 120);

      Mat source = new Mat();
      Mat output = new Mat();
      
      while(!Thread.interrupted()) {
          try {
            cvSink.grabFrame(source);
            Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2BGRA);
            /*Imgproc.rectangle(output, new Point(18, 50), new Point(19,70), new Scalar(255,255,255));
            Imgproc.rectangle(output, new Point(160-18, 50), new Point(160-19,70), new Scalar(255,255,255));
            Imgproc.rectangle(output, new Point(79, 50), new Point(80,70), new Scalar(255,255,255));
            */
            if(alertEnabled)
            Imgproc.rectangle(output, new Point(4, 4), new Point(156, 116), alertColor, 8);
            outputStream.putFrame(output);
          } catch (Exception e) {

          }
      }
    });
    
    cameraThread.start();
    visionSub.init();
    Utilities.init();
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    visionSub.update();
    DriverStation ds = DriverStation.getInstance();
    matchTime = ds.getMatchTime();
    
    if (matchTime>45 || ds.isAutonomous() || matchTime < 0){
      alertEnabled = false;
      //SmartDashboard.putBooleanArray("matchTime", new boolean[]{true,false,false,false,false});
    } else if (matchTime>30) {
      alertEnabled = true;
      alertColor = new Scalar(0, 215, 255);
      //SmartDashboard.putBooleanArray("matchTime", new boolean[]{false,true,false,false,false});
    } else if (matchTime>20){
      alertEnabled = true;
      alertColor = new Scalar(0, 0, 255);
      //SmartDashboard.putBooleanArray("matchTime", new boolean[]{false,false,true,false,false});
    } else {
      alertEnabled = true;
      if(Math.round(matchTime*2)%2==1){
        alertColor = new Scalar(0, 215, 255);
        //SmartDashboard.putBooleanArray("matchTime", new boolean[]{false,false,false,true,false});
      }else{
        alertColor = new Scalar(0, 0, 255);
        //SmartDashboard.putBooleanArray("matchTime", new boolean[]{false,false,false,false,true});
      }
      //SmartDashboard.putNumber("matchTime",Math.round(matchTime)%2+3);
    }
    SmartDashboard.putNumber("matchTimeNumber", matchTime);
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    visionSub.testMode = false;
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();
    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }

    //visionSub.testMode = false;
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    visionSub.testMode = false;
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    
    Scheduler.getInstance().run();
  }

  @Override
  public void testInit() {
    visionSub.testMode = true;
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
  }
}
