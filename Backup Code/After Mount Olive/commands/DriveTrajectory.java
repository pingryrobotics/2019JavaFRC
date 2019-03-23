/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.io.File;
import java.io.IOException;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;

public class DriveTrajectory extends Command {
  EncoderFollower left;
  EncoderFollower right;
  Timer timer;


  Trajectory cur;
  public DriveTrajectory() {
    requires(Robot.drive);
    requires(Robot.gyro);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    File f = new File("/newTrajectory.bin");
    try{
      if (!f.exists()) {
        System.out.println("File does not exist, creating it now.");
        f.createNewFile();
      }
    }catch(IOException e){
      e.printStackTrace();
    }

    Robot.trajectoryWanted.setBoolean(true);

    timer = new Timer();
    timer.start();

    updateTrajectory();

    Robot.gyro.zero();
  }

  private void updateTrajectory(){
    timer.reset();
    try {
      cur = Pathfinder.readFromFile(new File("/newTrajectory.bin"));
      left = new EncoderFollower(cur);
      right = new EncoderFollower(cur);
    }catch (IOException e){
      e.printStackTrace();
    }
  }

  @Override
  protected void execute() {
    if(timer.get() > 1){
      updateTrajectory();
    }

    if(cur != null && right != null && left != null){

      double l = left.calculate((int) Robot.drive.getLeftPosition());
      double r = right.calculate((int) Robot.drive.getRightPosition());

      double gyro_heading = Robot.gyro.getRotation();    // Assuming the gyro is giving a value in degrees
      double desired_heading = Pathfinder.r2d(left.getHeading());  // Should also be in degrees

      // This allows the angle difference to respect 'wrapping', where 360 and 0 are the same value
      double angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading);
      angleDifference = angleDifference % 360.0;
      if (Math.abs(angleDifference) > 180.0) {
        angleDifference = (angleDifference > 0) ? angleDifference - 360 : angleDifference + 360;
      } 

      double turn = 0.8 * (-1.0/80.0) * angleDifference;

      Robot.drive.move(l + turn, r - turn);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.trajectoryWanted.setBoolean(false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}
