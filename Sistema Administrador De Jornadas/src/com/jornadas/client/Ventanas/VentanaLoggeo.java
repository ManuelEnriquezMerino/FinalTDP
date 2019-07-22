package com.jornadas.client.Ventanas;

import java.util.Collection;
import java.util.LinkedList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.jornadas.client.ServicioAsync;
import com.jornadas.shared.usuario.Usuario;

public class VentanaLoggeo extends VentanaPanelVerticalYServicio{

	protected Label lbl;
	protected TextBox textBoxID, textBoxDNI;
	protected Button botonConsultar;
	
	public VentanaLoggeo(ServicioAsync servicio) {
		super(servicio);
		
		Panel.getElement().getStyle().setBackgroundColor("#262B71");
		
		textBoxID = new TextBox();
		textBoxID.setText("ID Aca");
		
		textBoxDNI = new TextBox();
		textBoxDNI.setText("DNI Aca");
		
		botonConsultar = new Button("Obtener Usuario");
		botonConsultar.addClickHandler(new oyenteConsultar());
		
		lbl = new Label();
		lbl.getElement().getStyle().setColor("#fffff0");
		
		
		Panel.add(textBoxDNI);
		Panel.add(textBoxID);
		Panel.add(botonConsultar);
		Panel.add(lbl);
	}
	
	private class oyenteConsultar implements ClickHandler {

		public void onClick(ClickEvent event) {
			System.out.println("CLICK");
			
			Servicio.obtenerUsuario(textBoxID.getText(), textBoxDNI.getText(), new AsyncCallback<Usuario>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
				}

				@Override
				public void onSuccess(Usuario resultado) {
					if(resultado==null)
						lbl.setText("ERROR: Usuario no encontrado");
					else {
						RootLayoutPanel.get().remove(0);
						
						VentanaDatos datos = new VentanaDatos(resultado, Servicio);
						VentanaArea area = new VentanaArea(Servicio);
						VentanaCreacionAsistente creacion = new VentanaCreacionAsistente(Servicio);
						VentanaCreacionActividad actividad = new VentanaCreacionActividad(Servicio);
						VentanaCreacionTarea tarea = new VentanaCreacionTarea(Servicio);
						
						Collection<Ventana> paneles = new LinkedList<Ventana>();
						
						paneles.add(datos);
						paneles.add(area);
						paneles.add(creacion);
						paneles.add(actividad);
						paneles.add(tarea);
						
						VentanaPestanias pestanias = new VentanaPestanias(paneles);
						
						RootLayoutPanel.get().add(pestanias.obtenerPanel());
					}
				}
			});
		}
    }
}
