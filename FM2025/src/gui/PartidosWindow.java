package gui;

import domain.LeagueManager;
import domain.Partido;
import domain.MatchSimulator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PartidosWindow extends JFrame {
    private LeagueManager manager;
    private JTable table;
    private DefaultTableModel model;
    private JButton btnSimular;
    // opcional: control de thread activo
    private Thread activeSimThread = null;

    public PartidosWindow(JFrame parent, LeagueManager manager) {
        super("Partidos");
        this.manager = manager;
        setSize(900, 420);
        setLocationRelativeTo(parent);
        // inicializar modelo y tabla aquí
        model = new DefaultTableModel(new String[]{"Jornada", "Local", "Visitante", "Resultado"}, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(model);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel south = new JPanel();
        JButton btnGen = new JButton("Generar fixture");
        btnSimular = new JButton("Simular seleccionado");
        JButton btnReload = new JButton("Refrescar tabla");
        south.add(btnGen);
        south.add(btnSimular);
        south.add(btnReload);
        add(south, BorderLayout.SOUTH);

        btnGen.addActionListener(e -> {
            manager.generarFixtureRecursivo();
            reloadTable();
        });

        btnReload.addActionListener(e -> reloadTable());

        btnSimular.addActionListener(e -> {
            int sel = table.getSelectedRow();
            if (sel < 0) {
                JOptionPane.showMessageDialog(this, "Selecciona un partido");
                return;
            }
            // por seguridad, convertimos índice visual a índice de modelo
            int modelRow = table.convertRowIndexToModel(sel);

            List<Partido> partidos = manager.getPartidos();
            if (modelRow < 0 || modelRow >= partidos.size()) {
                JOptionPane.showMessageDialog(this, "Partido no válido.");
                return;
            }
            Partido p = partidos.get(modelRow);
            if (p.isJugado()) {
                JOptionPane.showMessageDialog(this, "Partido ya jugado");
                return;
            }

            // Desactivar botones mientras simulamos
            btnSimular.setEnabled(false);
            btnGen.setEnabled(false);
            btnReload.setEnabled(false);

            // Crear simulador y ejecutar en un Thread separado
            MatchSimulator sim = new MatchSimulator(p, table, modelRow);
            activeSimThread = new Thread(() -> {
                try {
                    sim.run();
                } finally {
                    SwingUtilities.invokeLater(() -> {
                        btnSimular.setEnabled(true);
                        btnGen.setEnabled(true);
                        btnReload.setEnabled(true);
                        // refrescar tabla completa por si hay inconsistencias
                        reloadTable();
                    });
                }
            });
            activeSimThread.start();
        });

        // cargamos tabla si ya hay partidos
        reloadTable();
    }

    private void reloadTable() {
        // reconstruye la tabla desde el manager
        model.setRowCount(0);
        int jornada = 1;
        for (Partido p : manager.getPartidos()) {
            String resultado = p.isJugado() ? (p.getGolesLocal() + " - " + p.getGolesVisitante()) : "Pendiente";
            model.addRow(new Object[]{jornada++, p.getLocal().getNombre(), p.getVisitante().getNombre(), resultado});
        }
    }
}
