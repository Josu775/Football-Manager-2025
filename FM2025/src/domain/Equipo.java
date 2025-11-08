package domain;

public class Equipo {
    private String nombre;
    private String ciudad;

    public Equipo(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
    }

    public String getNombre() { return nombre; }
    public String getCiudad() { return ciudad; }

    @Override
    public String toString() {
        return nombre + " (" + ciudad + ")";
    }
}
