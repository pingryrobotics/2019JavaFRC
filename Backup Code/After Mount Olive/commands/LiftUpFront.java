package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftUpFront extends Command{
    public LiftUpFront(){
        requires(Robot.liftFront);
    }
    public void intitialize(){} 
    public void execute(){
        Robot.liftFront.lift(-.75);
    }
    public boolean isFinished(){
        return false;
    }
}