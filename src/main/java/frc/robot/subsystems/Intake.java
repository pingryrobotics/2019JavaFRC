package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;


public class Intake extends Subsystem{
    TalonSRX shaft;
    int timeout = 30;
    int minPositionEncoder = 0;
    int maxPositionEncoder = 1337560;
    //int minPositionEncoder = 0;
    //int maxPositionEncoder = -5000;
    double minPositionInches = 16;
    double maxPositionInches = 73.5;

    double ticksPerInch = (maxPositionEncoder - minPositionEncoder)/(maxPositionInches - minPositionInches);

    @Override
    public void initDefaultCommand(){
       
    }

    public Intake(double f, double p, double i, double d){
        shaft = new TalonSRX(RobotMap.liftTalon);
        shaft.setInverted(true);
        shaft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, timeout);
        shaft.configClearPositionOnLimitR(true, timeout);
        shaft.configClearPositionOnLimitF(false, timeout);
        shaft.setInverted(true);
        shaft.configNominalOutputForward(0);
        shaft.configNominalOutputReverse(0);
        //configPeakOutput is the motor power 
        shaft.configPeakOutputForward(1.0);
        shaft.configPeakOutputReverse(-1.0);

        //configuring PIDF parameters
        shaft.config_kF(0, f, timeout);
        shaft.config_kP(0, p, timeout);
        shaft.config_kI(0, i, timeout);
        shaft.config_kD(0, d, timeout);
    }

    private double ticksToInches(double ticks){return  ((ticks-minPositionEncoder)/ticksPerInch) + minPositionInches;}

    private double inchesToTicks(double inches){return ((inches-minPositionInches)*ticksPerInch) + minPositionEncoder;}

    /**
     * Gets the current position of the encoder
     * @return Current position in ticks
     */
    public double getPosition(){
        return shaft.getSelectedSensorPosition();
    }

    /**
     * Gets the current position of the encoder converted to inches
     * @return
     */
    public double getPositionInches(){
        return ticksToInches(getPosition());
    }
    
    /**
     * Resets the encoders current position to 0
     */
    public void zeroEncoder(){
        shaft.setSelectedSensorPosition(0, 0, timeout);
    }

    /**
     * Sets the intake motor power to a value
     * @param pow Power to set the motor to
     */
    public void go(double pow){
        shaft.set(ControlMode.PercentOutput, pow);
    }

    /**
     * Sets the intake motor target position
     * @param position Target position in inches
     */
    public void goToPosition(double position){
        shaft.set(ControlMode.Position, inchesToTicks(position));
    }
}