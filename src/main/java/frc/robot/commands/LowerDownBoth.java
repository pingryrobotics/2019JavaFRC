package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LowerDownBoth extends Command{
    
    public LowerDownBoth(){
        requires(Robot.liftFront);
        requires(Robot.liftBack);
    }

    public void initialize(){}

    public void execute(){
        Robot.liftFront.lift(.75);
        Robot.liftBack.lift(.75);

    }

    public boolean isFinished(){
        return true;
    }
}