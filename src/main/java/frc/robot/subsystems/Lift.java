package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem{
    TalonSRX lift;

    @Override
    public void initDefaultCommand(){}

    /**
     * Creates a drive base lift (front or back mini-chassis lift)
     * @param TalonID CAN ID for the lift motor
     */
    public Lift(int TalonID){
        lift = new TalonSRX(TalonID);
        
        lift.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
        lift.configClearPositionOnLimitR(true, 30);
        lift.configClearPositionOnLimitF(false, 30);
        this.zero();
    }
    
    /**
     * Retract/Extend the lift at a given power.
     * @param pow Power to lift (-1 to 1). +1 is retract, -1 is extend
     */
    public void lift(double pow){
        lift.set(ControlMode.PercentOutput, -pow);
    }

    /**
     * Zeros the lift encoder
     */
    public void zero(){
        lift.setSelectedSensorPosition(0);
    }


    public boolean isRetracted(){
        return lift.getSensorCollection().isRevLimitSwitchClosed();
    }

    public double getPosition(){
        return -lift.getSelectedSensorPosition()*Math.PI/(12*75)*(19/20.0);
    }
    
}