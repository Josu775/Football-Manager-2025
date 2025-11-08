package gui;

import domain.Jugador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaJugadores extends JFrame {

    private DefaultTableModel tableModel;

    public VentanaJugadores(JFrame parent) {
        setTitle("Gestión de Jugadores");
        setSize(700, 400);
        setLocationRelativeTo(parent);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        String[] cols = {"Nombre", "Edad", "Posición"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // por ahora no editable
            }
        };
        JTable table = new JTable(tableModel);
        JScrollPane sp = new JScrollPane(table);

        JPanel botones = new JPanel();
        JButton add = new JButton("Añadir jugador");
        JButton del = new JButton("Eliminar");
        botones.add(add);
        botones.add(del);

        add(sp, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        // Datos de ejemplo
        tableModel.addRow(new Object[]{"Juan Pérez", 24, "Delantero"});
        tableModel.addRow(new Object[]{"María Díaz", 22, "Centrocampista"});

        add.addActionListener(e -> {
            JTextField nombre = new JTextField();
            JTextField edad = new JTextField();
            JTextField pos = new JTextField();
            Object[] message = {
                    "Nombre:", nombre,
                    "Edad:", edad,
                    "Posición:", pos
            };
            int option = JOptionPane.showConfirmDialog(this, message, "Nuevo jugador", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    String n = nombre.getText().trim();
                    int eAge = Integer.parseInt(edad.getText().trim());
                    String p = pos.getText().trim();
                    tableModel.addRow(new Object[]{n, eAge, p});
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Edad no válida");
                }
            }
        });

        del.addActionListener(e -> {
            int sel = table.getSelectedRow();
            if (sel >= 0) tableModel.removeRow(sel);
            else JOptionPane.showMessageDialog(this, "Selecciona una fila primero");
        });
    }
}
