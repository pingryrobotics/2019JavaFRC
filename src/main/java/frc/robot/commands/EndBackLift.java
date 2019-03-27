package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class EndBackLift extends Command{
    public EndBackLift(){
        requires(Robot.liftBack);
    }
    public void execute(){
        Robot.liftBack.lift(0);
    }
    public boolean isFinished(){
        return false;
    }

}