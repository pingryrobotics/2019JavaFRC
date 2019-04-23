package frc.robot.commands.lift_commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ExtendFrontJoy extends Command{
    /**
     * Extends the front drive wheels using the value from the joysticks
     */
    public ExtendFrontJoy(){
        requires(Robot.liftFront);
    }
    public void intitialize(){} 
    public void execute(){
        Robot.liftFront.lift(Robot.oi.drive3.getRawAxis(5));
    }
    public boolean isFinished(){
        return false;
    }
}