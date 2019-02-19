package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class EndLift extends Command{

    public boolean isFinished(){
        return false;
    }
    public EndLift(){
        requires(Robot.liftBack);
        requires(Robot.liftFront);
    }
    public void execute(){
        Robot.liftBack.lift(0);
        Robot.liftFront.lift(0);
    }

}