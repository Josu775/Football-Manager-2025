package gui;

import domain.GameSession;
import domain.Equipo;
import domain.Jugador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainGameWindow extends JFrame {

    private GameSession session;
    private Equipo equipo;

    public MainGameWindow(Window parent, GameSession session) {
        super("Partida - " + session.getJugadorEquipo().getNombre());
        this.session = session;
        this.equipo = session.getJugadorEquipo();
        setSize(900, 600);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel top = new JPanel(new BorderLayout(5,5));
        JLabel nombre = new JLabel(equipo.getNombre(), SwingConstants.CENTER);
        nombre.setFont(new Font("Arial", Font.BOLD, 26));
        top.add(nombre, BorderLayout.NORTH);

        JPanel info = new JPanel(new GridLayout(1,4,10,10));
        info.add(new JLabel("Ciudad: " + equipo.getCiudad()));
        info.add(new JLabel("Estadio: " + equipo.getEstadio()));
        info.add(new JLabel("Formación: " + equipo.getFormacion()));
        info.add(new JLabel("Valoración: " + String.format("%.1f / 5", equipo.getValoracion())));
        top.add(info, BorderLayout.CENTER);

        add(top, BorderLayout.NORTH);

        // Tabla once titular (sin valoración)
        String[] cols = {"#", "Nombre", "Posición", "Edad"};
        DefaultTableModel tm = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        JTable table = new JTable(tm);
        int idx = 1;
        for (Jugador j : equipo.getOnceTitular()) {
            tm.addRow(new Object[]{idx++, j.getNombre(), j.getPosicion(), j.getEdad()});
        }

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel south = new JPanel();
        JButton btnGuardar = new JButton("Guardar partida (no implementado aún)");
        JButton btnVolver = new JButton("Salir a menú");
        south.add(btnGuardar);
        south.add(btnVolver);
        add(south, BorderLayout.SOUTH);

        btnVolver.addActionListener(e -> {
            dispose();
            new WelcomeWindow().setVisible(true);
        });
    }
}
