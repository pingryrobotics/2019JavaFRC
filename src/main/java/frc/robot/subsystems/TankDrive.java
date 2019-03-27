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
    
    public double powerCoef = 1.0;
    

    /**
     * Drive base
     */
    public TankDrive() {
        /*
        //Talon SRX's for test robot
        frontRight = new TalonSRX(RobotMap.frontRightID);
        frontLeft = new TalonSRX(RobotMap.frontLeftID);
        backRight = new TalonSRX(RobotMap.backRightID);
        backLeft = new TalonSRX(RobotMap.backLeftID);
        */
        
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

    /**
     * Gets the encoder position of the right drive motors
     * @return Position in inches. Positive is forwards
     */
    public double getRightPosition(){
        return -ticksToInches(frontRight.getEncoder().getPosition());
    }

    /**
     * Gets the encoder position of the left drive motors
     * @return Position in inches. Positive is forwards
     */
    public double getLeftPosition(){
        return ticksToInches(frontLeft.getEncoder().getPosition());
    }

    private double ticksToInches(double ticks){
        return (ticks / 12)*6*Math.PI;
    }

    /**
     * Resets the encoders to have a current value of 0.
     */
    public void resetEncoders(){
        frontRight.setEncPosition(0);
        frontLeft.setEncPosition(0);
    }

    /*public void move(double pow1, double pow2){
        frontRight.set(ControlMode.PercentOutput,-pow1*powerCoef);
        backRight.set(ControlMode.PercentOutput,-pow1*powerCoef);
        frontLeft.set(ControlMode.PercentOutput,pow2*powerCoef);
        backLeft.set(ControlMode.PercentOutput,pow2*powerCoef);
    }*/

    public void move(double leftPow, double rightPow){
        frontRight.set(-rightPow*powerCoef);
        frontLeft.set(leftPow*powerCoef);
    }
}