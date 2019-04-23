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
        return Math.abs(Robot.intake.getPositionInches() - this.pos) < 0.75;
    }

    @Override
    public void end(){  
      Robot.intake.go(0);
    }
}