/**
 * Simple class containing constants used throughout project
 */
package frc.robot.motionProfileCal;

public class Constants {
	/**
	 * Which PID slot to pull gains from. Starting 2018, you can choose from
	 * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
	 * configuration.
	 */
	public static final int kSlotIdx = 0;

	/**
	 * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
	 * now we just want the primary one.
	 */
	public static final int kPIDLoopIdx = 0;

	/**
	 * set to zero to skip waiting for confirmation, set to nonzero to wait and
	 * report to DS if action fails.
	 */
	public static final int kTimeoutMs = 30;

	/**
	 * Gains used in Motion Magic, to be adjusted accordingly
     * Gains(kp, ki, kd, kf, izone, peak output);
     */
	public static final Gains drivekGains = new Gains(0.15, 0, 0, 0.2, 0, 1.0);
	
	public static final int driveAcceleration = 750;

	public static final int driveMaxVelocity = 19000;

	public static final Gains elevatekGains = new Gains(0.15, 0, 0, 0.2, 0, 1.0);
	
	public static final int elevateAcceleration = 2048;

	public static final int elevateMaxVelocity = 16384;
}