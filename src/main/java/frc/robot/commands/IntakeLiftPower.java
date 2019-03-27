package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeLiftPower extends Command{
    double power;
    /**
     * Moves the intake up or down using the given power
     * @param power Power to move at (-1 to 1) where +1 moves up.
     */
    public IntakeLiftPower(double power){
      this.power = power;
      requires(Robot.intake);
    }

    @Override
    public void execute(){
      Robot.intake.go(power);
    }

    public void interrupted(){
      Robot.intake.go(0);
    }

    public boolean isFinished(){
        return false;
    }
}