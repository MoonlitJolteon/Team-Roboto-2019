package frc.robot.motionProfileCal;


import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class configureDriveTalons {

    public static void configureTalon(TalonSRX _talon) {

        /* Set relevant frame periods to be at least as fast as periodic rate */
        _talon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
        _talon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);
    
        /* Set the peak and nominal outputs */
        _talon.configNominalOutputForward(0, Constants.kTimeoutMs);
        _talon.configNominalOutputReverse(0, Constants.kTimeoutMs);
        _talon.configPeakOutputForward(1, Constants.kTimeoutMs);
        _talon.configPeakOutputReverse(-1, Constants.kTimeoutMs);
    
        /* Set Motion Magic gains in slot0 - see documentation */
        _talon.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
        _talon.config_kF(Constants.kSlotIdx, Constants.drivekGains.kF, Constants.kTimeoutMs);
        _talon.config_kP(Constants.kSlotIdx, Constants.drivekGains.kP, Constants.kTimeoutMs);
        _talon.config_kI(Constants.kSlotIdx, Constants.drivekGains.kI, Constants.kTimeoutMs);
        _talon.config_kD(Constants.kSlotIdx, Constants.drivekGains.kD, Constants.kTimeoutMs);
    
        /* Set acceleration and vcruise velocity - see documentation */
        _talon.configMotionCruiseVelocity(Constants.driveMaxVelocity, Constants.kTimeoutMs);
        _talon.configMotionAcceleration(Constants.driveAcceleration, Constants.kTimeoutMs);
    
        /* Zero the sensor */
        _talon.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

        _talon.setNeutralMode(NeutralMode.Brake);
    }
}
