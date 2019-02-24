package frc.robot;


import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Timer;

public class Drivetrain {
    private DifferentialDrive ddrive;
    private SpeedControllerGroup left;
    private SpeedControllerGroup right;
    private double kp = 0.01;
    
    public double setpoint = 180.0;

    public Drivetrain(int frontLeft, int rearLeft, int frontRight, int rearRight) {
        left = new SpeedControllerGroup(new WPI_TalonSRX(frontLeft), new WPI_TalonSRX(rearLeft));
        right = new SpeedControllerGroup(new WPI_TalonSRX(frontRight), new WPI_TalonSRX(rearRight));
        ddrive = new DifferentialDrive(left, right);  
    }

    public void drive(double leftVelocity, double rightVelocity, int exit_or_enter) {
        if(exit_or_enter == -1) {
            ddrive.tankDrive(leftVelocity, rightVelocity);
        } else if(exit_or_enter == 0) {
            ddrive.tankDrive(0.6, 0.6);
        } else {
            ddrive.tankDrive(-0.6, -0.6);
        }
    }

    public void turnToAngle(double error) {
        ddrive.tankDrive(kp * error, -kp * error);
    }
}