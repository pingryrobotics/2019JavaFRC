package frc.robot.commands;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

public class IntakeLiftPosition extends PIDCommand{
    public IntakeLiftPosition(double p, double i, double d){
      super(p, i, d);
      requires(Robot.intakeLift);
    }

    public double returnPIDInput(){
      return Robot.intakeLift.getPosition();
    }

    public void usePIDOutput(double output){
      Robot.intakeLift.go(output);
    }

    public void setTarget(double target){
      this.setSetpoint(target);
    }

    public boolean isFinished(){
        return false;
    }
}