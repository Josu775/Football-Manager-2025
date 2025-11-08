package domain;

import javax.swing.*;
import java.util.Random;

/**
 * Simula un partido en un hilo separado. Actualiza la UI vía SwingUtilities.invokeLater.
 */
public class MatchSimulator implements Runnable {
    private final Partido partido;
    private final JTable tablaUI; // referencia opcional para actualizar fila en GUI
    private final int rowIndex; // fila en la tabla

    // campos de instancia (pueden cambiar)
    private int golesLocal = 0;
    private int golesVisit = 0;

    public MatchSimulator(Partido partido, JTable tablaUI, int rowIndex) {
        this.partido = partido;
        this.tablaUI = tablaUI;
        this.rowIndex = rowIndex;
    }

    @Override
    public void run() {
        Random rnd = new Random();
        // simulación simple: 90' en pasos (cada 200ms = 5 minutos simulados aprox)
        for (int minute = 1; minute <= 90; minute += 5) {
            if (Thread.currentThread().isInterrupted()) {
                // si nos interrumpen, salimos limpiamente
                return;
            }

            if (rnd.nextDouble() < 0.08) golesLocal++;
            if (rnd.nextDouble() < 0.06) golesVisit++;

            final int displayL = golesLocal;
            final int displayV = golesVisit;
            final int min = minute;

            if (tablaUI != null) {
                SwingUtilities.invokeLater(() -> {
                    try {
                        // Columna 3 es "Resultado" (asegúrate de tener 4 columnas en el modelo)
                        tablaUI.setValueAt(displayL + " - " + displayV + " (m" + min + ")", rowIndex, 3);
                    } catch (Exception ex) {
                        // la fila pudo haber cambiado; ignoramos
                    }
                });
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        // Fijar resultado final en el objeto Partido
        partido.setResultado(golesLocal, golesVisit);

        if (tablaUI != null) {
            final int finalL = golesLocal;
            final int finalV = golesVisit;
            SwingUtilities.invokeLater(() -> {
                try {
                    tablaUI.setValueAt(finalL + " - " + finalV, rowIndex, 3);
                } catch (Exception ex) {
                    // ignorar si la fila ya no existe
                }
            });
        }
    }
}
