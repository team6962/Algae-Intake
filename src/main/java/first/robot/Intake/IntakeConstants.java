package first.robot.Intake;

import static org.wpilib.units.Units.Degrees;
import static org.wpilib.units.Units.Inches;
import static org.wpilib.units.Units.KilogramSquareMeters;
import static org.wpilib.units.Units.Pounds;

import org.wpilib.units.measure.Angle;
import org.wpilib.units.measure.Distance;
import org.wpilib.units.measure.Mass;
import org.wpilib.units.measure.MomentOfInertia;

import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;

public class IntakeConstants {
    public static final TalonFXConfiguration ROLLER_MOTOR_CONFIGURATION = 
    new TalonFXConfiguration()
        .withMotorOutput(
            new MotorOutputConfigs()
                .withInverted(InvertedValue.Clockwise_Positive));
    

    public static final TalonFXConfiguration PIVOT_MOTOR_CONFIGURATION = 
    new TalonFXConfiguration()
        .withMotorOutput(
            new MotorOutputConfigs()
                .withInverted(InvertedValue.CounterClockwise_Positive))
        .withSlot0(
            new Slot0Configs()
            .withKA(0.026)
            .withKP(21.285)
            .withKG(0.521)
            .withKD(0.947)
            .withKV(0.643)
        );
    

    public static final double GEAR_RATIO = 44.01;
    public static final Distance COM_TO_ROTATION_AXIS_DISTANCE = Inches.of(8.726682);
    public static final Mass MOVING_MASS = Pounds.of(3.677);
    public static final MomentOfInertia MOI = KilogramSquareMeters.of(67.0); // fix this or ballino slimes
    public static final Angle MIN_ANGLE_COM_WRT_ROTATION_AXIS = Degrees.of(33.04726291);
    public static final Angle MAX_ANGLE_COM_WRT_ROTATION_AXIS = Degrees.of(133.04726291);
    
}
