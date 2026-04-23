package eventos;

import java.time.LocalDate;

/**
 * Evento personal que incluye una ubicación.
 */

public class EventoPersonal extends Evento {
    private String ubicacion;
    
    /**
     * Construye un evento personal.
     *
     * @param titulo nombre del evento
     * @param fecha fecha del evento
     * @param ubicacion lugar del evento
     * @param prioridad prioridad del evento
     */

    public EventoPersonal(String titulo, LocalDate fecha, String ubicacion, Prioridad prioridad) {
        super(titulo, fecha, prioridad);
        this.ubicacion = ubicacion;
    }
    
    /**
     * Devuelve detalles del evento personal.
     *
     * @return ubicación del evento
     */

    @Override
    public String getDetalles() {
        return "Ubicación: " + ubicacion;
    }
}