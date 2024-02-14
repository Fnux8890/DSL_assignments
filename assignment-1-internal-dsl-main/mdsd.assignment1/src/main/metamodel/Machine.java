package main.metamodel;

import java.util.ArrayList;
import java.util.List;

public class Machine {
	private List<State> states = new ArrayList<>(); 
	private State initialState = null;

	public List<State> getStates() {
		return states;
	}

	public State getInitialState() {
		return initialState;
	}

	public State getState(String string) {
		// TODO Auto-generated method stub
		return null;
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

