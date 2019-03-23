package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeLiftPower extends Command{
    double power;
    public IntakeLiftPower(double power){
      this.power = power;
      requires(Robot.intakeLift);
    }

    @Override
    public void execute(){
      Robot.intakeLift.go(power);
    }

    public void interrupted(){
      Robot.intakeLift.go(0);
    }

    public boolean isFinished(){
        return false;
    }
}