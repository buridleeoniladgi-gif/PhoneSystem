package ui;

import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.err.println("Using default look and feel: " + ex.getMessage());
        }

        java.awt.EventQueue.invokeLater(() -> {
            MainForm form = new MainForm();
            form.setLocationRelativeTo(null);
            form.setVisible(true);
        });
    }
}
