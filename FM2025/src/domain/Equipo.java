package domain;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private int id;
    private String nombre;
    private String ciudad;
    private List<Jugador> plantilla = new ArrayList<>();

    public Equipo(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public String getCiudad() { return ciudad; }
    public List<Jugador> getPlantilla() { return plantilla; }

    public void addJugador(Jugador j) { plantilla.add(j); }
    public void removeJugador(Jugador j) { plantilla.remove(j); }

    @Override
    public String toString() {
        return nombre + " (" + ciudad + ")";
    }
}
