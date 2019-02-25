package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;



public class DriveBaseCommands extends Command{
    public DriveBaseCommands(){
        super("Drive Base Commands");
        requires(Robot.drive);
    }
    public void initialize(){}

    public void execute(){
        Robot.drive.move(-Math.pow(Robot.oi.drive1.getRawAxis(1),1)/2, -Math.pow(Robot.oi.drive2.getRawAxis(1),1)/2);
    }
    
    public boolean isFinished(){
        return false;
    }
    
}