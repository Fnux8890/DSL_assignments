package main.metamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Machine {
	private final List<State> states;
	private final Map<String, Integer> integers;
	private final State initialState;

	// Constructor to initialize the machine with states and initial state
	public Machine(List<State> states, State initialState, Map<String, Integer> integers) {
		this.integers = Collections.unmodifiableMap(new HashMap<>(integers));
		this.states = Collections.unmodifiableList(new ArrayList<>(states));
		this.initialState = initialState;
	}

	public List<State> getStates() {
		return states;
	}

	public State getInitialState() {
		return initialState;
	}

	public State getState(String string) {
		return states.stream()
				.filter(state -> state.getName().equals(string))
				.findFirst()
				.orElse(new State(string, new ArrayList<>()));
	}

	public int numberOfIntegers() {
		return integers.size();
	}

	public boolean hasInteger(String string) {
		return integers.containsKey(string);
	}

	public Map<String, Integer> getIntegers() {
		return integers;
	}

}
