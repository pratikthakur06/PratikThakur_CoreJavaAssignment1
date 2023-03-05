package model;

public class Task {

	private int taskId;
	private String taskTitle;
	private String taskText;
	private String assignedTo;
	private String taskCompleted;
	
	public Task(String taskTitle, String taskText, String assignedTo, String taskCompleted) {
		super();
		this.taskTitle = taskTitle;
		this.taskText = taskText;
		this.assignedTo = assignedTo;
		this.taskCompleted = taskCompleted;
	}
	
	public Task() {
		// TODO Auto-generated constructor stub
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskText() {
		return taskText;
	}

	public void setTaskText(String taskText) {
		this.taskText = taskText;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String isTaskCompleted() {
		return taskCompleted;
	}

	public void setTaskCompleted(String taskCompleted) {
		this.taskCompleted = taskCompleted;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskTitle=" + taskTitle + ", taskText=" + taskText + ", assignedTo="
				+ assignedTo + ", taskCompleted=" + taskCompleted + "]";
	}
}
