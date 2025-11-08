package gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("Football Manager - Prototipo 1");
        setSize(900, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel titulo = new JLabel("Football Manager", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        mainPanel.add(titulo, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(1,2,10,10));
        // Panel izquierdo: navegación
        JPanel nav = new JPanel();
        nav.setLayout(new BoxLayout(nav, BoxLayout.Y_AXIS));
        JButton btnEquipos = new JButton("Gestionar Equipos");
        JButton btnJugadores = new JButton("Gestionar Jugadores");
        JButton btnSalir = new JButton("Salir");
        btnEquipos.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnJugadores.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        nav.add(Box.createVerticalGlue());
        nav.add(btnEquipos);
        nav.add(Box.createVerticalStrut(10));
        nav.add(btnJugadores);
        nav.add(Box.createVerticalStrut(10));
        nav.add(btnSalir);
        nav.add(Box.createVerticalGlue());

        // Panel derecho: area de información (placeholder)
        JPanel info = new JPanel(new BorderLayout());
        JTextArea ta = new JTextArea("Bienvenido al prototipo 1.\n\nUsa los botones para abrir las ventanas.");
        ta.setEditable(false);
        info.add(new JScrollPane(ta), BorderLayout.CENTER);

        center.add(nav);
        center.add(info);

        mainPanel.add(center, BorderLayout.CENTER);
        add(mainPanel);

        // Listeners
        btnEquipos.addActionListener(e -> new VentanaEquipos(this));
        btnJugadores.addActionListener(e -> new VentanaJugadores(this));
        btnSalir.addActionListener(e -> System.exit(0));
    }
}
