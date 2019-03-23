/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveTowardsTarget extends Command {
  double power;
  double pTurn;

  /**
   * Drives towards the vision target detected by the Jetson
   * @param power Power to drive forward at
   * @param pTurn Proportional gain for error in orientation
   */
  public DriveTowardsTarget(double power, double pTurn) {
    requires(Robot.drive);
    this.power = power;
    this.pTurn = pTurn;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //If positive, the target is to our right and we need to turn left
    double turnError = Robot.yaw.getDouble(0)*pTurn;

    Robot.drive.move(power - turnError, power + turnError);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.lineDetected.getBoolean(false);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drive.move(0,0);
  }
}
