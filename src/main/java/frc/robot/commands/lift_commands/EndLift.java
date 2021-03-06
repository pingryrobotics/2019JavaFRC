package frc.robot.commands.lift_commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class EndLift extends Command{
    public EndLift(){
        requires(Robot.liftBack);
        requires(Robot.liftFront);
    }
    public void execute(){
        Robot.liftBack.lift(0);
        Robot.liftFront.lift(0);
    }
    public boolean isFinished(){
        return false;
    }
}