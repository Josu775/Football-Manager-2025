package gui;

import domain.Equipo;
import domain.Jugador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MarketWindow extends JFrame {

    private static final Random RNG = new Random();

    public MarketWindow(JFrame parent, Equipo targetTeam) {
        super("Mercado - " + targetTeam.getNombre());
        setSize(700, 500);
        setLocationRelativeTo(parent);
        init(targetTeam);
        setVisible(true);
    }

    private void init(Equipo targetTeam) {
        String[] cols = {"Nombre", "Posición", "Edad", "Valoración"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // generamos lista de agentes libres
        List<Jugador> freeAgents = generateFreeAgents(12, targetTeam.getValoracion());
        for (Jugador j : freeAgents) {
            model.addRow(new Object[]{j.getNombre(), j.getPosicion(), j.getEdad(), String.format("%.1f", j.getValoracion())});
        }

        JPanel south = new JPanel();
        JButton btnSign = new JButton("Fichar seleccionado");
        JButton btnClose = new JButton("Cerrar");
        south.add(btnSign);
        south.add(btnClose);
        add(south, BorderLayout.SOUTH);

        btnSign.addActionListener(e -> {
            int sel = table.getSelectedRow();
            if (sel < 0) { JOptionPane.showMessageDialog(this, "Selecciona un jugador."); return; }
            // crear jugador nuevo y añadir al equipo
            String nombre = (String) model.getValueAt(sel, 0);
            String pos = (String) model.getValueAt(sel, 1);
            int edad = Integer.parseInt(model.getValueAt(sel, 2).toString());
            double val = Double.parseDouble(model.getValueAt(sel, 3).toString());
            Jugador fichado = new Jugador(nombre, pos, edad, val);
            // añadir a once titular para simplificar (real FM sería plantilla y mercado)
            targetTeam.getOnceTitular().add(fichado);
            JOptionPane.showMessageDialog(this, "Jugador fichado: " + fichado.getNombre());
            // eliminar de tabla
            model.removeRow(sel);
        });

        btnClose.addActionListener(e -> dispose());
    }

    private List<Jugador> generateFreeAgents(int n, double teamRating) {
        List<Jugador> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = generateName();
            String pos = pickPosition();
            int age = 18 + RNG.nextInt(16);
            double val = Math.max(1.5, Math.min(5.0, teamRating + (RNG.nextDouble() * 1.2 - 0.6)));
            val = Math.round(val * 10.0) / 10.0;
            res.add(new Jugador(name, pos, age, val));
        }
        return res;
    }

    // simple helper name (puedes reusar randomNombre de LeagueData si haces pública)
    private String generateName() {
        String[] names = {"Lucas Pérez","Diego López","Sergio Torres","Álvaro Ruiz","Pablo Martín","Rubén Díaz","Manu García","Hugo Moreno","Iván Ramos","Javier Ortega","Adrián Campos"};
        return names[RNG.nextInt(names.length)] + " " + (100 + RNG.nextInt(900));
    }

    private String pickPosition() {
        String[] pos = {"POR","DEF","MID","ATT"};
        return pos[RNG.nextInt(pos.length)];
    }
}
