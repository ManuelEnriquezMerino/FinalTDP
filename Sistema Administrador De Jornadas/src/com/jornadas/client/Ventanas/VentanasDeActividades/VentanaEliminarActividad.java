package com.jornadas.client.Ventanas.VentanasDeActividades;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.jornadas.client.ServicioAsync;
import com.jornadas.client.Ventanas.VentanasAbstractas.VentanaPanelVerticalYServicio;
import com.jornadas.shared.actividad.Actividad;

public class VentanaEliminarActividad extends VentanaPanelVerticalYServicio{

	protected ListBox listBoxActividades;
	protected Map<String,Actividad> Actividades;
	protected Button botonEliminar;
	
	public VentanaEliminarActividad(ServicioAsync servicio) {
		super(servicio);
		
		Nombre = "Eliminar Actividad";
		
		Panel.getElement().getStyle().setBackgroundColor("#98BCDC");
		
		inicializarListBox();
		
		botonEliminar = new Button("Eliminar Actividad");
		botonEliminar.addClickHandler(new oyenteEliminar());
		
		Panel.add(listBoxActividades);
		Panel.add(botonEliminar);
	}
	
	protected void inicializarListBox() {
		Actividades = new HashMap<String,Actividad>();
		listBoxActividades = new ListBox();
		listBoxActividades.addItem("Actividades");
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

	private class oyenteEliminar implements ClickHandler {

		public void onClick(ClickEvent event) {
			Actividad actividad = Actividades.get(listBoxActividades.getSelectedItemText());
			
			if(actividad!=null) {
				Servicio.eliminarActividad(actividad.obtenerID(), new AsyncCallback<Boolean> () {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
					}

					@Override
					public void onSuccess(Boolean resultado) {
						if(resultado)
							Window.alert("Actividad eliminada con exito");
						else
							Window.alert("Error al eliminar la actividad");
					}
					
				});
			}
		}
	}
}
