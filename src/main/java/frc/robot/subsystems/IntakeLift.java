package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;


public class IntakeLift extends Subsystem{
    TalonSRX shaft;

    @Override
    public void initDefaultCommand(){
       
    }

    public IntakeLift(){
        shaft = new TalonSRX(RobotMap.liftTalon);
    }

    public double getPosition(){
        return shaft.getSelectedSensorPosition();
    }

    public void zeroEncoder(){
        shaft.setSelectedSensorPosition(0);
    }


    public void go(double pow){
        shaft.set(ControlMode.PercentOutput, pow);
    }

}