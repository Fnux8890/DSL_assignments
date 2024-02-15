package main;

import java.util.ArrayList;
import java.util.List;

import main.metamodel.Machine;
import main.metamodel.State;

public class StateMachine {	
	private Machine machine = null;
	private List<State> states = new ArrayList<State>();
	private State initialState = new State();
	

	public Machine build() {
		State iniState = states.isEmpty() 
				? new State() 
				: states.get(0);
		machine = new Machine(states, iniState);
		return machine;
	}
	
	//I assume that this adds a state to the machine list of states
	public StateMachine state(String string) {
		State newState = new State();
		states.add(newState);
		return this;
	}

	public StateMachine initial() {
		initialState = states.get(states.size()-1);
		return this;
	}

	public StateMachine when(String string) {
		// TODO Auto-generated method stub
		return null;
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
