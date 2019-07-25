package com.jornadas.client.Ventanas.VentanasDeTareas;

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
import com.jornadas.shared.tarea.Tarea;

public class VentanaEliminarTarea extends VentanaPanelVerticalYServicio{

	protected ListBox listBoxTareas;
	protected Map<String,Tarea> Tareas;
	protected Button botonEliminar;
	
	public VentanaEliminarTarea(ServicioAsync servicio) {
		super(servicio);
		
		Nombre = "Eliminar Tarea";
		
		Panel.getElement().getStyle().setBackgroundColor("#98BCDC");
		
		inicializarListBox();
		
		botonEliminar = new Button("Eliminar Tarea");
		botonEliminar.addClickHandler(new oyenteEliminar());
		
		Panel.add(listBoxTareas);
		Panel.add(botonEliminar);
	}
	
	protected void inicializarListBox() {
		Tareas = new HashMap<String,Tarea>();
		listBoxTareas = new ListBox();
		listBoxTareas.addItem("Tareas");
		Servicio.obtenerTareas(new AsyncCallback<Collection<Tarea>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
			}

			@Override
			public void onSuccess(Collection<Tarea> resultado) {
				for(Tarea act : resultado) {
					Tareas.put(act.obtenerTitulo(), act);
					listBoxTareas.addItem(act.obtenerTitulo());
				}
			}
		});
	}

	private class oyenteEliminar implements ClickHandler {

		public void onClick(ClickEvent event) {
			Tarea tarea = Tareas.get(listBoxTareas.getSelectedItemText());
			
			if(tarea!=null) {
				Servicio.eliminarTarea(tarea.obtenerID(), new AsyncCallback<Boolean> () {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
					}

					@Override
					public void onSuccess(Boolean resultado) {
						if(resultado)
							Window.alert("Tarea eliminada con exito");
						else
							Window.alert("Error al eliminar la tarea");
					}
					
				});
			}
		}
	}
}
