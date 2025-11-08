package gui;

import domain.GameSession;
import domain.Equipo;

import javax.swing.*;
import java.awt.*;

public class MainGameWindow extends JFrame {

    private GameSession session;
    private Equipo equipo;
    private JLabel lblTeamName;
    private JLabel lblFormation;
    private JLabel lblRating;

    public MainGameWindow(Window parent, GameSession session) {
        super("Partida - " + session.getJugadorEquipo().getNombre());
        this.session = session;
        this.equipo = session.getJugadorEquipo();
        setSize(1100, 700);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        // Layout principal: izquierda menú, centro contenido
        JPanel leftMenu = new JPanel();
        leftMenu.setLayout(new BoxLayout(leftMenu, BoxLayout.Y_AXIS));
        leftMenu.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        leftMenu.setPreferredSize(new Dimension(220, 0));

        lblTeamName = new JLabel(equipo.getNombre(), SwingConstants.CENTER);
        lblTeamName.setFont(new Font("Arial", Font.BOLD, 16));
        lblTeamName.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftMenu.add(lblTeamName);
        leftMenu.add(Box.createVerticalStrut(10));

        lblFormation = new JLabel("Formación: " + equipo.getFormacion());
        lblFormation.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftMenu.add(lblFormation);
        leftMenu.add(Box.createVerticalStrut(5));

        lblRating = new JLabel("Valoración: " + String.format("%.1f / 5", equipo.getValoracion()));
        lblRating.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftMenu.add(lblRating);
        leftMenu.add(Box.createVerticalStrut(15));

        // Botones principales (estilo menú FM)
        JButton btnClasificacion = new JButton("Clasificación");
        JButton btnMercado = new JButton("Mercado");
        JButton btnPlantilla = new JButton("Plantilla");
        JButton btnTacticas = new JButton("Tácticas");
        JButton btnCalendario = new JButton("Calendario");
        JButton btnEntrenamiento = new JButton("Entrenamiento");
        JButton btnGuardar = new JButton("Guardar partida");
        JButton btnSalir = new JButton("Menu principal");

        Dimension btnSize = new Dimension(200, 36);
        for (JButton b : new JButton[]{btnClasificacion, btnMercado, btnPlantilla, btnTacticas, btnCalendario, btnEntrenamiento, btnGuardar, btnSalir}) {
            b.setMaximumSize(btnSize);
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            leftMenu.add(b);
            leftMenu.add(Box.createVerticalStrut(8));
        }

        // Panel central donde mostramos la ficha/acciones rápidas
        JPanel center = new JPanel(new BorderLayout(10,10));
        center.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel title = new JLabel("Panel principal", SwingConstants.LEFT);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        center.add(title, BorderLayout.NORTH);

        JTextArea txtOverview = new JTextArea();
        txtOverview.setEditable(false);
        txtOverview.setLineWrap(true);
        txtOverview.setWrapStyleWord(true);
        updateOverviewText(txtOverview);
        center.add(new JScrollPane(txtOverview), BorderLayout.CENTER);

        // Panel inferior con accesos rápidos
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton quickSim = new JButton("Simular partido (rápido)");
        JButton quickTransfers = new JButton("Abrir Mercado");
        bottom.add(quickSim);
        bottom.add(quickTransfers);
        center.add(bottom, BorderLayout.SOUTH);

        add(leftMenu, BorderLayout.WEST);
        add(center, BorderLayout.CENTER);

        // Listeners: abrir ventanas
        btnClasificacion.addActionListener(e -> new ClassificationWindow(this));
        btnMercado.addActionListener(e -> new MarketWindow(this, equipo));
        btnPlantilla.addActionListener(e -> new SquadWindow(this, equipo));
        btnTacticas.addActionListener(e -> {
            TacticsWindow tw = new TacticsWindow(this, equipo);
            tw.setVisible(true);
            // al cerrar actualizamos etiquetas
            lblFormation.setText("Formación: " + equipo.getFormacion());
        });
        btnCalendario.addActionListener(e -> new CalendarWindow(this));
        btnEntrenamiento.addActionListener(e -> {
            TrainingWindow tw = new TrainingWindow(this, equipo);
            tw.setVisible(true);
            // refrescar overview y rating label
            updateOverviewText(txtOverview);
            lblRating.setText("Valoración: " + String.format("%.1f / 5", equipo.getValoracion()));
        });

        quickSim.addActionListener(e -> JOptionPane.showMessageDialog(this, "Simulación rápida no implementada en este prototipo."));
        quickTransfers.addActionListener(e -> new MarketWindow(this, equipo));

        btnGuardar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Guardar no implementado (próximo paso)."));
        btnSalir.addActionListener(e -> {
            dispose();
            new WelcomeWindow().setVisible(true);
        });
    }

    private void updateOverviewText(JTextArea area) {
        StringBuilder sb = new StringBuilder();
        sb.append("Equipo: ").append(equipo.getNombre()).append("\n");
        sb.append("Ciudad: ").append(equipo.getCiudad()).append("\n");
        sb.append("Estadio: ").append(equipo.getEstadio()).append("\n");
        sb.append("Formación: ").append(equipo.getFormacion()).append("\n");
        sb.append("Valoración global: ").append(String.format("%.1f / 5", equipo.getValoracion())).append("\n\n");
        sb.append("Acciones rápidas:\n");
        sb.append("- Ir a Plantilla: editar once, ver detalles.\n");
        sb.append("- Mercado: fichar o vender jugadores.\n");
        sb.append("- Tácticas: cambiar formación y mentalidad.\n");
        sb.append("- Calendario: ver próximos enfrentamientos y simular partidos.\n");
        area.setText(sb.toString());
    }
}
