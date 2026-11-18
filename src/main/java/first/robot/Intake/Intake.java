package first.robot.Intake;

import static org.wpilib.units.Units.Volts;

import java.util.function.Supplier;

import org.wpilib.units.measure.Angle;
import org.wpilib.units.measure.AngularVelocity;
import org.wpilib.units.measure.Voltage;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.team6962.lib.StateMachine;

public class Intake extends StateMachine<IntakeState> {
    private TalonFX rollerMotor;
    private TalonFX pivotMotor;
    private StatusSignal<AngularVelocity> velocitySignal;
    private VoltageOut voltageRequest = new VoltageOut(0.0);

    public Intake(TalonFX pivotMotor, TalonFX rollerMotor) {
        this.pivotMotor = pivotMotor;
        this.rollerMotor = rollerMotor;
        pivotMotor.getConfigurator().apply(IntakeConstants.PIVOT_MOTOR_CONFIGURATION);
        rollerMotor.getConfigurator().apply(IntakeConstants.ROLLER_MOTOR_CONFIGURATION);

        
        setState(IntakeState.IDLE);
    }

    public void setPivotAngle(Angle targetAngle) {
        pivotMotor.setControl(new MotionMagicVoltage(targetAngle)
            .withFeedForward(IntakeConstants.PIVOT_MOTOR_CONFIGURATION.Slot0.kG * Math.cos(pivotMotor.getPosition().getValueAsDouble() * 2 * Math.PI)));
    }

    public void setRollerVoltage(double volts) {
        rollerMotor.setControl(voltageRequest.withOutput(Volts.of(volts)));
    }

    @Override
    public void onTransition(IntakeState newState) {
        switch(newState) {
            case IDLE -> {setPivotAngle(IntakeConstants.MIN_ANGLE_COM_WRT_ROTATION_AXIS); setRollerVoltage(0.0);}
            case INTAKING -> {setPivotAngle(IntakeConstants.MAX_ANGLE_COM_WRT_ROTATION_AXIS); setRollerVoltage(12.0);}
            case EXTAKING -> {setPivotAngle(IntakeConstants.MAX_ANGLE_COM_WRT_ROTATION_AXIS); setRollerVoltage(-12.0);}
        }
    }

    @Override
    public IntakeState getNextState(IntakeState currentState) {
        return currentState;
    }

    @Override
    public void periodic(IntakeState currentState) {
        IntakeState nextState = getNextState(currentState);
        if (nextState != currentState) {
            setState(nextState);
            return;
        }

        switch (currentState) {
            case IDLE -> {setPivotAngle(IntakeConstants.MIN_ANGLE_COM_WRT_ROTATION_AXIS); setRollerVoltage(0.0);}
            case INTAKING -> {setPivotAngle(IntakeConstants.MAX_ANGLE_COM_WRT_ROTATION_AXIS); setRollerVoltage(12.0);}
            case EXTAKING -> {setPivotAngle(IntakeConstants.MAX_ANGLE_COM_WRT_ROTATION_AXIS); setRollerVoltage(-12.0);}
        }
    }
}
