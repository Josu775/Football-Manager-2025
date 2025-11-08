package domain;

import java.time.LocalDateTime;

public class Partido {
    private Equipo local;
    private Equipo visitante;
    private LocalDateTime fecha;
    private Integer golesLocal; // null = no jugado
    private Integer golesVisitante;

    public Partido(Equipo local, Equipo visitante, LocalDateTime fecha) {
        this.local = local;
        this.visitante = visitante;
        this.fecha = fecha;
    }

    public Equipo getLocal() { return local; }
    public Equipo getVisitante() { return visitante; }
    public LocalDateTime getFecha() { return fecha; }
    public Integer getGolesLocal() { return golesLocal; }
    public Integer getGolesVisitante() { return golesVisitante; }

    public void setResultado(int gLocal, int gVisitante) {
        this.golesLocal = gLocal;
        this.golesVisitante = gVisitante;
    }

    public boolean isJugado() { return golesLocal != null; }
}
