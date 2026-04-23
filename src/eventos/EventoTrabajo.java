package eventos;

import java.time.LocalDate;

/**
 * Evento de trabajo asociado a un cliente.
 */
public class EventoTrabajo extends Evento {
    private String cliente;
    
    /**
     * Construye un evento de trabajo.
     *
     * @param titulo nombre del evento
     * @param fecha fecha del evento
     * @param cliente cliente asociado
     * @param prioridad prioridad del evento
     */

    public EventoTrabajo(String titulo, LocalDate fecha, String cliente, Prioridad prioridad) {
        super(titulo, fecha, prioridad);
        this.cliente = cliente;
    }

    /**
     * Devuelve detalles del evento de trabajo.
     *
     * @return cliente asociado al evento
     */
    
    @Override
    public String getDetalles() {
        return "Trabajo para cliente: " + cliente;
    }
}