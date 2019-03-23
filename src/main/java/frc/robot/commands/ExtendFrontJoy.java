package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ExtendFrontJoy extends Command{
    public ExtendFrontJoy(){
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