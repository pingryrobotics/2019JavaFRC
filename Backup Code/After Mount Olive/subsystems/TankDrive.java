package frc.robot.subsystems;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;


public class TankDrive extends Subsystem{
    CANSparkMax frontRight;
    CANSparkMax frontLeft;
    CANSparkMax backRight;
    CANSparkMax backLeft;
    
    /*TalonSRX frontRight;
    TalonSRX frontLeft;
    TalonSRX backRight;
    TalonSRX backLeft;*/
    
    public double powerCoef = 0.9;
    

    public TankDrive() {
        
        /*frontRight = new TalonSRX(RobotMap.frontRightID);
        frontLeft = new TalonSRX(RobotMap.frontLeftID);
        backRight = new TalonSRX(RobotMap.backRightID);
        backLeft = new TalonSRX(RobotMap.backLeftID);*/
        
        frontRight = new CANSparkMax(RobotMap.frontRightID, MotorType.kBrushless);
        frontLeft = new CANSparkMax(RobotMap.frontLeftID, MotorType.kBrushless);
        backLeft = new CANSparkMax(RobotMap.backLeftID, MotorType.kBrushless);
        backRight = new CANSparkMax(RobotMap.backRightID, MotorType.kBrushless);
       
        resetEncoders();
        backLeft.follow(frontLeft);
        backRight.follow(frontRight);
    }

    @Override 
    public void initDefaultCommand(){}

    public double getRightPosition(){
        return ticksToInches(frontRight.getEncoder().getPosition());
    }

    public double getLeftPosition(){
        return ticksToInches(frontLeft.getEncoder().getPosition());
    }

    public double ticksToInches(double ticks){
        return (ticks / 12)*6*Math.PI;
    }

    public void resetEncoders(){
        frontRight.setEncPosition(0);
        frontLeft.setEncPosition(0);
    }

    public void moveForwardDistance(double distance){
        
    }

    /*public void move(double pow1, double pow2){
        frontRight.set(ControlMode.PercentOutput,-pow1*powerCoef);
        backRight.set(ControlMode.PercentOutput,-pow1*powerCoef);
        frontLeft.set(ControlMode.PercentOutput,pow2*powerCoef);
        backLeft.set(ControlMode.PercentOutput,pow2*powerCoef);
    }*/

    public void move(double pow1, double pow2){
        frontRight.set(-pow1*powerCoef);
        frontLeft.set(pow2*powerCoef);
    }
}