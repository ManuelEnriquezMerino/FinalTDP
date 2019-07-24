package com.jornadas.client.Ventanas.VentanasInscripcionEventos;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.jornadas.client.ServicioAsync;
import com.jornadas.client.Ventanas.VentanasAbstractas.VentanaPanelVerticalYServicio;
import com.jornadas.shared.extras.Fecha;
import com.jornadas.shared.extras.Hora;
import com.jornadas.shared.tarea.Tarea;
import com.jornadas.shared.usuario.Voluntario;

public class VentanaInscripcionTarea extends VentanaPanelVerticalYServicio{
	
	protected Voluntario Ayudante;
	protected ListBox listBoxTareas; 
	protected Map<String,Tarea> Tareas;
	protected Label labelDescripcion, labelFecha, labelLugar, labelHora;
	protected Button botonInscribirse;
	
	public VentanaInscripcionTarea(Voluntario ayudante, ServicioAsync servicio) {
		super(servicio);
		Ayudante = ayudante;
		
		Nombre = "Inscripcion a Tarea";
		Panel.getElement().getStyle().setBackgroundColor("#c6ece7");
		
		inicializarLabels();
		inicializarListBox();
		
		botonInscribirse = new Button("Inscribirse a Tarea");
		botonInscribirse.addClickHandler(new oyenteInscribir());
		
		poblarPanel();
	}
	
	protected void inicializarLabels() {
		labelDescripcion = new Label();
		labelFecha = new Label();
		labelLugar = new Label();
		labelHora = new Label();
	}
	
	protected void inicializarListBox() {
		Tareas = new HashMap<String,Tarea>();
		listBoxTareas = new ListBox();
		listBoxTareas.addItem("Tareas");
		listBoxTareas.addChangeHandler(new cambioActividad());
		Servicio.obtenerTareas(new AsyncCallback<Collection<Tarea>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
			}

			@Override
			public void onSuccess(Collection<Tarea> resultado) {
				for(Tarea tarea : resultado) {
					if(tarea.estaDisponible()) {
						Tareas.put(tarea.obtenerTitulo(), tarea);
						listBoxTareas.addItem(tarea.obtenerTitulo());
					}
				}
			}
		});
	}
	
	protected void poblarPanel() {
		Panel.add(listBoxTareas);
		Panel.add(labelDescripcion);
		Panel.add(labelFecha);
		Panel.add(labelHora);
		Panel.add(labelLugar);
		Panel.add(botonInscribirse);
	}
	
	private class cambioActividad implements ChangeHandler {

		public void onChange(ChangeEvent event) {
			Tarea tareaElegida = Tareas.get(listBoxTareas.getSelectedItemText());
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
	
	private class oyenteInscribir implements ClickHandler {

		public void onClick(ClickEvent event) {
			Tarea tareaElegida = Tareas.get(listBoxTareas.getSelectedItemText());
			if(tareaElegida!=null) {
				Servicio.inscribirAyudanteATarea(Ayudante.obtenerID(), Ayudante.obtenerDNI(), tareaElegida.obtenerID(), new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
					}

					@Override
					public void onSuccess(Boolean resultado) {
						if(resultado) {
							Window.alert("Usuario inscripto a la Tarea con Exito");
							listBoxTareas.removeItem(listBoxTareas.getSelectedIndex());
						}
						else
							Window.alert("Error al inscribir al usuario en la tarea, verificar que la tarea no tenga un ayudante Asignado");
					}
				});
			} else {
				Window.alert("Elija alguna actividad");
			}
		}
	}
}
