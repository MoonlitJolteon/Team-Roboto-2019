package frc.robot.utils;

public class Utilities {
    private static final double wheelDiameter = 6; // Drive wheel diameter in inches.
    private static final double ticksPerRev = 4096; // The default number of encoder ticks per revolution.
    private static final double pi = Math.PI; // Pi from Java's math library.    
    
    private static double circumferance; // Used to find number of encoder ticks per inch.
    private static double ticksPerInch; // The number of encoder ticks per inch.

    public static void init() {
        circumferance = wheelDiameter * pi; // Calculate the circumference from the wheel diameter of the wheels, used to calculate number of ticks per inch.
        ticksPerInch = ticksPerRev / circumferance; // Calculate the number of encoder ticks per inch.
    }

    public static double inchToEncode(double inches) {
        double ticks; // Working variable.
        ticks = inches * ticksPerInch;  // Calculate number o       
        return ticks; // Return working variable.
    }

    public static double footToEncode(double feet) {
        double ticks; // Working variable.
        ticks = feet * (ticksPerInch*12); // Calculate number of encoder ticks per foot.
        return ticks; // Return working variable.
    }
}