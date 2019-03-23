package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ExtendFront extends Command{
    public double power;
    /**
     * Extends front drive chassis at the specified power
     * @param power Speed (-1 to 1) to extend. Negative will retract.
     */
    public ExtendFront(double power){
        this.power = Math.abs(power);
        requires(Robot.liftFront);
    }
    public void intitialize(){} 
    public void execute(){
        Robot.liftFront.lift(-power);
    }
    public boolean isFinished(){
        return power < 0?Robot.liftFront.isRetracted():false;
    }

    public void end(){
        Robot.liftFront.lift(0);
    }

    public void interrupted(){
        Robot.liftFront.lift(0);
    }
}