/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LineFollowing extends Command {

  double driveSpeed;
  double pCenter;
  double centerTarget;
  double pTurn;

  /**
   * Follows the line for as long as it's detected.
   * @param forwardSpeed Speed to move forward by default (should be between 0 and ~0.5)
   * @param centerTarget X dimension we want the line we're following to be at (where is cenetered?)
   * @param pCenter Proportional gain for being off center
   * @param pTurn Proportional gain for wrong orientation
   */
  public LineFollowing(double forwardSpeed, double centerTarget, double pCenter, double pTurn) {
    this.driveSpeed = forwardSpeed;
    this.centerTarget = centerTarget;
    this.pCenter = pCenter;
    this.pTurn = pTurn;
    requires(Robot.drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Positive means we need to turn to the left
    double turnError = Robot.slope.getDouble(0)*pTurn;

    //Positive means we need to turn to the right
    double centerError = (centerTarget - Robot.lineX.getDouble(0))*pCenter;

    Robot.drive.move(driveSpeed+turnError-centerError, driveSpeed-turnError+centerError);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //The command is finished when the robot cannot detect the line.
    return !Robot.lineDetected.getBoolean(false);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drive.move(0,0);
  }
}
