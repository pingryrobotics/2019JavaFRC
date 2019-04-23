/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.intake_commands.IntakePosition;

public class AutoPlace extends CommandGroup {
  /**
   * Automatically places a hatch using intake positions, limelight, and sonar.
   */
  public AutoPlace() {
    addSequential(new DriveTowardsTarget(0.2, 0.01));
    addSequential(new SonarForward());
    addSequential(new IntakePosition(16.5));
  }
}
