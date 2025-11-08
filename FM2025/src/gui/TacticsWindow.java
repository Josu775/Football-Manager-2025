package gui;

import domain.Equipo;

import javax.swing.*;
import java.awt.*;

public class TacticsWindow extends JFrame {

    public TacticsWindow(JFrame parent, Equipo equipo) {
        super("Tácticas - " + equipo.getNombre());
        setSize(420, 280);
        setLocationRelativeTo(parent);
        init(equipo);
        setVisible(true);
    }

    private void init(Equipo equipo) {
        JPanel p = new JPanel(new BorderLayout(8,8));
        p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        JLabel current = new JLabel("Formación actual: " + equipo.getFormacion());
        current.setFont(new Font("Arial", Font.BOLD, 14));
        p.add(current, BorderLayout.NORTH);

        String[] formations = {"4-4-2","4-3-3","4-2-3-1","3-5-2","5-3-2","4-4-1-1"};
        JComboBox<String> cb = new JComboBox<>(formations);
        cb.setSelectedItem(equipo.getFormacion());
        p.add(cb, BorderLayout.CENTER);

        JPanel south = new JPanel();
        JButton btnApply = new JButton("Aplicar");
        JButton btnClose = new JButton("Cerrar");
        south.add(btnApply);
        south.add(btnClose);
        p.add(south, BorderLayout.SOUTH);

        btnApply.addActionListener(e -> {
            String sel = (String) cb.getSelectedItem();
            equipo.setFormacion(sel);
            JOptionPane.showMessageDialog(this, "Nueva formación aplicada: " + sel);
            current.setText("Formación actual: " + equipo.getFormacion());
        });

        btnClose.addActionListener(e -> dispose());

        add(p);
    }
}
