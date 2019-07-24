package com.jornadas.client.Ventanas.VentanasGenerales;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.jornadas.client.ServicioAsync;
import com.jornadas.client.Ventanas.VentanasAbstractas.VentanaPanelVerticalYServicio;
import com.jornadas.shared.Visitor.GenerarVentanas;
import com.jornadas.shared.usuario.Usuario;

public class VentanaSistema extends VentanaPanelVerticalYServicio{

	protected Usuario Usuario;
	protected Button botonCerrarSesion, botonActualizar;
	
	public VentanaSistema(Usuario usuario, ServicioAsync servicio) {
		super(servicio);
		Nombre = "Sistema";
		Panel.getElement().getStyle().setBackgroundColor("#ffdead");
		
		Usuario = usuario;
		
		botonCerrarSesion = new Button("Cerrar Sesion");
		botonCerrarSesion.addClickHandler(new oyenteCerrarSesion());
		botonActualizar = new Button("Actualizar");
		botonActualizar.addClickHandler(new oyenteActualizar());
		
		Panel.add(botonCerrarSesion);
		Panel.add(botonActualizar);
	}
	
	private class oyenteCerrarSesion implements ClickHandler {

		public void onClick(ClickEvent event) {
			RootLayoutPanel.get().remove(0);
			VentanaLoggeo logeo = new VentanaLoggeo(Servicio);
			RootLayoutPanel.get().add(logeo.obtenerPanel());
		}
	}
	
	private class oyenteActualizar implements ClickHandler {
		public void onClick(ClickEvent event) {
			Servicio.obtenerUsuario(Usuario.obtenerID(), Usuario.obtenerDNI(), new AsyncCallback<Usuario>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
				}

				@Override
				public void onSuccess(Usuario resultado) {
					if(resultado==null)
						Window.alert("ERROR: No es posible actualizar en este momento, por favor vuelva a intentarlo mas tarde");
					else {
						RootLayoutPanel.get().remove(0);
						GenerarVentanas ventana = new GenerarVentanas(Servicio);
						resultado.accionar(ventana);
						RootLayoutPanel.get().add(ventana.obtenerVentana().obtenerPanel());
					}
				}
			});
		}
	}
}
