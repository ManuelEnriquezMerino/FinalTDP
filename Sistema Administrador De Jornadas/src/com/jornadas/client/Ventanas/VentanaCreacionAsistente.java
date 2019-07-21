package com.jornadas.client.Ventanas;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jornadas.client.ServicioAsync;

public class VentanaCreacionAsistente extends Ventana{

protected VerticalPanel panelLoggeo;
	
	protected ServicioAsync Servicio;

	private HorizontalPanel panelDNI;
	private Label etiquetaDNI;
	private Label respuesta;
	private TextBox textBoxDNI;
	private Button botonAgregar;
	
	public VentanaCreacionAsistente(ServicioAsync servicio) {
		Nombre = "Agregar Asistente";
		
		Servicio = servicio;
		
		inicializarComponentes();
		
		poblarPanel();
	}
	
	public void inicializarComponentes() {
		panelLoggeo = new VerticalPanel();
		panelLoggeo.setSize("100%", "100%"); 
		panelLoggeo.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		panelLoggeo.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		panelLoggeo.getElement().getStyle().setBackgroundColor("#b52561");
		
		etiquetaDNI = new Label("DNI:");
		etiquetaDNI.getElement().getStyle().setColor("#fffff0");
		textBoxDNI = new TextBox();
		
		botonAgregar = new Button("Agregar Asistente");
		botonAgregar.addClickHandler(new oyenteAgregar());
		
		respuesta = new Label();
		respuesta.getElement().getStyle().setColor("#fffff0");
		respuesta.getElement().getStyle().setFontSize(2, Unit.EM);
		
		panelDNI = new HorizontalPanel();
		panelDNI.add(etiquetaDNI);
		panelDNI.add(textBoxDNI);
	}
	
	public void poblarPanel() {
		panelLoggeo.add(panelDNI);
		panelLoggeo.add(respuesta);
		panelLoggeo.add(botonAgregar);
	}
	
	public Panel obtenerPanel() {
		return panelLoggeo;
	}
	
	private class oyenteAgregar implements ClickHandler {

		public void onClick(ClickEvent event) {			
			Servicio.registrarAsistente(textBoxDNI.getText(), new AsyncCallback<String>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Exception Received from server.");
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
