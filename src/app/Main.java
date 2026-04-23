package app;

import logic.GestorEventos;
import eventos.Evento;
import eventos.EventoTrabajo;
import eventos.EventoPersonal;
import eventos.EventoFormacion;

/**
 * Clase principal de ejecución de la aplicación JavaFX.
 */

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	/**
	 * Método principal de JavaFX.
	 *
	 * @param args argumentos de ejecución
	 */
	
	public static void main(String[] args) {
		 
		launch(args); 
	}
	
	/**
	 * Inicia la aplicación y carga la ventana principal.
	 *
	 * @param stage ventana principal de JavaFX
	 */
	
	@Override
    public void start(Stage stage) {

		  VentanaPrincipal ventana = new VentanaPrincipal();
	        ventana.mostrar(stage);
    }
	
	

}
