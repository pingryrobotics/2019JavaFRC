package frc.robot.subsystems;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;


public class TankDrive extends Subsystem{
    CANSparkMax frontRight;
    CANSparkMax frontLeft;
    CANSparkMax backRight;
    CANSparkMax backLeft;
    

    public TankDrive() {
        frontRight = new CANSparkMax(RobotMap.frontRightID, MotorType.kBrushless);
        frontLeft = new CANSparkMax(RobotMap.frontLeftID, MotorType.kBrushless);
        backLeft = new CANSparkMax(RobotMap.backLeftID, MotorType.kBrushless);
        backRight = new CANSparkMax(RobotMap.backRightID, MotorType.kBrushless);
        backLeft.follow(frontLeft);
        backRight.follow(frontRight);
    }

    @Override 
    public void initDefaultCommand(){}

    public void move(double pow1, double pow2){
        frontRight.set(-pow1);
        frontLeft.set(pow2);
    }
}