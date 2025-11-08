package gui;

import javax.swing.*;
import java.awt.*;

public class WelcomeWindow extends JFrame {

    public WelcomeWindow() {
        setTitle("Football Manager - Bienvenida");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel p = new JPanel(new BorderLayout(10,10));
        p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel title = new JLabel("Bienvenido a Mini Football Manager", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        p.add(title, BorderLayout.NORTH);

        JTextArea info = new JTextArea("Crea una nueva partida para elegir un equipo de LaLiga.\n\n" +
                "Versión prototipo: elegir equipo y ver su ficha básica.");
        info.setEditable(false);
        info.setBackground(getBackground());
        p.add(info, BorderLayout.CENTER);

        JPanel botones = new JPanel();
        JButton btnNueva = new JButton("Nueva partida");
        JButton btnSalir = new JButton("Salir");
        botones.add(btnNueva);
        botones.add(btnSalir);
        p.add(botones, BorderLayout.SOUTH);

        add(p);

        btnNueva.addActionListener(e -> {
            // Abrir selección de equipo
            new TeamSelectionWindow(this);
            // opcional: ocultar esta ventana mientras se elige
            setVisible(false);
        });

        btnSalir.addActionListener(e -> System.exit(0));
    }
}
