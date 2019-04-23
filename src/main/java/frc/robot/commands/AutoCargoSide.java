/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.drive_commands.DriveAtPower;
import frc.robot.commands.drive_commands.DriveForDistance;
import frc.robot.commands.drive_commands.GyroTurn;

public class AutoCargoSide extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoCargoSide(boolean isLeft) {
    addSequential(new DriveForDistance(0.5, 48));
    addSequential(new DriveAtPower(-0.25, 1));
    addSequential(new DriveForDistance(0.5, 12*12));
    if(isLeft){
      addSequential(new GyroTurn(90, 0.5, 0.1, 0, 0));
    }else{
      addSequential(new GyroTurn(-90, 0.5, 0.1, 0, 0));
    }
    //Uncomment if we need to backup for limelight before the place.
    //addSequential(new DriveForDistance(0.5, -12));
    addSequential(new AutoPlace());
    addSequential(new DriveForDistance(0.5, -12));
  }
}
