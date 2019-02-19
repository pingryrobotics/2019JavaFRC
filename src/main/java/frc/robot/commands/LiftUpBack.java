package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
public class LiftUpBack extends Command{
    public LiftUpBack(){
        requires(Robot.liftBack);
    }
    public boolean isFinished(){
        return false;
    }
    public void initialize(){}
    public void execute(){
        Robot.liftBack.lift(-.75);
    }
}