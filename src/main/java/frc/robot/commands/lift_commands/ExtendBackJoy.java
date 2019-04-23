package frc.robot.commands.lift_commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
public class ExtendBackJoy extends Command{

    /**
     * Extends the back drive wheels according to the joystick values.
     */
    public ExtendBackJoy(){
        requires(Robot.liftBack);
    }
    public boolean isFinished(){
        return false;
    }
    public void initialize(){}
    public void execute(){
        Robot.liftBack.lift(Robot.oi.drive3.getRawAxis(1));
    }
}