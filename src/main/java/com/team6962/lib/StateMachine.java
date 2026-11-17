package com.team6962.lib;

import org.wpilib.command3.Command;
import org.wpilib.command3.Mechanism;

public class StateMachine<T> implements Mechanism {
    private T currentState;

    public StateMachine() {
        setDefaultCommand(run(co -> {
            while (true) {
                updateStateMachine();
                co.yield();
            }
        }).named("StateMachine Update Loop"));
    }

    public final T getState() {
        if (currentState == null) {
            throw new IllegalStateException("Current state is not set.");
        }

        return currentState;
    }

    public final void setState(T newState) {
        if (newState == null) {
            throw new IllegalArgumentException("New state cannot be null.");
        }

        if (this.currentState != newState) {
            this.currentState = newState;
            onTransition(newState);
        }
    }

    public final boolean isInState(T state) {
        if (state == null) {
            throw new IllegalArgumentException("State to check cannot be null.");
        }

        return getState().equals(state);
    }

    public void onTransition(T newState) {
        // Override this method to handle actions when transitioning to a new state
    }

    public void periodic(T currentState) {
        // Override this method to handle periodic actions based on the current state
    }

    public T getNextState(T currentState) {
        // Override this method to define the logic for determining the next state
        return null;
    }

    private void updateStateMachine() {
        if (currentState != null) {
            periodic(getState());
        }
    }

    public Command setStateCommand(T newState) {
        return Command.noRequirements(co -> setState(newState)).named("Set State to " + newState);
    }
}