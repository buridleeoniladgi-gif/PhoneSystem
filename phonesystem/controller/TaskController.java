package controller;

import java.util.ArrayList;
import java.util.List;
import model.Task;
import model.TaskManager;

/** Connects the Phone System interface to the model. */
public class TaskController {
    private final TaskManager model;

    public TaskController(TaskManager model) { this.model = model; }

    public boolean handleAddTask(String brand, String unit, String price, String specs) {
        if (blank(brand) || blank(unit) || blank(price) || blank(specs)) return false;
        Task task = new Task();
        task.setBrand(brand.trim());
        task.setUnit(unit.trim());
        task.setPrice(price.trim());
        task.setSpecs(specs.trim());
        model.addTask(task);
        return model.saveTasks() == 0;
    }

    public boolean handleUpdateTask(String snum, String brand, String unit, String price, String specs) {
        if (blank(snum) || blank(brand) || blank(unit) || blank(price) || blank(specs)) return false;
        Task task = model.findTask(snum);
        if (task == null) return false;
        task.setBrand(brand.trim());
        task.setUnit(unit.trim());
        task.setPrice(price.trim());
        task.setSpecs(specs.trim());
        model.setTask(task);
        return model.saveTasks() == 0;
    }

    public boolean handleDeleteTask(String snum) {
        if (model.deleteTask(snum) == null) return false;
        return model.saveTasks() == 0;
    }

    public List<Task> getAllTask() {
        return new ArrayList<>(model.findAll().values());
    }

    public Task getTaskDetails(String snum) { return model.findTask(snum); }

    private boolean blank(String value) { return value == null || value.trim().isEmpty(); }
}
