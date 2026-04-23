package logic;

import java.util.ArrayList;

/**
 * Clase encargada de gestionar la lista de eventos.
 * Implementa operaciones básicas como añadir, eliminar,
 * listar, completar y filtrar eventos.
 */

import eventos.Evento;
import eventos.Evento.Prioridad;

public class GestorEventos implements Gestionable {
	
	/**
	 * Lista interna de eventos almacenados.
	 */
	
	private ArrayList<Evento> eventos;
	
	/**
	 * Constructor del gestor de eventos.
	 * Inicializa la lista vacía.
	 */

	public GestorEventos() {
		eventos = new ArrayList<>();
	}
	
	/**
	 * Añade un evento a la lista.
	 *
	 * @param e evento a añadir
	 */

	@Override
	public void agregarEvento(Evento e) {
		eventos.add(e);
	}
	
	/**
	 * Elimina un evento según su índice.
	 *
	 * @param indice posición del evento en la lista
	 */

	@Override
	public void eliminarEvento(int indice) {
		if (indice >= 0 && indice < eventos.size()) {
			eventos.remove(indice);
		} else {
			System.out.println("Índice no válido.");
		}
	}
	
	/**
	 * Devuelve la lista completa de eventos.
	 *
	 * @return lista de eventos
	 */

	@Override
	public ArrayList<Evento> listarEventos() {
		return eventos;
	}

	/**
	 * Marca un evento como completado.
	 *
	 * @param indice posición del evento
	 */
	
	public void marcarEventoComoCompletado(int indice) {
		if (indice >= 0 && indice < eventos.size()) {
			eventos.get(indice).marcarComoCompletado();
		} else {
			System.out.println("Índice no válido.");
		}
	}

	/**
	 * Filtra eventos según su estado de completado.
	 *
	 * @param completados true para completados, false para pendientes
	 * @return lista filtrada
	 */
	
	public ArrayList<Evento> filtrarCompletados(boolean completados) {
		ArrayList<Evento> resultado = new ArrayList<>();
		for (Evento e : eventos) {
			if (e.isCompletado() == completados) {
				resultado.add(e);
			}
		}
		return resultado;
	}

	/**
	 * Filtra eventos según su prioridad.
	 *
	 * @param prioridad nivel de prioridad
	 * @return lista filtrada
	 */
	
	public ArrayList<Evento> filtrarPorPrioridad(Prioridad prioridad) {
		ArrayList<Evento> resultadoPrioridad = new ArrayList<>();

		for (Evento e : eventos) {
			if (e.getPrioridad() == prioridad) {
				resultadoPrioridad.add(e);
			}
		}
		
		return resultadoPrioridad; 
	}
}