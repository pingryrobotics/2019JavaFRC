/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveForDistance extends Command {
  double power;
  double target;
  /**
   * Drives at a specified power until a distance is reached.
   * @param power Motor power to move at (0 to 1)
   * @param distance Distance (positive or negative) to move in inches
   */
  public DriveForDistance(double power, double distance) {
    this.power = Math.abs(power);
    target = distance;
    Robot.drive.resetEncoders();
    requires(Robot.drive);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.drive.move(power, power);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(target < 0){ //If we are moving backwards
      return Robot.drive.getLeftPosition() < target && Robot.drive.getRightPosition() < target;
    }else{ //If we are moving forwards
      return Robot.drive.getLeftPosition() > target && Robot.drive.getRightPosition() > target;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drive.move(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.drive.move(0,0);
  }
}