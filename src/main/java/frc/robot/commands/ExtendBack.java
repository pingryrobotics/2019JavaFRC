package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
public class ExtendBack extends Command{
    public double power;
    /**
     * Extends back drive chassis at the specified power
     * @param power Speed (-1 to 1) to extend. Negative will retract.
     */
    public ExtendBack(double power){
        requires(Robot.liftBack);
    }
    public boolean isFinished(){
        return power < 0?Robot.liftBack.isRetracted():false;
    }
    public void initialize(){}
    public void execute(){
        Robot.liftBack.lift(-power);
    }

    public void end(){
        Robot.liftBack.lift(0);
    }

    public void interrupted(){
        Robot.liftBack.lift(0);
    }
}