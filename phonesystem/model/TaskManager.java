package model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import util.IdGenerator;

/** Stores phone records and handles persistence. */
public class TaskManager {
    private HashMap<String, Task> tasks = new HashMap<>();
    private final String filePath = "phonesystem.ser";

    public synchronized void addTask(Task task) {
        String id;
        do { id = IdGenerator.generateId(); } while (tasks.containsKey(id));
        task.setSnum(id);
        tasks.put(id, task);
    }

    public synchronized Task deleteTask(String id) { return tasks.remove(id); }
    public synchronized Task findTask(String id) { return tasks.get(id); }
    public synchronized HashMap<String, Task> findAll() { return new HashMap<>(tasks); }

    public synchronized void setTask(Task task) {
        if (task != null && task.getSnum() != null && tasks.containsKey(task.getSnum())) {
            tasks.put(task.getSnum(), task);
        }
    }

    @SuppressWarnings("unchecked")
    public synchronized void loadTasks() {
        File file = new File(filePath);
        if (!file.isFile()) return;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Object data = in.readObject();
            if (data instanceof HashMap<?, ?>) {
                tasks = (HashMap<String, Task>) data;
            }
        } catch (IOException | ClassNotFoundException | ClassCastException ex) {
            System.err.println("Could not load saved phone records: " + ex.getMessage());
        }
    }

    public synchronized int saveTasks() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(tasks);
            return 0;
        } catch (IOException ex) {
            System.err.println("Could not save phone records: " + ex.getMessage());
            return 1;
        }
    }
}
