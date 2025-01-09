package Workshpo_project;
import java.io.*;
import java.util.*;
class Task {
    private int id;
    private String title;
    private String description;
    private String deadline;
    private boolean isCompleted;
    public Task(int id, String title, String description, String deadline) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.isCompleted = false;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDeadline() {
        return deadline;
    }
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
    public boolean isCompleted() {
        return isCompleted;
    }
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    public void display() {
        System.out.println("ID: " + id + "\n Title: " + title + "\n Description: " + description +
                           "\n Deadline: " + deadline + " \nCompleted: " + (isCompleted ? "Yes" : "No"));
    }
    public void saveToFile() {
        try (FileWriter fw = new FileWriter("tasks.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(id + "," + title + "," + description + "," + deadline + "," + isCompleted);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Task> loadFromFile() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("tasks.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Task task = new Task(Integer.parseInt(data[0]), data[1], data[2], data[3]);
                task.setCompleted(Boolean.parseBoolean(data[4]));
                tasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
public class TodoListApp {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        List<Task> tasks = Task.loadFromFile();
        while (true) {
            System.out.println("To-Do List Application");
            System.out.println("1. Add Task");
            System.out.println("2. Update Task");
            System.out.println("3. Delete Task");
            System.out.println("4. Mark Task as Completed");
            System.out.println("5. Display All Tasks");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    addTask(tasks);
                    break;
                case 2:
                    updateTask(tasks);
                    break;
                case 3:
                    deleteTask(tasks);
                    break;
                case 4:
                    markTaskCompleted(tasks);
                    break;
                case 5:
                    displayAllTasks(tasks);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
    private static void addTask(List<Task> tasks) {
        System.out.print("Enter Task Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Task Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Task Deadline (YYYY-MM-DD): ");
        String deadline = scanner.nextLine();
        int id = tasks.size() + 1; 
        Task newTask = new Task(id, title, description, deadline);
        newTask.saveToFile();
        tasks.add(newTask);
        System.out.println("Task added successfully!");
    }
    private static void updateTask(List<Task> tasks) {
        System.out.print("Enter Task ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        Task taskToUpdate = null;
        for (Task task : tasks) {
            if (task.getId() == id) {
                taskToUpdate = task;
                break;
            }
        }
        if (taskToUpdate != null) {
            System.out.print("Enter new Title: ");
            String newTitle = scanner.nextLine();
            System.out.print("Enter new Description: ");
            String newDescription = scanner.nextLine();
            System.out.print("Enter new Deadline (YYYY-MM-DD): ");
            String newDeadline = scanner.nextLine();
            taskToUpdate.setTitle(newTitle);
            taskToUpdate.setDescription(newDescription);
            taskToUpdate.setDeadline(newDeadline);
            System.out.println("Task updated successfully!");
        } else {
            System.out.println("Task not found!");
        }
    }
    private static void deleteTask(List<Task> tasks) {
        System.out.print("Enter Task ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Task taskToDelete = null;
        for (Task task : tasks) {
            if (task.getId() == id) {
                taskToDelete = task;
                break;
            }
        }
        if (taskToDelete != null) {
            tasks.remove(taskToDelete);
            System.out.println("Task deleted successfully!");
            rewriteTaskFile(tasks);
        } else {
            System.out.println("Task not found!");
        }
    }
    private static void markTaskCompleted(List<Task> tasks) {
        System.out.print("Enter Task ID to mark as completed: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        Task taskToMark = null;
        for (Task task : tasks) {
            if (task.getId() == id) {
                taskToMark = task;
                break;
            }
        }
        if (taskToMark != null) {
            taskToMark.setCompleted(true);
            System.out.println("Task marked as completed!");
            rewriteTaskFile(tasks);
        } else {
            System.out.println("Task not found!");
        }
    }
    private static void displayAllTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to display.");
        } else {
            for (Task task : tasks) {
                task.display();
            }
        }
    }
    private static void rewriteTaskFile(List<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("tasks.txt"))) {
            for (Task task : tasks) {
                bw.write(task.getId() + "," + task.getTitle() + "," + task.getDescription() + "," +
                         task.getDeadline() + "," + task.isCompleted());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
