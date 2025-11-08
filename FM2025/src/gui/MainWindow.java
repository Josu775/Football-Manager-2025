package gui;

import domain.Equipo;
import domain.LeagueManager;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private final LeagueManager manager;

    public MainWindow() {
        // Creamos la instancia única de LeagueManager aquí y la pasamos a las ventanas
        this.manager = new LeagueManager();

        // Puedes añadir algunos equipos de ejemplo para probar
        manager.addEquipo(new Equipo("Real Ejemplo", "Ciudad A"));
        manager.addEquipo(new Equipo("Club Demo", "Ciudad B"));
        manager.addEquipo(new Equipo("Equipo Test", "Ciudad C"));
        
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
        JPanel nav = new JPanel();
        nav.setLayout(new BoxLayout(nav, BoxLayout.Y_AXIS));
        JButton btnEquipos = new JButton("Gestionar Equipos");
        JButton btnJugadores = new JButton("Gestionar Jugadores");
        JButton btnPartidos = new JButton("Partidos");
        JButton btnSalir = new JButton("Salir");
        btnEquipos.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnJugadores.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPartidos.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        nav.add(Box.createVerticalGlue());
        nav.add(btnEquipos);
        nav.add(Box.createVerticalStrut(10));
        nav.add(btnJugadores);
        nav.add(Box.createVerticalStrut(10));
        nav.add(btnPartidos);
        nav.add(Box.createVerticalStrut(10));
        nav.add(btnSalir);
        nav.add(Box.createVerticalGlue());

        JPanel info = new JPanel(new BorderLayout());
        JTextArea ta = new JTextArea("Bienvenido al prototipo 1.\n\nUsa los botones para abrir las ventanas.");
        ta.setEditable(false);
        info.add(new JScrollPane(ta), BorderLayout.CENTER);

        center.add(nav);
        center.add(info);

        mainPanel.add(center, BorderLayout.CENTER);
        add(mainPanel);

        // listeners: abrimos ventanas pasando la misma instancia manager
        btnEquipos.addActionListener(e -> new VentanaEquipos(this, manager));
        btnJugadores.addActionListener(e -> new VentanaJugadores(this, manager));
        btnPartidos.addActionListener(e -> new PartidosWindow(this, manager));
        btnSalir.addActionListener(e -> System.exit(0));
    }
}
