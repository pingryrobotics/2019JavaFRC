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
        double power = Robot.oi.drive3.getPOV() == 90?0.6:0;
        power += Robot.oi.drive3.getPOV() == 270?-0.6:0;
        Robot.intake.go(power);
    }
    public boolean isFinished(){
        return false;
    }
}