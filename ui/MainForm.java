/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import controller.TaskController;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import model.Task;
import model.TaskManager;

/**
 *
 * @author erl
 */
public class MainForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainForm.class.getName());
    private TaskController controller;
    private final TaskTableModel tableModel = new TaskTableModel();
    private String selectedSnum;

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        this(createDefaultController());
    }

    public MainForm(TaskController controller) {
        initComponents();
        this.controller = controller;
        configureTable();
        jButtonSave.addActionListener(evt -> saveRecord());
        jButtonDelete.addActionListener(evt -> deleteRecord());
        jTableListofTable.getSelectionModel().addListSelectionListener(this::tableSelectionChanged);
        jButtonDelete.setEnabled(false);
        refreshTable();
    }

    private static TaskController createDefaultController() {
        TaskManager manager = new TaskManager();
        manager.loadTasks();
        return new TaskController(manager);
    }

    private void configureTable() {
        jTableListofTable.setModel(tableModel);
        jTableListofTable.getTableHeader().setReorderingAllowed(false);
    }

    private void refreshTable() {
        List<Task> records = controller.getAllTask();
        tableModel.setTasks(records);
    }

    private void saveRecord() {
        String brand = jTextFieldBrand.getText().trim();
        String unit = jTextFieldUnit.getText().trim();
        String price = jTextFieldPrice.getText().trim();
        String specs = jTextAreaspecs.getText().trim();

        boolean saved;
        if (selectedSnum == null) {
            saved = controller.handleAddTask(brand, unit, price, specs);
        } else {
            saved = controller.handleUpdateTask(selectedSnum, brand, unit, price, specs);
        }

        if (!saved) {
            JOptionPane.showMessageDialog(this,
                    "Please complete all fields and try again.",
                    "Phone System", JOptionPane.WARNING_MESSAGE);
            return;
        }

        clearForm();
        refreshTable();
        JOptionPane.showMessageDialog(this, "Record saved successfully.",
                "Phone System", JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteRecord() {
        if (selectedSnum == null) {
            JOptionPane.showMessageDialog(this, "Please select a record to delete.");
            return;
        }

        int answer = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this record?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            if (controller.handleDeleteTask(selectedSnum)) {
                clearForm();
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Unable to delete the selected record.");
            }
        }
    }

    private void tableSelectionChanged(ListSelectionEvent event) {
        if (event.getValueIsAdjusting()) return;
        int row = jTableListofTable.getSelectedRow();
        if (row < 0) {
            selectedSnum = null;
            jButtonDelete.setEnabled(false);
            jButtonSave.setText("Save");
            return;
        }

        int modelRow = jTableListofTable.convertRowIndexToModel(row);
        selectedSnum = tableModel.getSnum(modelRow);
        Task task = controller.getTaskDetails(selectedSnum);
        if (task != null) {
            jTextFieldBrand.setText(task.getBrand());
            jTextFieldUnit.setText(task.getUnit());
            jTextFieldPrice.setText(task.getPrice());
            jTextAreaspecs.setText(task.getSpecs());
            jButtonDelete.setEnabled(true);
            jButtonSave.setText("Update");
        }
    }

    private void clearForm() {
        selectedSnum = null;
        jTextFieldBrand.setText("");
        jTextFieldUnit.setText("");
        jTextFieldPrice.setText("");
        jTextAreaspecs.setText("");
        jTableListofTable.clearSelection();
        jButtonDelete.setEnabled(false);
        jButtonSave.setText("Save");
    }
    private void insertionSort() {

    List<Task> tasks =
            new java.util.ArrayList<>(controller.getAllTask());

    if (tasks.isEmpty()) {
        JOptionPane.showMessageDialog(this,
                "No phone records available!");
        return;
    }

    // Insertion Sort by Brand (A-Z)
    for (int i = 1; i < tasks.size(); i++) {

        Task key = tasks.get(i);
        int j = i - 1;

        while (j >= 0 &&
                tasks.get(j).getBrand().compareToIgnoreCase(
                        key.getBrand()) > 0) {

            tasks.set(j + 1, tasks.get(j));
            j--;
        }

        tasks.set(j + 1, key);
    }

    // Update JTable
    tableModel.setTasks(tasks);

    JOptionPane.showMessageDialog(this,
            "Records successfully sorted by Brand!",
            "Insertion Sort",
            JOptionPane.INFORMATION_MESSAGE);
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldBrand = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldUnit = new javax.swing.JTextField();
        jTextFieldPrice = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaspecs = new javax.swing.JTextArea();
        jButtonSave = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableListofTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Brand:");

        jLabel2.setText("Unit:");

        jLabel3.setText("Price:");

        jLabel4.setText("Specs:");

        jTextAreaspecs.setColumns(20);
        jTextAreaspecs.setRows(5);
        jScrollPane1.setViewportView(jTextAreaspecs);

        jButtonSave.setText("Save");

        jButtonDelete.setText("Delete");

        jButton2.setText("jButton2");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPrice))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldUnit))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldBrand))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonDelete)
                        .addGap(73, 73, 73)))
                .addGap(75, 75, 75)
                .addComponent(jButton2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSave)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonDelete)
                        .addComponent(jButton2)))
                .addContainerGap(333, Short.MAX_VALUE))
        );

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Phone System");

        jTableListofTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTableListofTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        insertionSort();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            MainForm form = new MainForm();
            form.setLocationRelativeTo(null);
            form.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableListofTable;
    private javax.swing.JTextArea jTextAreaspecs;
    private javax.swing.JTextField jTextFieldBrand;
    private javax.swing.JTextField jTextFieldPrice;
    private javax.swing.JTextField jTextFieldUnit;
    // End of variables declaration//GEN-END:variables
}
