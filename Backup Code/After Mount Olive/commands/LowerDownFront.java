package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LowerDownFront extends Command{
    public LowerDownFront(){
        requires(Robot.liftFront);
        
    }
    public void initialize(){}
    public void execute(){
        Robot.liftFront.lift(.75);
    }
    public boolean isFinished(){
        return false;
    }
}