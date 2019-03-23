package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
public class LiftUpBackJoy extends Command{
    public LiftUpBackJoy(){
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