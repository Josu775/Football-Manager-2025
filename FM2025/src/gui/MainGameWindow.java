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

        JButton btnClasificacion = new JButton("Clasificación");
        JButton btnMercado = new JButton("Mercado");
        JButton btnPlantilla = new JButton("Plantilla / Tácticas");
        JButton btnCalendario = new JButton("Calendario");
        Dimension btnSize = new Dimension(200, 36);
        for (JButton b : new JButton[]{btnClasificacion, btnMercado, btnPlantilla, btnCalendario}) {
            b.setMaximumSize(btnSize);
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            leftMenu.add(b);
            leftMenu.add(Box.createVerticalStrut(8));
        }

        JPanel center = new JPanel(new BorderLayout(10,10));
        center.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JTextArea txtOverview = new JTextArea();
        txtOverview.setEditable(false);
        updateOverviewText(txtOverview);
        center.add(new JScrollPane(txtOverview), BorderLayout.CENTER);

        // abajo: botón ATRÁS
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAtras = new JButton("Atrás");
        bottom.add(btnAtras);

        add(leftMenu, BorderLayout.WEST);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        btnClasificacion.addActionListener(e -> new ClassificationWindow(this));
        btnMercado.addActionListener(e -> new MarketWindow(this, equipo));
        btnPlantilla.addActionListener(e -> new SquadTacticsWindow(this, equipo));
        btnCalendario.addActionListener(e -> new CalendarWindow(this, equipo));

        btnAtras.addActionListener(e -> {
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
        sb.append("Valoración global: ").append(String.format("%.1f / 5", equipo.getValoracion())).append("\n");
        sb.append("Presupuesto: €").append(String.format("%.0f", equipo.getBudget())).append("\n\n");
        sb.append("Usa el menú para ver clasificación, mercado, plantilla/tácticas y calendario.");
        area.setText(sb.toString());
    }
}
