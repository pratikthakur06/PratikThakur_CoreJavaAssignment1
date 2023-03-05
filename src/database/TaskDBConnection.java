package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Task;

public class TaskDBConnection {

	Connection connection = DBConnection.getConnection();

	public List<Task> getAllTasks() throws Exception {
		String sql = "select * from task";
		List<Task> tasks = new ArrayList<Task>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Task task = new Task();
				task.setTaskId(rs.getInt(1));
				task.setTaskTitle(rs.getString(2));
				task.setTaskText(rs.getString(3));
				task.setAssignedTo(rs.getString(4));
				task.setTaskCompleted(rs.getString(5));
				tasks.add(task);
			}
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		return tasks;
	}

	public boolean insertTask(Task task) throws Exception {
		String sql = "insert into task values(?,?,?,?)";
		System.out.println(sql);
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, task.getTaskTitle());
			statement.setString(2, task.getTaskText());
			statement.setString(3, task.getAssignedTo());
			statement.setString(4, task.isTaskCompleted());

			statement.execute();
		} catch (SQLException ex) {
			System.out.println("Error while creating new task!");
			throw new Exception(ex.getMessage());
		}
		return true;
	}

	public boolean updateTask(Task task) throws Exception {
		String sql = "update task set taskTitle=?, taskText =?, assignedTo=?, taskCompleted=? where taskId=?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, task.getTaskTitle());
			statement.setString(2, task.getTaskText());
			statement.setString(3, task.getAssignedTo());
			statement.setString(4, task.isTaskCompleted());
			statement.setInt(5, task.getTaskId());
			statement.execute();
		} catch (SQLException ex) {
			System.out.println("Error while updating task!");
			throw new Exception(ex.getMessage());
		}
		return true;
	}

	public boolean deleteTask(int taskId) throws Exception {
		String sql = "delete from task where taskId=?";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, taskId);
			statement.execute();
		} catch (SQLException ex) {
			System.out.println("Error while deleting the task!");
			throw new Exception(ex.getMessage());
		}
		return true;
	}

	public Task getTaskById(int taskId) throws Exception {
		Task task = null;
		String sql = "select taskTitle, taskText, assignedTo, taskCompleted from task where taskId=?";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, taskId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				task = new Task();
				task.setTaskTitle(rs.getString(1));
				task.setTaskText(rs.getString(2));
				task.setAssignedTo(rs.getString(3));
				task.setTaskCompleted(rs.getString(4));
				task.setTaskId(rs.getInt(5));
			} else
				throw new Exception("No task with taskId: " + taskId + " found");
		} catch (SQLException e) {
			throw new Exception(e.getMessage());
		}
		return task;
	}
}
