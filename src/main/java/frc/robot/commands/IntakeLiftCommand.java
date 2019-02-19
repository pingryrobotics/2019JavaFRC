package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeLiftCommand extends Command{
    public IntakeLiftCommand(){
        requires(Robot.intakeLift);
    }

    public void execute(){
        Robot.intakeLift.go(Robot.oi.drive3.getRawAxis(1));
    }
    public boolean isFinished(){
        return false;
    }
}