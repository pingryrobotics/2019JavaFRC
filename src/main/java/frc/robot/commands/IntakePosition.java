package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakePosition extends Command{
    double pos;
    public IntakePosition(double position){
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
        //TODO: Add in a threshold for this
        return Robot.intake.getPositionInches() == this.pos;
    }

    @Override
    public void end(){
      Robot.intake.go(0);
    }
}