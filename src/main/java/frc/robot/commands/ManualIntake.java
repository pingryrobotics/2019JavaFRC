package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ManualIntake extends Command{
    /**
     * Moves the intake up or down using the joystick
     */
    public ManualIntake(){
        requires(Robot.intake);
    }

    public void execute(){
        Robot.intake.go(Robot.oi.drive3.getRawAxis(1));
    }
    public boolean isFinished(){
        return false;
    }
}