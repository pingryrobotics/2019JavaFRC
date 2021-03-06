/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Jetson extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  Relay power;

  public Jetson(){
    power = new Relay(0);
  }

  public void turnOn(){
    power.set(Relay.Value.kOn);
  }

  public void turnOff(){
    power.set(Relay.Value.kOff);
  }

  @Override
  public void initDefaultCommand() {
    
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
