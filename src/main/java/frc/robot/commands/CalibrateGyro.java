package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class CalibrateGyro extends Command{

    /**
     * Calibrates the gyroscope. Takes approximately 10 seconds.
     */
    public CalibrateGyro(){
        requires(Robot.gyro);
        setTimeout(5000);
    }

    public boolean isFinished(){
        return false;
    }

    public void execute(){
        Robot.gyro.calibrate();
    }
}