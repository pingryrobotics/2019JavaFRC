package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class ZeroGyro extends Command{
    public ZeroGyro(){
        requires(Robot.gyro);
        setTimeout(100);
    }

    @Override
    public void initialize(){
        Robot.gyro.zero();
    }
    
    public boolean isFinished(){
        return true;
    }
}