package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LowerDownBack extends Command{
    public LowerDownBack(){
        requires(Robot.liftBack);
    }
    public void initialize(){}
    public void execute(){
        Robot.liftBack.lift(.75);
    }
    public boolean isFinished(){
        return false;
    }
}