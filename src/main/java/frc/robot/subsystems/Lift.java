package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
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
    }
    
}