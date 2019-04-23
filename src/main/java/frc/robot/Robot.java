/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Sonar;
import frc.robot.subsystems.TankDrive;
import frc.robot.subsystems.GyroSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.commands.drive_commands.DriveBaseCommands;
import frc.robot.commands.drive_commands.GyroTurn;
import frc.robot.commands.lift_commands.EndLift;
import frc.robot.commands.HoldDrivePosition;
import frc.robot.commands.intake_commands.IntakePosition;
import frc.robot.commands.intake_commands.ManualIntake;
import frc.robot.commands.lift_commands.ExtendBackJoy;
import frc.robot.commands.lift_commands.ExtendBothGyro;
import frc.robot.commands.lift_commands.ExtendFrontJoy;
import frc.robot.commands.ZeroGyro;
import frc.robot.commands.AutoCargoSide;
import frc.robot.commands.AutoLift;
import frc.robot.commands.AutoPickup;
import frc.robot.commands.CalibrateGyro;
import frc.robot.OI;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
 // public static DriveBaseCommands tankDrive = new DriveBaseCommands();
  public static OI oi;
  public static Lift liftFront;
  public static Lift liftBack;
  public static EndLift endLift;
  public static TankDrive drive;
  public static Intake intake;
  public static GyroSubsystem gyro; 
  public static Sonar sonar;

  public static DriveBaseCommands driveCommand;
  public static ManualIntake manualIntake;
  public static AutoPickup autoPickup;
  
  public static ExtendBackJoy liftBackJoy;
  public static ExtendFrontJoy liftFrontJoy;
  public static ExtendBothGyro liftBothGyro;

  public static AutoLift autoLift;

  public static int chassisLiftMode = 0;
  public static final int joysticks = 0;
  public static final int triggers = 1;
  public static final int auto = 2;

  public static NetworkTableEntry tx;
  public static NetworkTableEntry limelightLed;
  public static NetworkTableEntry limelightDist;

  public static int limelightOff = 1;
  public static int limelightOn = 0;

  public static final double gyroLiftP = 0.03;
  public static final double gyroLiftI = 0.0003;
  public static final double gyroLiftD = 0;

  public static Timer t;

  public static IntakePosition collectUp;
  public static IntakePosition collectPos;

  
  /*public static double aIntegral;
  public static double aPrevError;*/

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    UsbCamera camera =  CameraServer.getInstance().startAutomaticCapture();
    camera.setResolution(160,120);
    camera.setFPS(15);
    /*aIntegral = 0;
    aPrevError = 0;*/
    //Initialize subsystems
    liftFront = new Lift(RobotMap.frontTalon);
    liftBack = new Lift(RobotMap.backTalon);
    drive = new TankDrive();
    intake = new Intake(0, 0.005, 0, 0);
    driveCommand = new DriveBaseCommands();
    endLift = new EndLift();
    gyro = new GyroSubsystem();
    sonar = new Sonar();

    //Initialize commands
    liftFrontJoy = new ExtendFrontJoy();
    liftBackJoy = new ExtendBackJoy();
    liftBothGyro = new ExtendBothGyro(gyroLiftP, gyroLiftI, gyroLiftD);
    autoLift = new AutoLift();
    collectUp = new IntakePosition(23);
    manualIntake = new ManualIntake();
    collectPos = new IntakePosition(16.5);
    autoPickup = new AutoPickup();
   
    //Initialize the Operator Interface
    oi = new OI();

    m_chooser.addOption("Manual", driveCommand);
    m_chooser.addOption("Cargo Left", new AutoCargoSide(true));
    m_chooser.addOption("Cargo Right", new AutoCargoSide(false));

    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable ll = inst.getTable("limelight");
    inst.startClientTeam(2577);

    tx = ll.getEntry("tx"); //Note: in degrees
    ll.getEntry("snapshot").setNumber(1);
    limelightLed = ll.getEntry("ledMode");
    limelightDist = ll.getEntry("camtran");
    
    SmartDashboard.putData("Calibrate Gyro", new CalibrateGyro());
    SmartDashboard.putData("Zero Gyro", new ZeroGyro());

    SmartDashboard.putData("Autonomous", m_chooser);

    limelightLed.setNumber(limelightOn);

    Robot.intake.zeroEncoder();
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
    //double[] def = {99.0,99.0,99.0};
    //SmartDashboard.putNumber("limeDst", Math.abs(Robot.limelightDist.getDoubleArray(def)[2]));
    SmartDashboard.putNumber("sonar", Robot.sonar.getDistance());
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    driveCommand.cancel();
    limelightLed.setNumber(limelightOn);
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
    limelightLed.setNumber(limelightOn);
    m_autonomousCommand = m_chooser.getSelected();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    if(oi.drive1.getRawButton(2) || oi.drive2.getRawButton(2)){
      m_autonomousCommand.cancel();
      if(!driveCommand.isRunning()) driveCommand.start();
    }
  }

  @Override
  public void teleopInit() {
    limelightLed.setNumber(limelightOn);
    driveCommand.start();
    gyro.zero();

    t = new Timer();
    t.start();
    //intakeCommand.start();

    if(chassisLiftMode == triggers){
      //liftBothGyro.start();
    }else if(chassisLiftMode == joysticks){
      liftBackJoy.start();
      liftFrontJoy.start();
    }
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    SmartDashboard.putNumber("IntakePosition", intake.getPosition());
    //SmartDashboard.putNumber("Encoder position back lift", liftBack.getPosition());
    //SmartDashboard.putNumber("Robot Tip", Robot.gyro.getTip());
    //SmartDashboard.putNumber("Drive Encoder", drive.getLeftPosition()/12);
    if(Robot.oi.drive2.getRawButton(9)){
      gyro.zero();
    }
    if((oi.drive3.getRawAxis(3)> 0.2 || oi.drive3.getRawAxis(2)>0.2) && chassisLiftMode != triggers){
      gyro.zero();
      chassisLiftMode = triggers;
      liftBothGyro.start();
    }else if((Math.abs(oi.drive3.getRawAxis(1)) > 0.1 || Math.abs(oi.drive3.getRawAxis(5)) > 0.1) && (chassisLiftMode != joysticks)){
      chassisLiftMode = joysticks;
      liftFrontJoy.start();
      liftBackJoy.start();
    }

    if(oi.drive1.getRawButton(3)){
      if(!autoPickup.isRunning()){
        autoPickup.start();
      }
    }else if(oi.drive1.getRawButton(2)){
      autoPickup.cancel();
      Robot.driveCommand.start();
    }

    if(oi.drive3.getPOV() == 0){
      collectUp.start();
    }else if(oi.drive3.getPOV() == 90 || oi.drive3.getPOV() == 270){
      manualIntake.start();
    }else if(oi.drive3.getPOV() == 180){
      collectPos.start();
    }

    if(oi.drive3.getRawButton(8)){
      if(!autoLift.isRunning()){
        autoLift.start();
      }
    }else if(oi.drive3.getRawButton(5)){
      autoLift.cancel();
      Robot.drive.move(0,0);
      Robot.liftFront.lift(0);
      Robot.liftBack.lift(0);
      Robot.driveCommand.start();
    }
  }

  public GyroTurn g;
  @Override
  public void testInit() {
    g = new GyroTurn(90, 0.5, 0.1, 0, 0);
    g.start();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {

    Scheduler.getInstance().run();
    
    //gyro.log();
    
    //SmartDashboard.putNumber("IntakePosition", intake.getPosition());
  }
}
