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
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jornadas.shared.usuario.Usuario;

public class Sistema_Administrador_De_Jornadas implements EntryPoint {
	
	ServicioAsync Servicio = GWT.create(Servicio.class);
	
	private Label lbl;
	private TextBox textBoxID, textBoxDNI;
	private Button botonConsultar, botonAgregar;
	
	public void onModuleLoad() {		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSize("100%", "100%"); 
		verticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		verticalPanel.getElement().getStyle().setBackgroundColor("#262B71");
		
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
		
		
		verticalPanel.add(textBoxDNI);
		verticalPanel.add(textBoxID);
		verticalPanel.add(botonConsultar);
		verticalPanel.add(botonAgregar);
		verticalPanel.add(lbl);
		
		RootLayoutPanel.get().add(verticalPanel);
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
					else
						lbl.setText("DNI: " +resultado.obtenerDNI() + " ID: " + resultado.obtenerID());
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
