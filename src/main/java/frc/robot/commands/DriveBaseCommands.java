package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;



public class DriveBaseCommands extends Command{
    /**
     * Drive commands for tank drive using joysticks
     */
    private double pTurn;
    public DriveBaseCommands(){
        super("Drive Base Commands");
        requires(Robot.drive);
    }
    public void initialize(){}

    public void execute(){
        //Allows us to slow down driving as necessary.
        double driveFactor = Robot.oi.drive1.getRawAxis(2);
        double leftPow = 0;
        double rightPow = 0;
        //Fine motor controls
        if(Robot.oi.drive1.getRawButton(11) || Robot.oi.drive2.getRawButton(5)){
            leftPow = 0.15;
            rightPow = 0.15;
        }else if(Robot.oi.drive1.getRawButton(10) || Robot.oi.drive2.getRawButton(4)){
            leftPow = -0.15;
            rightPow = -0.15;
        }else{
            //Regular move
            leftPow = -Math.pow(Robot.oi.drive2.getRawAxis(1),1)*driveFactor;
            rightPow = -Math.pow(Robot.oi.drive1.getRawAxis(1),1)*driveFactor;
            
            //TODO: find button number, tune pTurn
            if (robot.oi.getRawButton(buttonNum)){
                double steeringModifier = Robot.tx.getDouble(0)*pTurn;
                leftPow += steeringModifier;
                rightPow -= steeringModifier;
            }
        }

        Robot.drive.move(leftPow,rightPow)
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