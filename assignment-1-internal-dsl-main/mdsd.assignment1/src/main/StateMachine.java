package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

public class StateMachine {
	private Machine machine;
	private final List<State> states = new ArrayList<State>();
	private final Map<String, Integer> integers = new HashMap<>();
	private State initialState = null;
	private State currentState = null;
	private String pendingEventName;

	public Machine build() {
		machine = new Machine(states, initialState, integers);
		states.clear();
		integers.clear();
		initialState = null;
		currentState = null;
		pendingEventName = null;

		return machine;
	}

	// I assume that this adds a state to the machine list of states
	public StateMachine state(String string) {
		// Find existing state or create a new one if it doesn't exist
		currentState = states.stream()
				.filter(state -> state.getName().equals(string))
				.findFirst()
				.orElseGet(() -> {
					State newState = new State(string, new ArrayList<>());
					states.add(newState);
					return newState;
				});
		return this;
	}

	public StateMachine initial() {
		initialState = currentState;
		return this;
	}

	public StateMachine when(String string) {
		pendingEventName = string;
		return this;
	}

	public StateMachine to(String stateName) {
		State targetState = states.stream()
				.filter(s -> s.getName().equals(stateName))
				.findFirst()
				.orElseGet(() -> {
					State newState = new State(stateName, new ArrayList<>());
					states.add(newState);
					return newState;
				});

		if (currentState != null && pendingEventName != null) {
			Transition transition = new Transition(pendingEventName, targetState);
			currentState.getTransitions().add(transition);
		}

		// Clear the pending event name after creating the transition.
		pendingEventName = null;

		return this;
	}

	public StateMachine integer(String string) {
		integers.put(string, 0);
		return this;
	}

	public StateMachine set(String string, int i) {
		addOperationToLastTransition(string, Transition.OperationType.SET, i, null, null);
		return this;
	}

	public StateMachine increment(String string) {
		addOperationToLastTransition(string, Transition.OperationType.INCREMENT, null, null, null);
		return this;
	}

	public StateMachine decrement(String string) {
		addOperationToLastTransition(string, Transition.OperationType.DECREMENT, null, null, null);
		return this;
	}

	public StateMachine ifEquals(String string, int i) {
		updateLastTransitionCondition(string, Transition.ConditionType.EQUALS, i);
		return this;
	}

	public StateMachine ifGreaterThan(String string, int i) {
		updateLastTransitionCondition(string, Transition.ConditionType.GREATER_THAN, i);
		return this;
	}

	public StateMachine ifLessThan(String string, int i) {
		updateLastTransitionCondition(string, Transition.ConditionType.LESS_THAN, i);
		return this;
	}

	private void addOperationToLastTransition(
			String variableName,
			Transition.OperationType operationType,
			Integer value,
			Transition.ConditionType conditionType,
			Integer conditionValue) {
		if (currentState == null ||
				currentState.getTransitions().isEmpty()) {

			throw new IllegalStateException("No current state or transitions defined.");
		}
		Transition lastTransition = currentState.getTransitions().get(currentState.getTransitions().size() - 1);
		lastTransition.updateOperationAndCondition(variableName, operationType, value, conditionType, conditionValue);
	}

	private void updateLastTransitionCondition(
			String variableName,
			Transition.ConditionType conditionType,
			int value) {

		if (currentState != null &&
				!currentState.getTransitions().isEmpty()) {

			Transition lastTransition = currentState.getTransitions().get(currentState.getTransitions().size() - 1);
			lastTransition.updateCondition(variableName, conditionType, value);

		}
	}

}
