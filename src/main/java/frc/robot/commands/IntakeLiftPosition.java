package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeLiftPosition extends Command{
    double pos;
    public IntakeLiftPosition(double position){
      this.pos = position;
      requires(Robot.intake);
    }

    @Override
    public void initialize(){
      Robot.intake.goToPosition(pos);
    }

    @Override
    public void execute(){
      
    }

    public boolean isFinished(){
        return false;
    }
}