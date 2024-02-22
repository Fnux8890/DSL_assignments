package main.metamodel;

import java.util.List;

public class State {
	private String name;
	private List<Transition> transitions;

	public State(String string, List<Transition> transitions) {
		this.transitions = transitions;
		this.name = string;
	}

	public Object getName() {
		return name;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public Transition getTransitionByEvent(String string) {
		return transitions.stream().filter(transition -> transition.getEvent().equals(string)).findFirst().orElse(null);
	}
}
