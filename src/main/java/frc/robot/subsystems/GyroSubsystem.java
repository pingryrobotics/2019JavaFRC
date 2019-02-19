/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import com.analog.adis16448.frc.ADIS16448_IMU;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class GyroSubsystem extends Subsystem {
  public ADIS16448_IMU imu;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public GyroSubsystem(){
    imu = new ADIS16448_IMU();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void log(){
    SmartDashboard.putNumber("Robot Tip", this.getTip());
    SmartDashboard.putNumber("Robot Rotation", this.getRotation());
  }

  public void calibrate(){
    imu.calibrate();
  }

  public double getRotation(){
    return imu.getAngleZ();
  }

  public double getTip(){
    return imu.getAngleX(); //Positive is forwards
  }
}
