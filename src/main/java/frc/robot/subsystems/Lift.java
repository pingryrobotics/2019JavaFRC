package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem{
    TalonSRX lift;

    @Override
    public void initDefaultCommand(){}

    public Lift(int TalonID){
        lift = new TalonSRX(TalonID);
    }
    
    public void lift(double pow){
        lift.set(ControlMode.PercentOutput, pow);
        lift.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
        lift.configClearPositionOnLimitR(true, 30);
        lift.configClearPositionOnLimitF(false, 30);
        this.zero();
    }

    public void zero(){
        lift.setSelectedSensorPosition(0);
    }

    public boolean isRetracted(){
        //TODO: Figure out if it is the forward or reverse limit switch
        return lift.getSensorCollection().isFwdLimitSwitchClosed();
    }

    public double getPosition(){
        return lift.getSelectedSensorPosition()*2*Math.PI/(12*75);
    }
    
}