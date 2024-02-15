package main.metamodel;

import java.util.ArrayList;
import java.util.List;

public class Machine {
	private List<State> states = new ArrayList<State>();
	private State initialState = null;

	public Machine(List<State> states, State initialState) {
		if (initialState == null) {
			initialState = states.get(0);
		}
		this.initialState = initialState;
		this.states = states;
	}

	public List<State> getStates() {
		return states;
	}

	public State getInitialState() {
		return initialState;
	}

	public State getState(String string) {
		return states.stream().filter(state -> state.getName().equals(string)).findFirst().orElse(null);
	}

	public int numberOfIntegers() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean hasInteger(String string) {
		// TODO Auto-generated method stub
		return false;
	}
}
