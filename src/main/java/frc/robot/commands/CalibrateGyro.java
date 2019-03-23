package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class CalibrateGyro extends Command{

    public boolean isFinished(){
        return false;
    }
    public CalibrateGyro(){
        requires(Robot.gyro);
        setTimeout(5000);
    }
    public void execute(){
        Robot.gyro.calibrate();
    }
}