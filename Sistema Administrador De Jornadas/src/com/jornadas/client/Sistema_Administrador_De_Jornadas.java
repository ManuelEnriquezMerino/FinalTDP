package com.jornadas.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jornadas.client.Ventanas.VentanaCargarArea;
import com.jornadas.client.Ventanas.VentanaDatos;
import com.jornadas.shared.usuario.Usuario;

public class Sistema_Administrador_De_Jornadas implements EntryPoint {
	
	ServicioAsync Servicio = GWT.create(Servicio.class);
	
	protected VerticalPanel panelLoggeo;
	
	private Label lbl;
	private TextBox textBoxID, textBoxDNI;
	private Button botonConsultar, botonAgregar;
	
	public void onModuleLoad() {
		//crearPanelLoggeo();
		//RootLayoutPanel.get().add(panelLoggeo);
		
		VentanaCargarArea cargarArea = new VentanaCargarArea(Servicio);
		RootLayoutPanel.get().add(cargarArea.obtenerPanel());
	}
	
	private void crearPanelLoggeo() {
		panelLoggeo = new VerticalPanel();
		panelLoggeo.setSize("100%", "100%"); 
		panelLoggeo.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		panelLoggeo.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		panelLoggeo.getElement().getStyle().setBackgroundColor("#262B71");
		
		textBoxID = new TextBox();
		textBoxID.setText("ID Aca");
		
		textBoxDNI = new TextBox();
		textBoxDNI.setText("DNI Aca");
		
		botonConsultar = new Button("Obtener Usuario");
		botonConsultar.addClickHandler(new oyenteConsultar());
		
		botonAgregar = new Button("Agregar Usuario");
		botonAgregar.addClickHandler(new oyenteAgregar());
		
		lbl = new Label("OwO");
		lbl.getElement().getStyle().setColor("#fffff0");
		
		
		panelLoggeo.add(textBoxDNI);
		panelLoggeo.add(textBoxID);
		panelLoggeo.add(botonConsultar);
		panelLoggeo.add(botonAgregar);
		panelLoggeo.add(lbl);
	}
	
	private class oyenteConsultar implements ClickHandler {

		public void onClick(ClickEvent event) {
			System.out.println("CLICK");
			
			Servicio.obtenerUsuario(textBoxID.getText(), textBoxDNI.getText(), new AsyncCallback<Usuario>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Exception Received from server.");
				}

				@Override
				public void onSuccess(Usuario resultado) {
					if(resultado==null)
						lbl.setText("ERROR: Usuario no encontrado");
					else {
						RootLayoutPanel.get().remove(0);
						VentanaDatos datos = new VentanaDatos(resultado, Servicio);
						RootLayoutPanel.get().add(datos.obtenerPanel());
					}
				}
			});
		}
    }
	
	private class oyenteAgregar implements ClickHandler {

		public void onClick(ClickEvent event) {
			System.out.println("CLICK");
			
			Servicio.registrarUsuario(textBoxID.getText(), textBoxDNI.getText(), new AsyncCallback<Boolean>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Exception Received from server.");
				}

				@Override
				public void onSuccess(Boolean resultado) {
					if(resultado==true)
						lbl.setText("Usuario agregado con exito");
					else
						lbl.setText("Error: El usuario ya existe");
				}
			});
		}
    }
}
