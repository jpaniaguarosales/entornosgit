package eventos;

import java.time.LocalDate;

/**
 * Clase abstracta base que representa un evento genérico.
 * Contiene atributos comunes como título, fecha, prioridad y estado.
 */


public abstract class Evento {
	protected String titulo;
	protected LocalDate fecha;
	protected boolean completado;
	
	/**
	 * Enumeración que define los niveles de prioridad de un evento.
	 */
	
    public enum Prioridad {
        BAJA,
        MEDIA,
        ALTA
    }
    
    private Prioridad prioridad;
    
    /**
     * Construye un evento con título, fecha y prioridad.
     *
     * @param titulo nombre del evento
     * @param fecha fecha del evento
     * @param prioridad nivel de prioridad asignado
     */
    

	public Evento(String titulo, LocalDate fecha, Prioridad prioridad) {
		this.titulo = titulo;
		this.fecha = fecha;
		this.completado = false;
		this.prioridad = prioridad; 
	}
	
	/**
	 * Marca el evento como completado.
	 */

	public void marcarComoCompletado() {
		this.completado = true;
	}
	
	/**
	 * Indica si el evento está completado.
	 *
	 * @return true si está completado, false en caso contrario
	 */


	public boolean isCompletado() {
		return completado;
	}
	
	/**
	 * Devuelve el título del evento.
	 *
	 * @return título
	 */

	public String getTitulo() {
		return titulo;
	}
	
	/**
	 * Devuelve la fecha del evento.
	 *
	 * @return fecha del evento
	 */

	public LocalDate getFecha() {
		return fecha;
	}
	
	/**
	 * Obtiene la prioridad del evento.
	 *
	 * @return prioridad
	 */
	
	public Prioridad getPrioridad() {
	    return prioridad;
	}
	
	/**
	 * Establece la prioridad del evento.
	 *
	 * @param prioridad nueva prioridad
	 */

	public void setPrioridad(Prioridad prioridad) {
	    this.prioridad = prioridad;
	}
	
	/**
	 * Devuelve detalles específicos del evento según su tipo.
	 * Este es un metodo abstracto que obliga a las clases hijas a sobreescribirlos
	 * @return descripción del evento
	 */

	public abstract String getDetalles();
	
	/**
	 * Representación en texto del evento.
	 *
	 * @return cadena con título, fecha y estado
	 */

	@Override
	public String toString() {
		return titulo + " (" + fecha + ") - " + (completado ? "✔" : "Pendiente");
	}

}