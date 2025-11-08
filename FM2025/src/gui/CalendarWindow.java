package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import domain.Equipo;
import domain.LeagueData;

/**
 * CalendarWindow simplificada: muestra jornadas y rivales para el equipo seleccionado.
 * (Se ha eliminado la simulación masiva y la clasificación).
 */
public class CalendarWindow extends JFrame {

    private final Equipo equipo;
    private final List<Equipo> allTeams;
    private DefaultTableModel model;

    public CalendarWindow(JFrame parent, Equipo equipo) {
        super("Calendario - " + equipo.getNombre());
        this.equipo = equipo;
        this.allTeams = LeagueData.getLaLiga20(); // lista central
        setSize(800, 520);
        setLocationRelativeTo(parent);
        init();
        setVisible(true);
    }

    private void init() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"Jornada", "Rival", "Local/Visitante"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Generar fixture round-robin (ida y vuelta) y mostrar sólo las filas del equipo seleccionado
        List<Equipo> equipos = new ArrayList<>(allTeams);
        if (equipos.size() % 2 != 0) equipos.add(null);
        int n = equipos.size();
        List<Equipo> rot = new ArrayList<>(equipos);

        // rondas ida
        List<List<String>> rounds = new ArrayList<>();
        for (int round = 0; round < n - 1; round++) {
            List<String> pairings = new ArrayList<>();
            for (int i = 0; i < n / 2; i++) {
                Equipo a = rot.get(i);
                Equipo b = rot.get(n - 1 - i);
                if (a != null && b != null) {
                    pairings.add(a.getNombre() + " vs " + b.getNombre());
                }
            }
            rounds.add(pairings);
            // rotación
            List<Equipo> next = new ArrayList<>();
            next.add(rot.get(0));
            next.add(rot.get(n - 1));
            for (int i = 1; i < n - 1; i++) next.add(rot.get(i));
            rot = next;
        }

        // construir ida + vuelta
        List<List<String>> allRounds = new ArrayList<>();
        allRounds.addAll(rounds);
        for (List<String> r : rounds) {
            List<String> swapped = new ArrayList<>();
            for (String s : r) {
                String[] parts = s.split(" vs ");
                if (parts.length == 2) swapped.add(parts[1] + " vs " + parts[0]);
            }
            allRounds.add(swapped);
        }

        // rellenar tabla con jornadas del equipo
        model.setRowCount(0);
        int jornada = 1;
        for (List<String> r : allRounds) {
            String rival = "";
            String localOrAway = "";
            for (String p : r) {
                if (p.contains(equipo.getNombre())) {
                    String[] parts = p.split(" vs ");
                    if (parts[0].equals(equipo.getNombre())) {
                        rival = parts[1];
                        localOrAway = "Local";
                    } else {
                        rival = parts[0];
                        localOrAway = "Visitante";
                    }
                    break;
                }
            }
            model.addRow(new Object[]{jornada, rival, localOrAway});
            jornada++;
        }

        JPanel south = new JPanel();
        JButton btnClose = new JButton("Cerrar");
        south.add(btnClose);
        add(south, BorderLayout.SOUTH);

        btnClose.addActionListener(e -> dispose());
    }
}
