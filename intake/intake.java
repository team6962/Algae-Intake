import org.wpilib.math.util.Units;

import com.ctre.phoenix6.controls.MotionMagicDutyCycle;
import com.ctre.phoenix6.hardware.CANdi;
import com.ctre.phoenix6.hardware.TalonFX;

public class intake {
    private TalonFX rollerMotor;
    private TalonFX armMotor;

    private double rollerVoltage = 12.0;
    private double armMotorVoltage = 12.0;
    private intakeSim simulation;

    public intake(){
        rollerMotor = new TalonFX(intakeConstants.ROLLER_ID, new CANBus("subsystems"));
        armMotor = new TalonFX(intakeConstants.ARM_ID, new CANBus("subsystems"));
        rollerMotor.getConfigurator().apply(intakeConstants.ROLLER_CONFIGURATION);
        armMotor.getConfigurator().apply(intakeConstants.ARM_CONFIGURATION);
        candi = new CANdi(intakeConstants.CANDI_DEVICE_ID, new CANbus("subsystems"));
        candi.getConfigurator().apply(IntakeConstants.CANDI_CONFIGURATION);
    }

    public void intakeExtend(){
        armMotor.setControl(new MotionMagicVoltage(Units.degreesToRotations(-90)));
    }
     public void intakeRetract(){
        armMotor.setControl(new MotionMagicVoltage(Units.degreesToRotations(90)));
    }
    public void runIntake(){
        rollerMotor.setContorl(VoltageOut(rollerVoltage));
    }

}
