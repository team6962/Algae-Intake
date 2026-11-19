import static org.wpilib.units.Units.Radians;
import static org.wpilib.units.Units.Rotations;

import org.wpilib.math.util.Units;
import org.wpilib.units.measure.Angle;

import com.ctre.phoenix6.controls.MotionMagicDutyCycle;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.CANdi;
import com.ctre.phoenix6.hardware.TalonFX;

public class Intake {
    private TalonFX rollerMotor;
    private TalonFX armMotor;
    private CANdi candi;
    private double rollerVoltage = 12.0;
    private double armMotorVoltage = 12.0;
    private IntakeSim simulation;

    public Intake(){
        rollerMotor = new TalonFX(IntakeConstants.ROLLER_ID, new CANBus("subsystems"));
        armMotor = new TalonFX(IntakeConstants.ARM_ID, new CANBus("subsystems"));
        rollerMotor.getConfigurator().apply(IntakeConstants.ROLLER_CONFIGURATION);
        armMotor.getConfigurator().apply(IntakeConstants.ARM_CONFIGURATION);
    }

    public void rotateToAngle(Angle targetAngle){
        armMotor.setControl(new MotionMagicVoltage(Rotations.of(targetAngle.in(Radians))).withFeedForward(IntakeConstants.ARM_CONFIGURATION.Slot0.kG * Math.cos(armMotor.getPosition().getValueAsDouble() * 2* Math.PI)));
    }
    public void runIntake(){
        rollerMotor.setControl(new VoltageOut(12.0));
    }


}
