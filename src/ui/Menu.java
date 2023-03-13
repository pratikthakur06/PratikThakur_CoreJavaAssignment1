package ui;

import java.util.Scanner;

import database.TaskDBConnection;
import database.UserDBConnection;
import model.Task;
import model.User;
import service.TaskService;
import service.UserService;

public class Menu {

	public static void main(String[] args) {
		UserDBConnection userDBConnection = new UserDBConnection();
		TaskDBConnection taskDBConnection = new TaskDBConnection();

		UserService userService = new UserService(userDBConnection);
		TaskService taskService = new TaskService(taskDBConnection);

		Scanner sc = new Scanner(System.in);

		int choice;
		String email, password, name;
		boolean flag = true;

		do {
			System.out.println("Select Operation\n1. Login\n2. Register\n0. Exit");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				// Login Part
				System.out.println("Please enter email id: ");
				email = sc.nextLine();
				System.out.println("Please enter password");
				password = sc.nextLine();
				try {
					if (userService.validateCredentials(email, password)) {
						System.out.println("Login Successful!");
						dashboard(sc, userService, taskService, email);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case 2:
				System.out.println("Please enter details to register");
				System.out.println("enter email");
				email = sc.next();
				System.out.println("Enter your name");
				name = sc.next();
				System.out.println("Enter your password");
				password = sc.next();
				User u1 = new User(name, email, password);
				try {
					userService.registerUser(u1);
					System.out.println("User Registered Successfully!");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;

			case 0:
				System.out.println("Thanks exiting the application");
				flag = false;
				break;

			default:
				System.out.println("Wrong option");
				break;
			}
		} while (flag);
	}

	public static void todoMenu() {
		System.out.println(
				"Select Option:\n1. View All Tasks\n2. Add New Task\n3. Update Task\n4. Delete Task\n5. Search Task\n6. View All Completed Tasks\n7. View All Uncompleted Tasks\n8. Logout\n");
	}

	public static void dashboard(Scanner sc, UserService customerService, TaskService taskService, String email) {

		boolean flag = true;
		do {
			System.out.println("\n ***************** Todo Manager Dashboard ***************");
			todoMenu();
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				try {
					for (Task task : taskService.getAllTasks())
						System.out.println(task);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;

			case 2:
				System.out.println("Enter the task title: ");
				String taskTitle = sc.nextLine();
				System.out.println("Enter the task description: ");
				String taskText = sc.nextLine();
				System.out.println("Enter the task completion status(true/false): ");
				String taskCompleted = sc.nextLine();
				try {
					Task task = new Task(taskTitle, taskText, email, taskCompleted);
					taskService.addTask(task);
					System.out.println("Task created!");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;

			case 3:
				System.out.println("Edit task!");
				System.out.println("Enter task id: ");
				int taskId = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter task title: ");
				taskTitle = sc.nextLine();
				System.out.println("Enter task description: ");
				taskText = sc.nextLine();
				System.out.println("Enter task completion status(true/false): ");
				taskCompleted = sc.next();
				Task t1 = new Task();
				t1.setTaskId(taskId);
				t1.setTaskTitle(taskTitle);
				t1.setTaskText(taskText);
				t1.setAssignedTo(email);
				t1.setTaskCompleted(taskCompleted);
				try {
					taskService.updateTask(t1);
					System.out.println("Task updated successfully!");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;

			case 4:
				System.out.println("Enter task id: ");
				taskId = sc.nextInt();
				try {
					taskService.deleteTask(taskId);
					System.out.println("Task deleted successfully!");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;

			case 5:
				System.out.println("Enter task id: ");
				taskId = sc.nextInt();
				try {
					Task task = taskService.getTaskById(taskId);
					System.out.println(task);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;

			case 6:
				try {
					for (Task task : taskService.getAllCompletedTasks(email))
						System.out.println(task);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;

			case 7:
				try {
					for (Task task : taskService.getAllIncompletedTasks(email))
						System.out.println(task);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;

			case 8:
				System.out.println("You are successfully logged out!");
				flag = false;
				break;

			default:
				System.out.println("Wrong choice");
				break;
			}
		} while (flag);
	}
}
