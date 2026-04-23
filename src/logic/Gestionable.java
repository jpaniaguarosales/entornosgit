package logic;

import java.util.ArrayList;
import eventos.Evento;

/**
 * Interfaz que define las operaciones básicas
 * de gestión de eventos.
 */



public interface Gestionable {
	
	/**
	 * Añade un evento.
	 *
	 * @param e evento a añadir
	 */
	
    public void agregarEvento(Evento e);
    
    /**
     * Elimina un evento por índice.
     *
     * @param indice posición del evento
     */
    
    public void eliminarEvento(int indice);
    
    /**
     * Lista todos los eventos.
     *
     * @return lista de eventos
     */
    
    public ArrayList<Evento> listarEventos();
}
