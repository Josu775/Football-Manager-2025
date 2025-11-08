package gui;

import domain.Equipo;
import domain.Jugador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SquadWindow extends JFrame {

    public SquadWindow(JFrame parent, Equipo equipo) {
        super("Plantilla - " + equipo.getNombre());
        setSize(700, 500);
        setLocationRelativeTo(parent);
        init(equipo);
        setVisible(true);
    }

    private void init(Equipo equipo) {
        String[] cols = {"#", "Nombre", "Posición", "Edad", "Valoración"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = new JTable(model);
        for (int i = 0; i < equipo.getOnceTitular().size(); i++) {
            Jugador j = equipo.getOnceTitular().get(i);
            model.addRow(new Object[]{i+1, j.getNombre(), j.getPosicion(), j.getEdad(), String.format("%.1f", j.getValoracion())});
        }
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel south = new JPanel();
        JButton btnRemove = new JButton("Quitar seleccionado (sustituir)");
        JButton btnClose = new JButton("Cerrar");
        south.add(btnRemove);
        south.add(btnClose);
        add(south, BorderLayout.SOUTH);

        btnRemove.addActionListener(e -> {
            int sel = table.getSelectedRow();
            if (sel < 0) { JOptionPane.showMessageDialog(this, "Selecciona un jugador."); return; }
            int modelRow = table.convertRowIndexToModel(sel);
            Jugador eliminado = equipo.getOnceTitular().remove(modelRow);
            JOptionPane.showMessageDialog(this, "Jugador retirado temporalmente: " + eliminado.getNombre());
            model.removeRow(modelRow);
            // renumerar filas
            for (int r = 0; r < model.getRowCount(); r++) model.setValueAt(r+1, r, 0);
        });

        btnClose.addActionListener(e -> dispose());
    }
}
