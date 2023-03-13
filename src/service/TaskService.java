package service;

import java.util.List;

import database.TaskDBConnection;
import model.Task;

public class TaskService {

	private TaskDBConnection taskDBConnection;

	public TaskService(TaskDBConnection taskDBConnection) {
		this.taskDBConnection = taskDBConnection;
	}

	public List<Task> getAllTasks() throws Exception {
		if (taskDBConnection.getAllTasks().size() == 0)
			throw new Exception("No tasks added yet!");
		return this.taskDBConnection.getAllTasks();
	}

	public List<Task> getAllCompletedTasks(String email) throws Exception {
		if (taskDBConnection.getAllCompletedTasks(email).size() == 0)
			throw new Exception("No tasks completed yet!");
		return this.taskDBConnection.getAllCompletedTasks(email);
	}

	public List<Task> getAllIncompletedTasks(String email) throws Exception {
		if (taskDBConnection.getAllIncompletedTasks(email).size() == 0)
			throw new Exception("No task is incomplete!");
		return this.taskDBConnection.getAllIncompletedTasks(email);
	}

	public boolean addTask(Task task) throws Exception {
		if (task.getTaskTitle() == null || task.getTaskTitle().isBlank())
			throw new Exception("Task Title cannot be empty or null!");
		try {
			taskDBConnection.insertTask(task);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return true;
	}

	public boolean updateTask(Task task) throws Exception {
		try {
			this.taskDBConnection.updateTask(task);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return true;
	}

	public boolean deleteTask(int taskId) throws Exception {
		try {
			this.taskDBConnection.deleteTask(taskId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return true;
	}

	public Task getTaskById(int taskId) throws Exception {
		Task task = null;
		try {
			task = this.taskDBConnection.getTaskById(taskId);
			if (task == null)
				throw new Exception("Task with taskId: " + taskId + " does not exist");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return task;
	}
}
