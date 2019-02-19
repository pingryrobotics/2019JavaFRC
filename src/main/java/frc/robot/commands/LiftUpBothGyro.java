/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;


public class LiftUpBothGyro extends PIDCommand {
  public LiftUpBothGyro(double p, double i, double d) {
    super("LiftUpGyro", p, i, d);
    requires(Robot.gyro);
    requires(Robot.liftBack);
    requires(Robot.liftFront);
  }

  protected double returnPIDInput(){
    return Robot.gyro.getTip();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.setSetpoint(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void usePIDOutput(double output) {
    Robot.liftBack.lift(-0.75-output);
    Robot.liftFront.lift(-0.75+output);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
