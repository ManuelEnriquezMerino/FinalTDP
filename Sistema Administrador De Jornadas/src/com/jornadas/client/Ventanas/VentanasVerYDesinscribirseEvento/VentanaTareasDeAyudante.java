package com.jornadas.client.Ventanas.VentanasVerYDesinscribirseEvento;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jornadas.client.ServicioAsync;
import com.jornadas.client.Ventanas.VentanasAbstractas.VentanaEventoUsuario;
import com.jornadas.shared.extras.Fecha;
import com.jornadas.shared.extras.Hora;
import com.jornadas.shared.tarea.Tarea;
import com.jornadas.shared.usuario.Voluntario;

public class VentanaTareasDeAyudante extends VentanaEventoUsuario{

	
	protected Voluntario Ayudante;
	protected Map<String,Tarea> Tareas;
	
	public VentanaTareasDeAyudante(Voluntario ayudante, ServicioAsync servicio) {
		super(servicio);
		
		Nombre = "Mis Tareas";
		Ayudante=ayudante;
		Panel.getElement().getStyle().setBackgroundColor("#F5A9BC");
		
		Tareas = new HashMap<String,Tarea>();
		
		establecerListaDeActividades();
		
		botonDesanotarse.addClickHandler(new oyenteDesinscribir());
	}
	
	
	protected void establecerListaDeActividades() {
		listBoxEvento.addChangeHandler(new cambioActividad());
		
		Collection<Tarea> tareas = Ayudante.obtenerTareas();
		
		for(Tarea tarea : tareas) {
			Tareas.put(tarea.obtenerTitulo(), tarea);
			listBoxEvento.addItem(tarea.obtenerTitulo());
		}
	}
	
	private class cambioActividad implements ChangeHandler {

		public void onChange(ChangeEvent event) {
			Tarea tareaElegida = Tareas.get(listBoxEvento.getSelectedItemText());
			if(tareaElegida!=null) {
				labelDescripcion.setText(tareaElegida.obtenerDescripcion());
				Fecha fechaActividad = tareaElegida.obtenerFecha();
				labelFecha.setText("Fecha: " + fechaActividad.obtenerDia() + "/" + fechaActividad.obtenerMes() + "/" + fechaActividad.obtenerAnio());
				Hora horaInicio = tareaElegida.obtenerHorarioInicio();
				Hora horaFin = tareaElegida.obtenerHorarioFin();
				labelHora.setText("Inicio: " + horaInicio.obtenerHora() + ":" + horaInicio.obtenerMinutos() + " - Fin: " + horaFin.obtenerHora() + ":" + horaFin.obtenerMinutos());
				labelLugar.setText(tareaElegida.obtenerLugar());
			}
		}
	}
	
	protected class oyenteDesinscribir implements ClickHandler {

		public void onClick(ClickEvent event) {
			Tarea tareaElegida = Tareas.get(listBoxEvento.getSelectedItemText());
			if(tareaElegida!=null) {
				Servicio.desinscribirAyudanteATarea(Ayudante.obtenerID(), Ayudante.obtenerDNI(), tareaElegida.obtenerID(), new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
					}

					@Override
					public void onSuccess(Boolean resultado) {
						if(resultado) {
							Window.alert("Usuario desinscripto a la Tarea con Exito");
							listBoxEvento.removeItem(listBoxEvento.getSelectedIndex());
						}
						else
							Window.alert("Error al desinscribir al usuario en la tarea. Por favor vuelva a intentarlo");
					}
				});
			}
		}
	}
}
