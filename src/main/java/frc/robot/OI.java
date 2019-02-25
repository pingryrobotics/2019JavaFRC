/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.LiftUpBothGyro;
import frc.robot.commands.LowerDownBoth;
import frc.robot.commands.EndFrontLift;
import frc.robot.commands.EndBackLift;
import frc.robot.commands.EndLift;
import frc.robot.commands.LiftUpBack;
import frc.robot.commands.LowerDownBack;
import frc.robot.commands.LiftUpFront;
import frc.robot.commands.LowerDownFront;

import edu.wpi.first.wpilibj.Joystick;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  
  public Joystick drive1 = new Joystick(0);
  public Joystick drive2 = new Joystick(1);
  public Joystick drive3 = new Joystick(2);

  Button liftBack = new JoystickButton(drive3,8);
  Button lowerBack = new JoystickButton(drive3, 7);
  
  Button liftFront = new JoystickButton(drive3, 10);
  Button lowerFront = new JoystickButton(drive3, 9);

  Button liftButton = new JoystickButton(drive3, 12);
  Button lowerButton = new JoystickButton(drive3, 11);
  Button holdButton = new JoystickButton(drive3, 2);

  public OI(){
    
    liftButton.whenPressed(new LiftUpBothGyro(0.5, 0.06, 0.00000, 0));
    lowerButton.whenPressed(new LowerDownBoth());
    liftButton.whenReleased(new EndLift());
    lowerButton.whenReleased(new EndLift());

    holdButton.whenPressed(new LiftUpBothGyro(0.01, 0.06, 0.00000, 0));
    
    liftBack.whenPressed(new LiftUpBack());
    lowerBack.whenPressed(new LowerDownBack());
    liftBack.whenReleased(new EndBackLift());
    lowerBack.whenReleased(new EndBackLift());

    liftFront.whenPressed(new LiftUpFront());
    lowerFront.whenPressed(new LowerDownFront());
    liftFront.whenReleased(new EndFrontLift());
    lowerFront.whenReleased(new EndFrontLift());

  }  
}

