package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

public class MachineInterpreter {
    private Machine machine;
    private State currentState;

    private final Map<String, Integer> integers = new HashMap<>();

    public void run(Machine m) {
        machine = m;
        currentState = machine.getInitialState();

        machine.getIntegers().forEach((k, v) -> integers.put(k, v));
    }

    public State getCurrentState() {
        return currentState;
    }

    public void processEvent(String eventName) {
        List<Transition> possibleTransitions = currentState.getTransitions().stream()
                .filter(t -> eventName.equals(t.getEvent()))
                .collect(Collectors.toList());

        for (Transition transition : possibleTransitions) {
            System.out.println("Checking transition from " + currentState.getName() + " to "
                    + transition.getTarget().getName() + " on event " + eventName);
            System.out.println("Operation type: " + transition.getOperationType());
            System.out.println("Event name: " + eventName);

            if (canTransition(transition)) {
                System.out.println("Executing transition from " + currentState.getName() + " to "
                        + transition.getTarget().getName() + " on event " + eventName);
                executeTransition(transition);
                break; // Exit the loop after executing the first valid transition
            }
        }
    }

    public int getInteger(String variableName) {
        return integers.getOrDefault(variableName, 0);
    }

    private boolean canTransition(Transition transition) {
        // Check condition
        Transition.ConditionType type = transition.getConditionType();
        if (type == null || type == Transition.ConditionType.NONE) {
            return true; // No condition to evaluate
        }

        int variableValue = integers.getOrDefault(transition.getConditionVariableName(), 0);
        switch (transition.getConditionType()) {
            case EQUALS:
                return variableValue == transition.getConditionComparedValue();
            case GREATER_THAN:
                return variableValue > transition.getConditionComparedValue();
            case LESS_THAN:
                return variableValue < transition.getConditionComparedValue();
            default:
                return false; // Should not reach here if all enums are handled
        }
    }

    private void executeTransition(Transition transition) {
        // Update variables if needed
        if (transition.hasOperation()) {
            String variableName = transition.getOperationVariableName().toString();
            Integer operationValue = transition.getOperationValue();

            switch (transition.getOperationType()) {
                case SET:
                    integers.put(variableName, operationValue);
                    break;
                case INCREMENT:
                    integers.merge(variableName, 1, Integer::sum);
                    break;
                case DECREMENT:
                    integers.merge(variableName, -1, Integer::sum);
                    break;
                default:
                    break;
            }
        }

        // Move to the next state
        currentState = transition.getTarget();
    }

}
