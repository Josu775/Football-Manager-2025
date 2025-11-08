package domain;

public class Jugador {
    private String nombre;
    private String posicion;
    private int edad;
    private double valoracion; // 0..5

    public Jugador(String nombre, String posicion, int edad, double valoracion) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.edad = edad;
        this.valoracion = valoracion;
    }

    public String getNombre() { return nombre; }
    public String getPosicion() { return posicion; }
    public int getEdad() { return edad; }
    public double getValoracion() { return valoracion; }

    public void setValoracion(double nueva) {
        // nos aseguramos de que esté entre 1 y 5
        this.valoracion = Math.round(Math.max(1.0, Math.min(5.0, nueva)) * 10.0) / 10.0;
    }

    @Override
    public String toString() {
        return nombre + " (" + posicion + ", " + edad + " años, " + String.format("%.1f", valoracion) + ")";
    }
}
