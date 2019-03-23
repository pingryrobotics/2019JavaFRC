/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HoldDrivePosition extends Command {
  double targetLeftPosition;
  double targetRightPosition;
  double p = 0.5;

  /**
   * Uses encoders to keep the robots position in place
   * @param p Proportional gain on encoder error (How aggressively to resist change in position)
   */
  public HoldDrivePosition(double p) {

    this.p = p;
    requires(Robot.drive);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    targetLeftPosition = Robot.drive.getLeftPosition();
    targetRightPosition = Robot.drive.getRightPosition();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Error is positive if we are too far forward
    double leftError = Robot.drive.getLeftPosition() - targetLeftPosition;
    double rightError = Robot.drive.getRightPosition() - targetRightPosition;

    //We need to move backwards if too far forward, so negative
    Robot.drive.move(-leftError*p, -rightError*p);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
