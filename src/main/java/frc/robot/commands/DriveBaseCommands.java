package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;



public class DriveBaseCommands extends Command{
    /**
     * Drive commands for tank drive using joysticks
     */
    public DriveBaseCommands(){
        super("Drive Base Commands");
        requires(Robot.drive);
    }
    public void initialize(){}

    public void execute(){
        //Allows us to slow down driving as necessary.
        double driveFactor = Robot.oi.drive1.getRawAxis(2);

        //Fine motor controls
        if(Robot.oi.drive1.getRawButton(11) || Robot.oi.drive2.getRawButton(5)){
            Robot.drive.move(0.15, 0.15);
        }else if(Robot.oi.drive1.getRawButton(10) || Robot.oi.drive2.getRawButton(4)){
            Robot.drive.move(-0.15, -0.15);
        }else{
            //Regular move
            Robot.drive.move(-Math.pow(Robot.oi.drive2.getRawAxis(1),1)*driveFactor, -Math.pow(Robot.oi.drive1.getRawAxis(1),1)*driveFactor);
        }
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(){
        Robot.drive.move(0,0);
    }

    @Override
    protected void interrupted() {
        Robot.drive.move(0,0);
    }
    
}