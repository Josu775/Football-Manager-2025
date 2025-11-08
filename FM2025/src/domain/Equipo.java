package domain;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private String ciudad;
    private String estadio;
    private String formacion; // e.g. "4-3-3"
    private double valoracion; // 0..5
    private List<Jugador> onceTitular = new ArrayList<>();

    public Equipo(String nombre, String ciudad, String estadio, String formacion, double valoracion) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.estadio = estadio;
        this.formacion = formacion;
        this.valoracion = valoracion;
    }

    public String getNombre() { return nombre; }
    public String getCiudad() { return ciudad; }
    public String getEstadio() { return estadio; }
    public String getFormacion() { return formacion; }
    public double getValoracion() { return valoracion; }
    public List<Jugador> getOnceTitular() { return onceTitular; }

    public void setOnceTitular(List<Jugador> once) {
        this.onceTitular.clear();
        if (once != null) this.onceTitular.addAll(once);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
