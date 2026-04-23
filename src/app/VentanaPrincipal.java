package app;

import java.time.LocalDate;

import eventos.Evento;
import eventos.Evento.Prioridad;
import eventos.EventoFormacion;
import eventos.EventoPersonal;
import eventos.EventoTrabajo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import logic.GestorEventos;

/**
 * Clase principal de la interfaz gráfica de la aplicación.
 * Representa el menú principal de la agenda donde el usuario
 * puede acceder a las distintas funcionalidades:
 * añadir, listar, eliminar y completar eventos.
 * 
 * La interfaz está construida con JavaFX.
 * @author Jone Paniagua Rosales
 * @version 2. 
 */

public class VentanaPrincipal {

	// ESTILO VISUAL UNIFICADO
	private static final String ESTILO_FONDO = "-fx-padding: 30;"
			+ "-fx-background-color: linear-gradient(to bottom, #E3F2FD, #BBDEFB);" + "-fx-font-family: Arial;";

	// Gestor de lógica de negocio
	private GestorEventos gestor = new GestorEventos();

	/**
	 * Muestra la ventana principal de la aplicación con el menú de opciones.
	 *
	 * @param stage ventana principal de JavaFX donde se carga la interfaz
	 */
	
	public void mostrar(Stage stage) {
		stage.setTitle("Gestor de Eventos");

		Label titulo = new Label("📅 Agenda de Jone 😊");
		titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #1E3A8A;");

		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(15);
		root.setStyle(ESTILO_FONDO);

		// --- DEFINICIÓN DE BOTONES ---
		Button btnAgregar = crearBoton("➕ Añadir Evento", "#2E7D32");
		Button btnListar = crearBoton("📋 Listar Eventos", "#81C784");
		Button btnEliminar = crearBoton("🗑️ Eliminar Evento", "#6B8E23");
		Button btnCompletar = crearBoton("✔ Marcar Completado", "#00C853");

		// --- ASIGNACIÓN DE EVENTOS (LLAMADA A MÉTODOS) ---
		btnAgregar.setOnAction(e -> abrirVentanaAñadir());
		btnListar.setOnAction(e -> abrirVentanaListar());
		btnEliminar.setOnAction(e -> abrirVentanaEliminar());
		btnCompletar.setOnAction(e -> abrirVentanaCompletar());

		root.getChildren().addAll(titulo, btnAgregar, btnListar, btnEliminar, btnCompletar);

		Scene scene = new Scene(root, 400, 400);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * Abre una ventana para añadir un nuevo evento.
	 * Permite seleccionar tipo de evento, prioridad, fecha y datos adicionales.
	 */
	
	private void abrirVentanaAñadir() {
		Stage ventana = new Stage();
		ventana.setTitle("Añadir Evento");

		VBox layout = new VBox(10);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle(ESTILO_FONDO);
		layout.setPadding(new Insets(25));

		// CAMPOS COMUNES
		TextField txtNombre = new TextField();
		txtNombre.setPromptText("Nombre...");

		ComboBox<String> comboTipo = new ComboBox<>();
		comboTipo.getItems().addAll("Personal", "Trabajo", "Formacion");
		comboTipo.setValue("Personal");

		ComboBox<Prioridad> comboPrioridad = new ComboBox<>();
		comboPrioridad.getItems().addAll(Prioridad.values());
		comboPrioridad.setValue(Prioridad.MEDIA);

		DatePicker datePicker = new DatePicker(LocalDate.now());

		// --- PARTE DINÁMICA: EL CAMPO EXTRA ---
		Label lblExtra = new Label("Ubicación: "); // Por defecto para Personal
		TextField txtExtra = new TextField();
		txtExtra.setPromptText("¿Dónde es?");

		// CAMBIAMOS LA ETIQUETA SEGÚN EL TIPO SELECCIONADO
		comboTipo.setOnAction(e -> {
			String seleccion = comboTipo.getValue();

			switch (seleccion) {
			case "Personal":
				lblExtra.setText("Ubicación:");
				txtExtra.setPromptText("¿Dónde es?");
				break;

			case "Trabajo":
				lblExtra.setText("Cliente:");
				txtExtra.setPromptText("¿Para quién es?");
				break;
			case "Formacion":
				lblExtra.setText("Horas de duración:");
				txtExtra.setPromptText("Ej: 4");
				break;
			}
		});

		Button btnGuardar = new Button("GUARDAR");
		btnGuardar.setOnAction(ev -> {
			String nombre = txtNombre.getText();
			LocalDate fecha = datePicker.getValue();
			String valorExtra = txtExtra.getText();

			if (nombre.isEmpty() || fecha == null) {
				mostrarAlerta("Error", "Faltan datos por rellenar.");
				return;
			}

			Evento nuevo = null;
			try {
				switch (comboTipo.getValue()) {
				case "Personal":
					nuevo = new EventoPersonal(nombre, fecha, valorExtra, comboPrioridad.getValue());
					break;

				case "Trabajo":
					nuevo = new EventoTrabajo(nombre, fecha, valorExtra, comboPrioridad.getValue());
					break;
				case "Formacion":
					int horas = Integer.parseInt(valorExtra);
					nuevo = new EventoFormacion(nombre, fecha, horas, comboPrioridad.getValue());
					break;
				}

				gestor.agregarEvento(nuevo);
				mostrarAlerta("Éxito", "Evento guardado.");
				ventana.close();

			} catch (NumberFormatException e) {
				mostrarAlerta("Error", "En formacion las horas deben ser un número. ");
			}
		});

		layout.getChildren().addAll(new Label("NOMBRE:"), txtNombre, new Label("TIPO:"), comboTipo,
				new Label("PRIORIDAD:"), comboPrioridad, new Label("FECHA:"), datePicker, lblExtra, txtExtra,
				btnGuardar);

		ventana.setScene(new Scene(layout, 380, 500)); // TAMAÑO EXTRA PARA QUE NO ROZE
		ventana.show();
	}

	/**
	 * Abre una ventana donde se muestran todos los eventos registrados.
	 * Los eventos se colorean según su prioridad.
	 */
	
	private void abrirVentanaListar() {
		Stage ventana = new Stage();
		ventana.setTitle("Lista de Eventos");

		VBox layout = new VBox(10);
		layout.setStyle(ESTILO_FONDO);

		ListView<Evento> lista = new ListView<>();
		lista.getItems().addAll(gestor.listarEventos());

		// PERSONALIZACIÓN DE CELDAS
		lista.setCellFactory(param -> new ListCell<Evento>() {
			@Override
			protected void updateItem(Evento evento, boolean empty) {
				super.updateItem(evento, empty);
				if (empty || evento == null) {
					setText(null);
					setStyle("");
				} else {
					setText(evento.toString());
					if (evento.getPrioridad() == Prioridad.ALTA)
						setStyle("-fx-background-color: #ff9999;");
					else if (evento.getPrioridad() == Prioridad.MEDIA)
						setStyle("-fx-background-color: #fff599;");
					else
						setStyle("-fx-background-color: #b6ffb6;");
				}
			}
		});

		layout.getChildren().add(lista);
		ventana.setScene(new Scene(layout, 400, 300));
		ventana.show();
	}

	/**
	 * Abre una ventana que permite eliminar un evento seleccionado de la lista.
	 */
	
	private void abrirVentanaEliminar() {
		Stage ventana = new Stage();
		ventana.setTitle("Eliminar Evento");

		ListView<Evento> lista = new ListView<>();
		lista.getItems().addAll(gestor.listarEventos());

		Button btnBorrar = new Button("ELIMINAR SELECCIONADO");
		btnBorrar.setOnAction(ev -> {
			int indice = lista.getSelectionModel().getSelectedIndex();
			if (indice >= 0) {
				gestor.eliminarEvento(indice);
				lista.getItems().remove(indice);
				mostrarAlerta("Borrado", "Evento eliminado con éxito.");
			}
		});

		VBox layout = new VBox(10, lista, btnBorrar);
		layout.setStyle(ESTILO_FONDO);
		ventana.setScene(new Scene(layout, 350, 400));
		ventana.show();
	}

	/**
	 * Abre una ventana que permite marcar un evento como completado.
	 */
	
	private void abrirVentanaCompletar() {
		Stage ventana = new Stage();
		ventana.setTitle("Marcar Completado");

		ListView<Evento> lista = new ListView<>();
		lista.getItems().addAll(gestor.listarEventos());

		Button btnOk = new Button("MARCAR COMO COMPLETADO");
		btnOk.setOnAction(ev -> {
			int indice = lista.getSelectionModel().getSelectedIndex();
			if (indice >= 0) {
				gestor.marcarEventoComoCompletado(indice);
				lista.refresh();
			}
		});

		VBox layout = new VBox(10, lista, btnOk);
		layout.setStyle(ESTILO_FONDO);
		ventana.setScene(new Scene(layout, 350, 400));
		ventana.show();
	}

	/**
	 * Crea un botón con texto y color personalizado.
	 *
	 * @param texto texto que aparece en el botón
	 * @param colorHex color de fondo en formato hexadecimal
	 * @return botón configurado
	 */
	
	private Button crearBoton(String texto, String colorHex) {
		Button b = new Button(texto);
		b.setStyle("-fx-background-color: " + colorHex
				+ "; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 8; -fx-min-width: 220;");
		return b;
	}

	/**
	 * Muestra una alerta informativa en pantalla.
	 *
	 * @param titulo título de la alerta
	 * @param msg mensaje que se muestra al usuario
	 */
	
	private void mostrarAlerta(String titulo, String msg) {
		Alert alerta = new Alert(Alert.AlertType.INFORMATION);
		alerta.setTitle(titulo);
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		alerta.showAndWait();
	}
}