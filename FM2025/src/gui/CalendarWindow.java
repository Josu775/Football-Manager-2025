package gui;

import javax.swing.*;
import java.awt.*;

public class CalendarWindow extends JFrame {
    public CalendarWindow(JFrame parent) {
        super("Calendario (prototipo)");
        setSize(600, 420);
        setLocationRelativeTo(parent);
        init();
        setVisible(true);
    }

    private void init() {
        JTextArea ta = new JTextArea("Calendario y partidos se implementarán en la siguiente fase.\n\n" +
                "Ahora esta ventana es un placeholder con la idea donde exhibiremos jornadas, resultados y opciones para simular.");
        ta.setEditable(false);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        add(new JScrollPane(ta), BorderLayout.CENTER);
    }
}
