package gui;

import domain.Equipo;

import javax.swing.*;
import java.awt.*;

public class VentanaEquipos extends JFrame {

    private DefaultListModel<Equipo> model = new DefaultListModel<>();

    public VentanaEquipos(JFrame parent) {
        setTitle("Gestión de Equipos");
        setSize(500, 400);
        setLocationRelativeTo(parent);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel p = new JPanel(new BorderLayout(5,5));
        JList<Equipo> lista = new JList<>(model);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        p.add(new JScrollPane(lista), BorderLayout.CENTER);

        JPanel botones = new JPanel();
        JButton add = new JButton("Añadir equipo");
        JButton borrar = new JButton("Borrar seleccionado");
        botones.add(add);
        botones.add(borrar);
        p.add(botones, BorderLayout.SOUTH);

        add(p);

        // Datos de ejemplo
        model.addElement(new Equipo("Real Ejemplo", "Ciudad A"));
        model.addElement(new Equipo("Club Demo", "Ciudad B"));

        add.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Nombre del equipo:");
            if (nombre != null && !nombre.trim().isEmpty()) {
                model.addElement(new Equipo(nombre.trim(), "Sin sede"));
            }
        });

        borrar.addActionListener(e -> {
            int idx = lista.getSelectedIndex();
            if (idx >= 0) model.remove(idx);
            else JOptionPane.showMessageDialog(this, "Selecciona un equipo primero.");
        });
    }
}
