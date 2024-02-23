package main.metamodel;

public class Transition {
	private final String eventName;
	private final State targetState;
	private String operationTarget;
	private Integer operationValue = null;
	private OperationType operationType = OperationType.NONE;
	private ConditionType conditionType = ConditionType.NONE;
	private Integer conditionValue = null;

	// Enum for operation types
	public enum OperationType {
		SET, INCREMENT, DECREMENT, NONE
	}

	// Enum for condition types
	public enum ConditionType {
		EQUALS, GREATER_THAN, LESS_THAN, NONE
	}

	public Transition(
			String eventName,
			State targetState,
			String operationTarget,
			OperationType operationType,
			Integer operationValue,
			ConditionType conditionType,
			Integer conditionValue) {
		this.eventName = eventName;
		this.targetState = targetState;
		this.operationTarget = operationTarget;
		this.operationType = operationType;
		this.operationValue = operationValue;
		this.conditionType = conditionType == null ? ConditionType.NONE : conditionType;
		this.conditionValue = conditionValue;
	}

	public Transition(String eventName, State targetState) {
		this(eventName, targetState, null, OperationType.NONE, null, ConditionType.NONE, null);
	}

	public void updateCondition(
			String operationTarget,
			ConditionType conditionType,
			Integer conditionValue) {
		this.operationTarget = operationTarget;
		this.conditionType = conditionType;
		this.conditionValue = conditionValue;
	}

	public void updateOperationAndCondition(
			String operationTarget,
			OperationType operationType,
			Integer operationValue,
			ConditionType conditionType,
			Integer conditionValue) {
		this.operationTarget = operationTarget;
		this.operationType = operationType;
		this.operationValue = operationValue;
		this.conditionType = conditionType;
		this.conditionValue = conditionValue;
	}

	public Object getEvent() {
		return eventName;
	}

	public State getTarget() {
		return targetState;
	}

	public boolean hasSetOperation() {
		return operationType == OperationType.SET;
	}

	public boolean hasIncrementOperation() {
		return operationType == OperationType.INCREMENT;
	}

	public boolean hasDecrementOperation() {
		return operationType == OperationType.DECREMENT;
	}

	public Object getOperationVariableName() {
		return operationTarget;
	}

	public Integer getOperationValue() {
		return operationValue;
	}

	public boolean isConditional() {
		return conditionType != ConditionType.NONE;
	}

	public Object getConditionVariableName() {
		return operationTarget;
	}

	public Integer getConditionComparedValue() {
		return conditionValue;
	}

	public boolean isConditionEqual() {
		return conditionType == ConditionType.EQUALS;
	}

	public boolean isConditionGreaterThan() {
		return conditionType == ConditionType.GREATER_THAN;
	}

	public boolean isConditionLessThan() {
		return conditionType == ConditionType.LESS_THAN;
	}

	public boolean hasOperation() {
		return operationType != OperationType.NONE;
	}

	public ConditionType getConditionType() {
		return conditionType;
	}

	public OperationType getOperationType() {
		return operationType;
	}

}
