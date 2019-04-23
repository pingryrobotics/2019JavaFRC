/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Sonar extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  AnalogInput sensor;

  public Sonar(){
    sensor = new AnalogInput(RobotMap.sonarPort);
  }

  /**
   * Get distance detected on the sonar
   * @return Distance detected in inches from front of the bumpers
   */
  public double getDistance(){
    return sensor.getVoltage()*100/2.54 - 12;
  }

  @Override
  protected void initDefaultCommand() {

  }
}
