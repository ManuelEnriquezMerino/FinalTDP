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
import com.jornadas.shared.actividad.Actividad;
import com.jornadas.shared.extras.Fecha;
import com.jornadas.shared.extras.Hora;
import com.jornadas.shared.usuario.Asistente;

public class VentanaInscripcionActividad extends VentanaPanelVerticalYServicio{
	
	protected Asistente Asistente;
	protected ListBox listBoxActividades; 
	protected Map<String,Actividad> Actividades;
	protected Label labelDescripcion, labelFecha, labelLugar, labelHora;
	protected Button botonInscribirse;
	
	public VentanaInscripcionActividad(Asistente asistente, ServicioAsync servicio) {
		super(servicio);
		Asistente = asistente;
		
		Nombre = "Inscripcion a Actividades";
		Panel.getElement().getStyle().setBackgroundColor("#c6ece7");
		
		inicializarLabels();
		inicializarListBox();
		
		botonInscribirse = new Button("Inscribirse a Actividad");
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
		Actividades = new HashMap<String,Actividad>();
		listBoxActividades = new ListBox();
		listBoxActividades.addItem("Actividades");
		listBoxActividades.addChangeHandler(new cambioActividad());
		Servicio.obtenerActividades(new AsyncCallback<Collection<Actividad>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
			}

			@Override
			public void onSuccess(Collection<Actividad> resultado) {
				for(Actividad act : resultado) {
					Actividades.put(act.obtenerTitulo(), act);
					listBoxActividades.addItem(act.obtenerTitulo());
				}
			}
		});
	}
	
	protected void poblarPanel() {
		Panel.add(listBoxActividades);
		Panel.add(labelDescripcion);
		Panel.add(labelFecha);
		Panel.add(labelHora);
		Panel.add(labelLugar);
		Panel.add(botonInscribirse);
	}
	
	private class cambioActividad implements ChangeHandler {

		public void onChange(ChangeEvent event) {
			Actividad actividadElegida = Actividades.get(listBoxActividades.getSelectedItemText());
			if(actividadElegida!=null) {
				labelDescripcion.setText(actividadElegida.obtenerDescripcion());
				Fecha fechaActividad = actividadElegida.obtenerFecha();
				labelFecha.setText("Fecha: " + fechaActividad.obtenerDia() + "/" + fechaActividad.obtenerMes() + "/" + fechaActividad.obtenerAnio());
				Hora horaInicio = actividadElegida.obtenerHorarioInicio();
				Hora horaFin = actividadElegida.obtenerHorarioFin();
				labelHora.setText("Inicio: " + horaInicio.obtenerHora() + ":" + horaInicio.obtenerMinutos() + " - Fin: " + horaFin.obtenerHora() + ":" + horaFin.obtenerMinutos());
				labelLugar.setText(actividadElegida.obtenerLugar());
			}
		}
		
	}
	
	private class oyenteInscribir implements ClickHandler {

		public void onClick(ClickEvent event) {
			Actividad actividadElegida = Actividades.get(listBoxActividades.getSelectedItemText());
			if(actividadElegida!=null) {
				Servicio.inscribirAsistenteAActividad(Asistente.obtenerID(), Asistente.obtenerDNI(), actividadElegida.obtenerID(), new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
					}

					@Override
					public void onSuccess(Boolean resultado) {
						if(resultado) {
							Window.alert("Usuario inscripto a la Actividad con Exito");
							listBoxActividades.removeItem(listBoxActividades.getSelectedIndex());
						}
						else
							Window.alert("Error al inscribir al usuario en la actividad, verificar que no este ya inscripto y que la actividad no este llena");
					}
				});
			} else {
				Window.alert("Elija alguna actividad");
			}
		}
	}
}
