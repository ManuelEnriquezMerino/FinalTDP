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
import com.jornadas.shared.actividad.Actividad;
import com.jornadas.shared.extras.Fecha;
import com.jornadas.shared.extras.Hora;
import com.jornadas.shared.usuario.Asistente;

public class VentanaActividadesDeAsistente extends VentanaEventoUsuario{

	protected Asistente Asistente;
	protected Map<String,Actividad> Actividades;
	
	public VentanaActividadesDeAsistente(Asistente asistente, ServicioAsync servicio) {
		super(servicio);
		Nombre = "Mis Actividades";
		Asistente=asistente;
		Panel.getElement().getStyle().setBackgroundColor("#E1E9b7");
		
		Actividades = new HashMap<String,Actividad>();
		
		establecerListaDeActividades();
		
		botonDesanotarse.addClickHandler(new oyenteDesinscribir());
	}
	
	
	protected void establecerListaDeActividades() {
		listBoxEvento.addChangeHandler(new cambioActividad());
		
		Collection<Actividad> actividades = Asistente.obtenerActividadesInscriptas();
		
		for(Actividad actividad : actividades) {
			Actividades.put(actividad.obtenerTitulo(), actividad);
			listBoxEvento.addItem(actividad.obtenerTitulo());
		}
	}
	
	private class cambioActividad implements ChangeHandler {

		public void onChange(ChangeEvent event) {
			Actividad actividadElegida = Actividades.get(listBoxEvento.getSelectedItemText());
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
	
	protected class oyenteDesinscribir implements ClickHandler {

		public void onClick(ClickEvent event) {
			Actividad actividadElegida = Actividades.get(listBoxEvento.getSelectedItemText());
			if(actividadElegida!=null) {
				Servicio.desinscribirAsistenteAActividad(Asistente.obtenerID(), Asistente.obtenerDNI(), actividadElegida.obtenerID(), new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
					}

					@Override
					public void onSuccess(Boolean resultado) {
						if(resultado) {
							Window.alert("Usuario desinscripto a la Actividad con Exito");
							listBoxEvento.removeItem(listBoxEvento.getSelectedIndex());
						}
						else
							Window.alert("Error al desinscribir al usuario en la actividad. Por favor vuelva a intentarlo");
					}
				});
			}
		}
	}
}
