/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive_commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class GyroTurn extends PIDCommand {
  double target;
  double speed;
  public GyroTurn(double angle, double speed, double p, double i, double d) {
    super(p,i,d);
    this.speed = Math.abs(speed);
    this.target = angle;
    this.setSetpoint(this.target);
    requires(Robot.gyro);
    requires(Robot.drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.gyro.zeroRotation();
  }

  protected double returnPIDInput() {
    return Robot.gyro.getRotation();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void usePIDOutput(double output) {
    double power = this.speed*(Math.max(-1, Math.min(1, output)));
    Robot.drive.move(-power, power);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(target-Robot.gyro.getRotation()) < 1;
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
  }
}
