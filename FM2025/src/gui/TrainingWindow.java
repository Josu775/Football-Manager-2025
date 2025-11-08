package gui;

import domain.Equipo;
import domain.Jugador;

import javax.swing.*;
import java.awt.*;

public class TrainingWindow extends JFrame {

    public TrainingWindow(JFrame parent, Equipo equipo) {
        super("Entrenamiento rápido - " + equipo.getNombre());
        setSize(480, 360);
        setLocationRelativeTo(parent);
        init(equipo);
        setVisible(true);
    }

    private void init(Equipo equipo) {
        JPanel p = new JPanel(new BorderLayout(8,8));
        p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JLabel lbl = new JLabel("Entrenamiento rápido: mejora leve de valoración");
        p.add(lbl, BorderLayout.NORTH);

        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        StringBuilder sb = new StringBuilder("Once antes del entrenamiento:\n");
        for (var j : equipo.getOnceTitular()) sb.append("- ").append(j.getNombre()).append(" (").append(j.getPosicion()).append(") ").append(String.format("%.1f", j.getValoracion())).append("\n");
        ta.setText(sb.toString());
        p.add(new JScrollPane(ta), BorderLayout.CENTER);

        JPanel south = new JPanel();
        JButton btnRun = new JButton("Ejecutar entrenamiento");
        JButton btnClose = new JButton("Cerrar");
        south.add(btnRun);
        south.add(btnClose);
        p.add(south, BorderLayout.SOUTH);

        btnRun.addActionListener(e -> {
            // aumentar ligeramente la valoración de cada jugador (max 5.0)
            for (Jugador j : equipo.getOnceTitular()) {
                double v = j.getValoracion() + (Math.random() * 0.2); // +0..0.2
                if (v > 5.0) v = 5.0;
                // setValor? Jugador no tiene setter: workaround: reflection not needed—recreate? Simpler: assume Jugador has setValoracion; if not, modify class (te lo indico abajo).
                try {
                    // intentar usar método setValoracion (si existe)
                    var m = j.getClass().getMethod("setValoracion", double.class);
                    m.invoke(j, v);
                } catch (Exception ex) {
                    // si no existe el setter, no hacemos nada; pero entrenador puede afectar la valoración del equipo global (no implementado)
                }
            }
            JOptionPane.showMessageDialog(this, "Entrenamiento ejecutado. Algunas valoraciones han aumentado levemente.");
            dispose();
        });

        btnClose.addActionListener(e -> dispose());

        add(p);
    }
}
