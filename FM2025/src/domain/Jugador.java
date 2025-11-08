package domain;

public class Jugador {
    private String nombre;
    private String posicion;
    private int edad;

    public Jugador(String nombre, String posicion, int edad, double d) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.edad = edad;
    }

    public String getNombre() { return nombre; }
    public String getPosicion() { return posicion; }
    public int getEdad() { return edad; }

    @Override
    public String toString() {
        return nombre + " (" + posicion + ", " + edad + " años)";
    }
}
