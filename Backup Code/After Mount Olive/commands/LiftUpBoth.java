package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftUpBoth extends Command{
    public LiftUpBoth(){
        requires(Robot.liftBack);
        requires(Robot.liftFront);
    }

    public void initialize(){}

    public void execute(){
        Robot.liftFront.lift(-.75);
        Robot.liftBack.lift(-.75);
    }

    public boolean isFinished(){
        return true;
    }

}