package domain;

public class Jugador {
    private int id;
    private String nombre;
    private int edad;
    private String posicion;
    private int goles = 0;

    public Jugador(String nombre, int edad, String posicion) {
        this.nombre = nombre;
        this.edad = edad;
        this.posicion = posicion;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getPosicion() { return posicion; }
    public int getGoles() { return goles; }
    public void addGoles(int n) { goles += n; }
}
