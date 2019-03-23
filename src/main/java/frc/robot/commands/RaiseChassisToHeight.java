/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class RaiseChassisToHeight extends PIDCommand {
  double endPosition;
  double pAccel;
  double powerLimit;
  public RaiseChassisToHeight(double powerLimit, double pAccel, double targetPosition, double p, double i, double d) {
    super("RaiseChassisToHeight", p, i, d);
    endPosition = targetPosition;
    this.pAccel = pAccel;
    this.powerLimit = powerLimit;
    requires(Robot.gyro);
    requires(Robot.liftBack);
    requires(Robot.liftFront);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.liftBack.zero();
    Robot.gyro.zero();
    this.setSetpoint(0);
  }

  @Override
  protected double returnPIDInput(){
    return Robot.gyro.getTip();
  }

  @Override
  protected void usePIDOutput(double output) {
    double power;
    double current = Robot.liftBack.getPosition();
    
    /*if(current < endPosition/2.0){
      //We need to accelerate upwards:
      double error = current + 1; //TODO: Change this 1 to a better constant.
      power = powerLimit*(Math.max(-1, Math.min(1, pAccel*error)));
    }else {
      //We need to decelerate:
      double error = current - endPosition;
      power = powerLimit*(Math.max(-1, Math.min(1, pAccel*error)));
    }*/

    //We need to decelerate:
    double error = current - endPosition;
    power = powerLimit*(Math.max(-1, Math.min(1, pAccel*error)));
    
    //Lifts at the power just calculated, but correcting for gyro tip
    Robot.liftBack.lift(-power-output);
    Robot.liftFront.lift(-power+output);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return endPosition - Robot.liftBack.getPosition() < 0.5;
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
