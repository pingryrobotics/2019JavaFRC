/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class AutoLift extends CommandGroup {
  /**
   * Automatically lifts the robot and climbs the habitat.
   * Assumes the robot is already lined up in front of platform.
   */
  public AutoLift() {
    //Make sure we don't move while lifting up chassis
    //addParallel(new HoldDrivePosition(0.5));
    addParallel(new LimelightOff());

    addParallel(new DriveAtPower(0,1));
    addSequential(new RaiseChassisToHeight(0.5, 0.3, 21.5, Robot.gyroLiftP, Robot.gyroLiftI, Robot.gyroLiftD));

    //Hold our height while driving forward to get front idlers on platform
    addParallel(new HoldChassisHeight(Robot.gyroLiftP, Robot.gyroLiftI, Robot.gyroLiftD));
    addSequential(new DriveForDistance(0.1, 13));

    //Lower our back wheels down for the angle onto platform
    addSequential(new ExtendBack(0.75), 0.35);

    //Drive forward while raising front wheels
    addParallel(new DriveAtPower(0.15, 10)); //Drives forward until retracted
    addSequential(new ExtendFront(-0.75)); //Will end when fully retracted

    //Drive forward to get mid wheels on chassis
    addSequential(new DriveForDistance(0.3, 20)); //Drives forward for 20 inches

    //Raise back wheels while driving forward
    addParallel(new DriveAtPower(0.15, 10));  //Drive forward to get on the platform
    addSequential(new ExtendBack(-0.75));

    //Finish getting on the platform
    addParallel(new DriveAtPower(0.1, 1.4));
  }

  @Override
  public void end(){
    Robot.drive.move(0,0);
    Robot.liftFront.lift(0);
    Robot.liftBack.lift(0);
    Robot.driveCommand.start();
  }
}
