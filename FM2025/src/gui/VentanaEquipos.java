package gui;

import domain.Equipo;
import domain.LeagueManager;

import javax.swing.*;
import java.awt.*;

public class VentanaEquipos extends JFrame {
    private DefaultListModel<Equipo> model = new DefaultListModel<>();
    private LeagueManager manager;

    public VentanaEquipos(JFrame parent, LeagueManager manager) {
        super("Gestión de Equipos");
        this.manager = manager;
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

        // cargar datos desde manager
        model.clear();
        for (Equipo e : manager.getEquipos()) model.addElement(e);

        add.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Nombre del equipo:");
            if (nombre != null && !nombre.trim().isEmpty()) {
                Equipo nuevo = new Equipo(nombre.trim(), "Sin sede");
                manager.addEquipo(nuevo);
                model.addElement(nuevo);
            }
        });

        borrar.addActionListener(e -> {
            int idx = lista.getSelectedIndex();
            if (idx >= 0) {
                Equipo sel = model.get(idx);
                model.remove(idx);
                // opcional: remover del manager (implementa método si lo necesitas)
                manager.getEquipos().remove(sel);
            } else JOptionPane.showMessageDialog(this, "Selecciona un equipo primero.");
        });
    }
}
