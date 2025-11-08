package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * LeagueData con 20 equipos y presupuesto asignado.
 */
public class LeagueData {

    private static final Random RNG = new Random();

    public static List<Equipo> getLaLiga20() {
        List<Equipo> lista = new ArrayList<>();

        // Los budgets son indicativos (millones ficticios)
        lista.add(createEquipo("Real Madrid", "Madrid", "Santiago Bernabéu", "4-3-3", 5.0, 800_000_000));
        lista.add(createEquipo("FC Barcelona", "Barcelona", "Camp Nou", "4-3-3", 4.9, 750_000_000));
        lista.add(createEquipo("Atlético de Madrid", "Madrid", "Cívitas Metropolitano", "4-4-2", 4.6, 420_000_000));
        lista.add(createEquipo("Sevilla FC", "Sevilla", "Ramón Sánchez Pizjuán", "4-2-3-1", 4.1, 160_000_000));
        lista.add(createEquipo("Real Sociedad", "Donostia/San Sebastián", "Reale Arena", "4-3-3", 4.0, 110_000_000));
        lista.add(createEquipo("Villarreal CF", "Villarreal", "Estadio de la Cerámica", "4-4-2", 3.9, 120_000_000));
        lista.add(createEquipo("Real Betis", "Sevilla", "Benito Villamarín", "4-3-3", 3.9, 140_000_000));
        lista.add(createEquipo("Athletic Club", "Bilbao", "San Mamés", "4-2-3-1", 3.8, 130_000_000));
        lista.add(createEquipo("Valencia CF", "Valencia", "Mestalla", "4-4-2", 3.7, 100_000_000));
        lista.add(createEquipo("RCD Mallorca", "Mallorca", "Iberostar", "4-4-2", 3.4, 30_000_000));
        lista.add(createEquipo("CA Osasuna", "Pamplona", "El Sadar", "4-2-3-1", 3.3, 25_000_000));
        lista.add(createEquipo("RC Celta", "Vigo", "Balaídos", "4-3-3", 3.3, 35_000_000));
        lista.add(createEquipo("Getafe CF", "Getafe", "Coliseum Alfonso Pérez", "4-4-2", 3.2, 30_000_000));
        lista.add(createEquipo("Granada CF", "Granada", "Nuevo Los Cármenes", "4-2-3-1", 3.1, 20_000_000));
        lista.add(createEquipo("Rayo Vallecano", "Madrid", "Vallecas", "4-2-3-1", 3.0, 25_000_000));
        lista.add(createEquipo("Deportivo Alavés", "Vitoria-Gasteiz", "Mendizorrotza", "4-4-2", 2.9, 18_000_000));
        lista.add(createEquipo("Cádiz CF", "Cádiz", "Nuevo Mirandilla", "4-4-2", 2.8, 15_000_000));
        lista.add(createEquipo("Elche CF", "Elche", "Martínez Valero", "4-4-2", 2.7, 12_000_000));
        lista.add(createEquipo("RCD Espanyol", "Barcelona", "RCDE Stadium", "4-3-3", 3.2, 40_000_000));
        lista.add(createEquipo("Girona FC", "Girona", "Montilivi", "4-3-3", 3.4, 50_000_000));

        // generar once titular con nombres aleatorios y valoraciones relativas
        for (Equipo e : lista) {
            List<Jugador> once = new ArrayList<>();
            once.add(new Jugador(randomNombre(), "POR", randomEdad(24,34), randomValor(e.getValoracion())));
            for (int i=0;i<4;i++) once.add(new Jugador(randomNombre(), "DEF", randomEdad(20,33), randomValor(e.getValoracion())));
            for (int i=0;i<3;i++) once.add(new Jugador(randomNombre(), "MID", randomEdad(19,32), randomValor(e.getValoracion())));
            for (int i=0;i<3;i++) once.add(new Jugador(randomNombre(), "ATT", randomEdad(19,33), randomValor(e.getValoracion())));
            e.setOnceTitular(once);
        }
        return lista;
    }

    private static Equipo createEquipo(String n, String c, String s, String f, double val, double budget) {
        return new Equipo(n,c,s,f,val,budget);
    }

    private static int randomEdad(int a, int b) { return a + RNG.nextInt(b - a + 1); }

    private static double randomValor(double teamVal) {
        double v = teamVal + (RNG.nextDouble() * 1.0 - 0.5);
        v = Math.max(1.5, Math.min(5.0, v));
        return Math.round(v * 10.0) / 10.0;
    }

    // simple name generator
    private static String randomNombre() {
        String[] fn = {"Álvaro","Alejandro","Sergio","David","Juan","Pablo","Luis","Miguel","Javier","Carlos",
                "Andrés","Fernando","Rubén","Óscar","Diego","Iván","Hugo","Martín","Marco","Iker"};
        String[] ln = {"García","González","Rodríguez","Fernández","López","Martínez","Sánchez","Pérez","Gómez","Ruiz"};
        return fn[RNG.nextInt(fn.length)] + " " + ln[RNG.nextInt(ln.length)];
    }
}
