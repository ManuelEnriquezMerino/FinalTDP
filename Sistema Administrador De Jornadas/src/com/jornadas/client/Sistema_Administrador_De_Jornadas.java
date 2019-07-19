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
import com.jornadas.client.Ventanas.VentanaArea;
import com.jornadas.client.Ventanas.VentanaDatos;
import com.jornadas.shared.actividad.Area;
import com.jornadas.shared.actividad.TipoActividad;
import com.jornadas.shared.excepciones.FechaInvalidaException;
import com.jornadas.shared.usuario.CoordinadorDeArea;
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
		
		Area area = new Area();
		area.establecerID("" + 1);
		area.establecerNombre("Charlas");
		
		
		CoordinadorDeArea c = new CoordinadorDeArea();
		c.establecerApellido("z");
		c.establecerCarrera("z");
		c.establecerDNI("z");
		try {
			c.establecerFechaDeNacimiento(1, 1, 1);
		} catch (FechaInvalidaException e) {
			e.printStackTrace();
		}
		c.establecerID("z");
		c.establecerLU("z");
		c.establecerMail("z");
		c.establecerNombre("z");
		c.establecerTelefono("z");
		c.establecerUniversidad("z");
		
		
		area.establecerCoordinador(c);
		
		TipoActividad tipo;
		tipo = new TipoActividad();
		tipo.establecerDescripcion("Descripcion: "+2);
		tipo.establecerID("ID: "+2);
		tipo.establecerTitulo("Titulo: "+2);
		
		area.agregarTipoDeActividad(tipo);
		
		VentanaArea cargarArea = new VentanaArea(area,Servicio);
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
