package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LiftUpFrontJoy extends Command{
    public LiftUpFrontJoy(){
        requires(Robot.liftFront);
    }
    public void intitialize(){} 
    public void execute(){
        Robot.liftFront.lift(Robot.oi.drive3.getRawAxis(5));
    }
    public boolean isFinished(){
        return false;
    }
}