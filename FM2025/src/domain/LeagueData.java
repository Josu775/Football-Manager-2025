package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Datos estáticos de LaLiga (20 equipos) para la elección inicial.
 * Puedes mejorar/editar los once titulares más adelante.
 */
public class LeagueData {

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

        // Rellenar once titular sencillo para cada equipo (11 jugadores ficticios)
        for (Equipo e : lista) {
            List<Jugador> once = new ArrayList<>();
            // creación de 11 jugadores genéricos con posiciones típicas según formación simplificada
            once.add(new Jugador(e.getNombre() + " GK", "POR", 28, 3.5));
            once.add(new Jugador(e.getNombre() + " LD", "DEF", 27, 3.4));
            once.add(new Jugador(e.getNombre() + " CD1", "DEF", 29, 3.6));
            once.add(new Jugador(e.getNombre() + " CD2", "DEF", 26, 3.5));
            once.add(new Jugador(e.getNombre() + " LI", "DEF", 25, 3.3));
            once.add(new Jugador(e.getNombre() + " MC1", "MID", 27, 3.7));
            once.add(new Jugador(e.getNombre() + " MC2", "MID", 26, 3.6));
            once.add(new Jugador(e.getNombre() + " MC3", "MID", 24, 3.5));
            once.add(new Jugador(e.getNombre() + " ED", "ATT", 25, 3.8));
            once.add(new Jugador(e.getNombre() + " DC", "ATT", 28, 3.9));
            once.add(new Jugador(e.getNombre() + " EI", "ATT", 23, 3.6));
            e.setOnceTitular(once);
        }

        return lista;
    }

    private static Equipo createEquipo(String nombre, String ciudad, String estadio, String formacion, double valoracion) {
        return new Equipo(nombre, ciudad, estadio, formacion, valoracion);
    }
}
