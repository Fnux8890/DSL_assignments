package main;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import main.metamodel.Machine;
import main.metamodel.State;

public class StateMachine {
	private Machine machine = null;
	private List<State> states = new ArrayList<State>();
	private State initialState = null;

	public Machine build() {
		machine = new Machine(states, initialState);
		return machine;
	}

	// I assume that this adds a state to the machine list of states
	public StateMachine state(String string) {
		State newState = new State(string);
		states.add(newState);
		return this;
	}

	public StateMachine initial() {
		initialState = states.get(states.size() - 1);
		return this;
	}

	public StateMachine when(String string) {
		// regex to check if string contains "change to"
		Pattern pattern = Pattern.compile("change to");
		// if string contains "change to"

		if (pattern.matcher(string).find()) {
			// split string into two parts
			String[] parts = string.split("change to");
			String stateName = parts[1].trim();
			String eventName = parts[0].trim();
			State currentState = states.get(states.size() - 1);
			State targetState = machine.getState(stateName);
			currentState.addTransition(eventName, targetState);
		}
		return this;
	}

	public StateMachine to(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public StateMachine integer(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public StateMachine set(String string, int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public StateMachine increment(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public StateMachine decrement(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public StateMachine ifEquals(String string, int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public StateMachine ifGreaterThan(String string, int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public StateMachine ifLessThan(String string, int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
