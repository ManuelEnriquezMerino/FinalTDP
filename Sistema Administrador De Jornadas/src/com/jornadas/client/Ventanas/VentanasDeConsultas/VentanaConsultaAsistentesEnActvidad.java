package com.jornadas.client.Ventanas.VentanasDeConsultas;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.jornadas.client.ServicioAsync;
import com.jornadas.client.Ventanas.VentanasAbstractas.VentanaPanelVerticalYServicio;
import com.jornadas.shared.actividad.Actividad;
import com.jornadas.shared.usuario.Asistente;

public class VentanaConsultaAsistentesEnActvidad extends VentanaPanelVerticalYServicio{

	protected TextArea textAreaAsistentes;
	protected ListBox listBoxActividades; 
	protected Map<String,Actividad> Actividades;
	
	public VentanaConsultaAsistentesEnActvidad(ServicioAsync servicio) {
		super(servicio);
		Panel.getElement().getStyle().setBackgroundColor("#e690ec");
		
		Nombre = "Inscriptos a Actividad";
		
		inicializarListBox();
		inicializarTextArea();
		
		Panel.add(listBoxActividades);
		Panel.add(textAreaAsistentes);
	}
	
	private void inicializarListBox() {
		Actividades = new HashMap<String,Actividad>();
		listBoxActividades = new ListBox();
		listBoxActividades.addItem("Actividades");
		listBoxActividades.addChangeHandler(new cambioActividad());

		Servicio.obtenerActividades(new AsyncCallback<Collection<Actividad>>() {
			public void onFailure(Throwable caught) {
				Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
			}

			public void onSuccess(Collection<Actividad> resultado) {
				for(Actividad actividad : resultado) {
					Actividades.put(actividad.obtenerTitulo(), actividad);
					listBoxActividades.addItem(actividad.obtenerTitulo());
				}
			}
		});
	}
	
	private void inicializarTextArea() {
		textAreaAsistentes = new TextArea();
		textAreaAsistentes.setReadOnly(true);
		textAreaAsistentes.setCharacterWidth(150);
		textAreaAsistentes.setVisibleLines(25);
	}
	
	private class cambioActividad implements ChangeHandler {

		public void onChange(ChangeEvent event) {
			Actividad actividadElegida = Actividades.get(listBoxActividades.getSelectedItemText());
			
			if (actividadElegida!=null) {
				String Aux = "";
			
				for(Asistente asistente : actividadElegida.obtenerAsistentes()) {
					Aux += asistente.obtenerID() + ": " + asistente.obtenerNombre() + " " + asistente.obtenerApellido() + ", " +
							asistente.obtenerDNI() + ", " + asistente.obtenerLU() + ", " +
							asistente.obtenerFechaDeNacimiento().obtenerDia() + "/" +
							asistente.obtenerFechaDeNacimiento().obtenerMes() + "/" + 
							asistente.obtenerFechaDeNacimiento().obtenerAnio() + ", " +
							asistente.obtenerUniversidad() + ", " + asistente.obtenerUniversidad() + ", " +
							asistente.obtenerTelefono() + ", " + asistente.obtenerMail() + "\r\n";
				}
			
				textAreaAsistentes.setText(Aux);
			} else 
				textAreaAsistentes.setText("");
		}
	}

}
