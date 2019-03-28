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

  /**
   * Raises the chassis to a specific height using a double p controller. Uses gyro to lift and vertical position to decelerate
   * @param powerLimit Max power to raise the chassis at
   * @param pAccel Deceleration constant for proportional controller (% power per inch)
   * @param targetPosition Target lift height
   * @param p Proportional gain for gyro hold angle controller
   * @param i Integral gain for gyro hold angle controller
   * @param d Derivative gain for gyro hold angle controller
   */
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
    Robot.chassisLiftMode = Robot.auto;
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
    double error = endPosition - current; //Error will normally be positive
    power = powerLimit*(Math.max(-1, Math.min(1, pAccel*error))); //Power will be positive
    
    //Lifts at the power just calculated, but correcting for gyro tip
    Robot.liftBack.lift(-power-output); //power is negative since we need to extend.
    Robot.liftFront.lift(-power+output);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return endPosition - Robot.liftBack.getPosition() < 0.75;
  }

  @Override
  protected void end() {
    Robot.liftBack.lift(0);
    Robot.liftFront.lift(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
