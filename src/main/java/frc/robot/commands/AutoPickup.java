/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPickup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoPickup() {
    addParallel(new IntakePosition(16.5));
    addSequential(new DriveTowardsTarget(0.2, 0.01));
    addSequential(new SonarForward());
    addSequential(new IntakePosition(23));
    //addSequential(new DriveAtPower(0.1, 2));
  }
}
