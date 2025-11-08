package domain;

/**
 * Jugador con valoracion en escala 0.0 - 5.0 (double, 1 decimal).
 */
public class Jugador {
    private String nombre;
    private String posicion;
    private int edad;
    private double valoracion; // 0.0 .. 5.0 (una décima)

    public Jugador(String nombre, String posicion, int edad, double valoracion) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.edad = edad;
        setValoracion(valoracion); // usamos el setter para validar y redondear
    }

    public String getNombre() { return nombre; }
    public String getPosicion() { return posicion; }
    public int getEdad() { return edad; }

    // devuelve la valoración (ej. 4.5)
    public double getValoracion() { return valoracion; }

    // setValoracion con validación y redondeo a 1 decimal
    public void setValoracion(double nueva) {
        double v = Math.max(0.0, Math.min(5.0, nueva));
        this.valoracion = Math.round(v * 10.0) / 10.0;
    }

    @Override
    public String toString() {
        return nombre + " (" + posicion + ", " + edad + " años, " + String.format("%.1f", valoracion) + ")";
    }
}
