package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ExtendBoth extends Command{
    /**
     * Extends both drive chassis at the specified power
     * @param power Speed (-1 to 1) to extend. Negative will retract.
     */

    double power;
    public ExtendBoth(double power){
        this.power = power;
        requires(Robot.liftBack);
        requires(Robot.liftFront);
    }

    public void initialize(){}

    public void execute(){
        Robot.liftFront.lift(-power);
        Robot.liftBack.lift(-power);
    }

    public boolean isFinished(){
        return true;
    }

}