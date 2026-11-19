import static org.wpilib.units.Units.Amps;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;

public class IntakeConstants {
    public static final double GEAR_RATIO = 44.01;
    public static final double MOI = 67.67; //this is not final
    public static final int ROLLER_ID = 1;//change probably
    public static final int ARM_ID = 2; //change also probably 67 lol
    
    public static final TalonFXConfiguration ROLLER_CONFIGURATION =
        new TalonFXConfiguration()
        .withMotorOutput(new MotorOutputConfigs().withInverted(InvertedValue.Clockwise_Positive))
        .withCurrentLimits(
            new CurrentLimitsConfigs()
                .withStatorCurrentLimit(Amps.of(120))
                .withSupplyCurrentLimit(Amps.of(40))
                .withSupplyCurrentLimitEnable(true)
                .withStatorCurrentLimitEnable(true)

        );
    public static final TalonFXConfiguration ARM_CONFIGURATION =
        new TalonFXConfiguration()
        .withMotorOutput(new MotorOutputConfigs().withInverted(InvertedValue.Clockwise_Positive))
        .withFeedback(new FeedbackConfigs().withSensorToMechanismRatio(GEAR_RATIO))
        .withSlot0(new Slot0Configs().withKP(21.285)
        .withKV(0.643)
        .withKD(0.947)
        .withKG(0.521))
        .withCurrentLimits(
            new CurrentLimitsConfigs()
                .withStatorCurrentLimit(Amps.of(120))
                .withSupplyCurrentLimit(Amps.of(40))
                .withSupplyCurrentLimitEnable(true)
                .withStatorCurrentLimitEnable(true));



}
