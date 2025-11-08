package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LeagueManager {
    private List<Equipo> equipos = new ArrayList<>();
    private List<Partido> partidos = new ArrayList<>();

    public void addEquipo(Equipo e) {
        equipos.add(e);
    }

    public List<Equipo> getEquipos() { return equipos; }
    public List<Partido> getPartidos() { return partidos; }

    /**
     * Genera un calendario round-robin usando recursividad simple.
     * Para N equipos: si N es impar añade bye (null) o usa rotación.
     */
    public void generarFixtureRecursivo() {
        partidos.clear();
        List<Equipo> lista = new ArrayList<>(equipos);
        if (lista.size() < 2) return;
        // si impar, añadimos un "bye" (null) - no generaremos partidos con null
        boolean odd = (lista.size() % 2 != 0);
        if (odd) lista.add(null);

        genRoundRobin(lista, 0);
    }

    // Gen recursiva: rotación por rondas. depth = jornada actual
    private void genRoundRobin(List<Equipo> current, int depth) {
        int n = current.size();
        if (depth == n - 1) return; // base: ya generadas todas las rondas
        // generar emparejamientos para la ronda 'depth'
        for (int i = 0; i < n / 2; i++) {
            Equipo a = current.get(i);
            Equipo b = current.get(n - 1 - i);
            if (a != null && b != null) {
                // fijamos la fecha simple: ahora + depth días
                Partido p = new Partido(a, b, LocalDateTime.now().plusDays(depth));
                partidos.add(p);
            }
        }
        // rotación: mantén el primero, rota el resto
        List<Equipo> rotated = new ArrayList<>();
        rotated.add(current.get(0));
        rotated.add(current.get(n - 1));
        for (int i = 1; i < n - 1; i++) rotated.add(current.get(i));
        // llamada recursiva
        genRoundRobin(rotated, depth + 1);
    }
}
