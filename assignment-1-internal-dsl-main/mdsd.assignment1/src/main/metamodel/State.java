package main.metamodel;

import java.util.ArrayList;
import java.util.List;

public class State {
	private String stateName = "";
	private List<Transition> transitions = new ArrayList<Transition>();
	
	public State() {
		this.stateName = "";
	}
	
	public State(String stateName) {
		this.stateName = stateName;
	}
	
	public Object getName() {
		return stateName;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public Transition getTransitionByEvent(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
