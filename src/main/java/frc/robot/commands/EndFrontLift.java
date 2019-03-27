package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class EndFrontLift extends Command{
    public EndFrontLift(){
        requires(Robot.liftFront);
    }
    public void execute(){
        Robot.liftFront.lift(0);
    }
    public boolean isFinished(){
        return false;
    }

}