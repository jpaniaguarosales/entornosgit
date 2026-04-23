package eventos;

import java.time.LocalDate;

/**
 * Evento de tipo formación con duración en horas.
 */

public class EventoFormacion extends Evento {
    private int duracionHoras;
    
    /**
     * Construye un evento de formación.
     *
     * @param titulo nombre del evento
     * @param fecha fecha del evento
     * @param duracionHoras duración en horas
     * @param prioridad prioridad del evento
     */

    public EventoFormacion(String titulo, LocalDate fecha, int duracionHoras, Prioridad prioridad) {
        super(titulo, fecha, prioridad);
        this.duracionHoras = duracionHoras;
    }
    
    /**
     * Devuelve los detalles específicos del evento de formación.
     *
     * @return duración en horas
     */

    @Override
    public String getDetalles() {
        return "Duración: " + duracionHoras + " horas";
    }
}