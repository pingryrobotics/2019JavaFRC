/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
//import frc.robot.commands.AutoLift;
//import frc.robot.commands.DriveTowardsTarget;
import frc.robot.commands.DropHatchOff;
import frc.robot.commands.ExtendBoth;
import frc.robot.commands.IntakePosition;
import frc.robot.commands.IntakePower;
import edu.wpi.first.wpilibj.Joystick;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  
  public Joystick drive1 = new Joystick(1);
  public Joystick drive2 = new Joystick(0);
  public Joystick drive3 = new Joystick(2);

  Button autoClimb = new JoystickButton(drive3, 8);
  Button cancelClimb = new JoystickButton(drive3, 7);

  Button lowerPost = new JoystickButton(drive3, 6);

  Button highRocket = new JoystickButton(drive3, 4);//Y button
  Button midRocket = new JoystickButton(drive3, 2); //B button
  Button lowRocket = new JoystickButton(drive3, 1); //A button

  Button dropHatch = new JoystickButton(drive3, 3); //X button

  //Button highRocket2 = new JoystickButton(drive1, 3);
  //Button midRocket2 = new JoystickButton(drive1, 5);
  //Button lowRocket2 = new JoystickButton(drive1, 2);
  //Button collectHatch2 = new JoystickButton(drive1, 4);
  //Button dropHatch2 = new JoystickButton(drive1, 1);

  Button lowerPost2 = new JoystickButton(drive2, 2);

  public OI(){

    highRocket.whenPressed(new IntakePosition(72));
    //highRocket2.whenPressed(new IntakePosition(73.5));
    midRocket.whenPressed(new IntakePosition(47));
    //midRocket2.whenPressed(new IntakePosition(47));
    lowRocket.whenPressed(new IntakePosition(21));
    //lowRocket2.whenPressed(new IntakePosition(21));

    //collectHatch2.whenPressed(new IntakePosition(16));

    //autoClimb.whenPressed(new AutoLift());
    cancelClimb.whenPressed(new ExtendBoth(-1.0));


    lowerPost.whenPressed(new IntakePower(-0.5));
    lowerPost.whenReleased(new IntakePower(0));
    lowerPost2.whenPressed(new IntakePower(-0.5));
    lowerPost2.whenReleased(new IntakePower(0));

    //raisePost.whenPressed(new IntakePower(0.5));
    //raisePost.whenReleased(new IntakePower(0));
    //raisePost2.whenPressed(new IntakePower(0.5));
    //raisePost2.whenReleased(new IntakePower(0));

    dropHatch.whenPressed(new DropHatchOff());
    //dropHatch2.whenPressed(new DropHatchOff());
  }
}