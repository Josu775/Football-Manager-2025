package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Datos estáticos de LaLiga (20 equipos) con generación aleatoria
 * de nombres de jugadores para que el once titular muestre nombres reales.
 */
public class LeagueData {

    private static final Random RNG = new Random();

    private static final String[] FIRST_NAMES = {
            "Álvaro","Alejandro","Sergio","David","Juan","Pablo","Luis","Miguel","Javier","Carlos",
            "Andrés","Fernando","Rubén","Óscar","Diego","Iván","Hugo","Martín","Marco","Iker",
            "Adrián","Samuel","Víctor","Rodrigo","Manuel","Ángel","Borja","Mauro","Enzo","Nicolás"
    };

    private static final String[] LAST_NAMES = {
            "García","González","Rodríguez","Fernández","López","Martínez","Sánchez","Pérez",
            "Gómez","Ruiz","Hernández","Díaz","Jiménez","Álvarez","Moreno","Muñoz","Romero","Alonso",
            "Torres","Ramírez","Vargas","Rivas","Rojas","Ortega","Soto","Castro","Navarro","Molina",
            "Iglesias","Suárez"
    };

    // Posiciones abreviadas usadas en la ficha
    private static final String[] POSITIONS = {"POR","DEF","MID","ATT"};

    public static List<Equipo> getLaLiga20() {
        List<Equipo> lista = new ArrayList<>();

        lista.add(createEquipo("Real Madrid", "Madrid", "Santiago Bernabéu", "4-3-3", 5.0));
        lista.add(createEquipo("FC Barcelona", "Barcelona", "Camp Nou", "4-3-3", 4.9));
        lista.add(createEquipo("Atlético de Madrid", "Madrid", "Cívitas Metropolitano", "4-4-2", 4.6));
        lista.add(createEquipo("Sevilla FC", "Sevilla", "Ramón Sánchez Pizjuán", "4-2-3-1", 4.1));
        lista.add(createEquipo("Real Sociedad", "Donostia/San Sebastián", "Reale Arena", "4-3-3", 4.0));
        lista.add(createEquipo("Villarreal CF", "Villarreal", "Estadio de la Cerámica", "4-4-2", 3.9));
        lista.add(createEquipo("Real Betis", "Sevilla", "Benito Villamarín", "4-3-3", 3.9));
        lista.add(createEquipo("Athletic Club", "Bilbao", "San Mamés", "4-2-3-1", 3.8));
        lista.add(createEquipo("Valencia CF", "Valencia", "Mestalla", "4-4-2", 3.7));
        lista.add(createEquipo("RCD Mallorca", "Mallorca", "Iberostar", "4-4-2", 3.4));
        lista.add(createEquipo("CA Osasuna", "Pamplona", "El Sadar", "4-2-3-1", 3.3));
        lista.add(createEquipo("RC Celta", "Vigo", "Balaídos", "4-3-3", 3.3));
        lista.add(createEquipo("Getafe CF", "Getafe", "Coliseum Alfonso Pérez", "4-4-2", 3.2));
        lista.add(createEquipo("Granada CF", "Granada", "Nuevo Los Cármenes", "4-2-3-1", 3.1));
        lista.add(createEquipo("Rayo Vallecano", "Madrid", "Vallecas", "4-2-3-1", 3.0));
        lista.add(createEquipo("Deportivo Alavés", "Vitoria-Gasteiz", "Mendizorrotza", "4-4-2", 2.9));
        lista.add(createEquipo("Cádiz CF", "Cádiz", "Nuevo Mirandilla", "4-4-2", 2.8));
        lista.add(createEquipo("Elche CF", "Elche", "Martínez Valero", "4-4-2", 2.7));
        lista.add(createEquipo("RCD Espanyol", "Barcelona", "RCDE Stadium", "4-3-3", 3.2));
        lista.add(createEquipo("Girona FC", "Girona", "Montilivi", "4-3-3", 3.4));

        // Rellenar once titular con nombres aleatorios y posiciones plausibles
        for (Equipo e : lista) {
            List<Jugador> once = new ArrayList<>();
            // 1 portero
            once.add(new Jugador(randomNombre(), "POR", randomEdad(24, 34), randomValoracion(e.getValoracion())));
            // 4 defensas (LD, CD1, CD2, LI)
            once.add(new Jugador(randomNombre(), "DEF", randomEdad(20, 32), randomValoracion(e.getValoracion())));
            once.add(new Jugador(randomNombre(), "DEF", randomEdad(20, 34), randomValoracion(e.getValoracion())));
            once.add(new Jugador(randomNombre(), "DEF", randomEdad(20, 32), randomValoracion(e.getValoracion())));
            once.add(new Jugador(randomNombre(), "DEF", randomEdad(20, 31), randomValoracion(e.getValoracion())));
            // 3 medios
            once.add(new Jugador(randomNombre(), "MID", randomEdad(20, 32), randomValoracion(e.getValoracion())));
            once.add(new Jugador(randomNombre(), "MID", randomEdad(20, 32), randomValoracion(e.getValoracion())));
            once.add(new Jugador(randomNombre(), "MID", randomEdad(19, 31), randomValoracion(e.getValoracion())));
            // 3 atacantes
            once.add(new Jugador(randomNombre(), "ATT", randomEdad(20, 33), randomValoracion(e.getValoracion())));
            once.add(new Jugador(randomNombre(), "ATT", randomEdad(20, 33), randomValoracion(e.getValoracion())));
            once.add(new Jugador(randomNombre(), "ATT", randomEdad(19, 31), randomValoracion(e.getValoracion())));

            e.setOnceTitular(once);
        }

        return lista;
    }

    private static Equipo createEquipo(String nombre, String ciudad, String estadio, String formacion, double valoracion) {
        return new Equipo(nombre, ciudad, estadio, formacion, valoracion);
    }

    // genera un nombre compuesto (Nombre + PrimerApellido + SegundoApellido)
    private static String randomNombre() {
        String fn = FIRST_NAMES[RNG.nextInt(FIRST_NAMES.length)];
        String ln1 = LAST_NAMES[RNG.nextInt(LAST_NAMES.length)];
        String ln2 = LAST_NAMES[RNG.nextInt(LAST_NAMES.length)];
        // evitar repetir apellidos iguales
        if (ln1.equals(ln2)) {
            int tries = 0;
            while (ln1.equals(ln2) && tries++ < 5) ln2 = LAST_NAMES[RNG.nextInt(LAST_NAMES.length)];
        }
        return fn + " " + ln1 + " " + ln2;
    }

    // edad aleatoria entre min..max (inclusive)
    private static int randomEdad(int min, int max) {
        return min + RNG.nextInt(max - min + 1);
    }

    // genera una valoración del jugador basada en la valoración del equipo (ligera variación)
    private static double randomValoracion(double teamRating) {
        // teamRating es 0..5; jugador tendrá algo cercano con +/- 0.6 variación
        double variation = (RNG.nextDouble() * 1.2) - 0.6; // [-0.6, +0.6]
        double val = Math.max(1.5, Math.min(5.0, teamRating + variation));
        // redondear a 1 decimal
        return Math.round(val * 10.0) / 10.0;
    }
}
