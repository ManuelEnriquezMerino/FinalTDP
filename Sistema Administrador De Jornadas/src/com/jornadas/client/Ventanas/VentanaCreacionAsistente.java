package com.jornadas.client.Ventanas;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.jornadas.client.ServicioAsync;

public class VentanaCreacionAsistente extends VentanaCreacionUsuario{

	private Button botonAgregar;
	
	public VentanaCreacionAsistente(ServicioAsync servicio) {
		super(servicio);
		
		Nombre = "Agregar Asistente";
		
		botonAgregar = new Button("Agregar Asistente");
		botonAgregar.addClickHandler(new oyenteAgregar());
		
		Panel.add(botonAgregar);
		
		Panel.getElement().getStyle().setBackgroundColor("#b52561");
		etiquetaDNI.getElement().getStyle().setColor("#fffff0");
		respuesta.getElement().getStyle().setColor("#fffff0");
	}
	
	private class oyenteAgregar implements ClickHandler {

		public void onClick(ClickEvent event) {			
			Servicio.registrarAsistente(textBoxDNI.getText(), new AsyncCallback<String>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
				}

				@Override
				public void onSuccess(String resultado) {
					if(resultado!=null)
						respuesta.setText("Usuario agregado con exito, su id es " + resultado);
					else
						respuesta.setText("Error: El usuario ya existe");
				}
			});
		}
    }
}
