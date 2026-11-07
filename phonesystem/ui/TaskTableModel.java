package ui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Task;

/** Table model for phone records. */
public class TaskTableModel extends AbstractTableModel {
    private final String[] columns = {"S/N", "Brand", "Unit", "Price", "Specs"};
    private List<Task> tasks = new ArrayList<>();

    @Override public int getRowCount() { return tasks.size(); }
    @Override public int getColumnCount() { return columns.length; }
    @Override public String getColumnName(int column) { return columns[column]; }

    @Override
    public Object getValueAt(int row, int column) {
        Task task = tasks.get(row);
        return switch (column) {
            case 0 -> task.getSnum();
            case 1 -> task.getBrand();
            case 2 -> task.getUnit();
            case 3 -> task.getPrice();
            case 4 -> task.getSpecs();
            default -> null;
        };
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks == null ? new ArrayList<>() : new ArrayList<>(tasks);
        fireTableDataChanged();
    }

    public String getSnum(int row) { return tasks.get(row).getSnum(); }
}
