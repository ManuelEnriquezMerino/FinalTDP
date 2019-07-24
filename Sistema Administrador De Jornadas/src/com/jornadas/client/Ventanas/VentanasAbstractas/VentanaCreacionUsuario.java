package com.jornadas.client.Ventanas.VentanasAbstractas;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.jornadas.client.ServicioAsync;

public abstract class VentanaCreacionUsuario extends VentanaPanelVerticalYServicio{

	protected HorizontalPanel panelDNI;
	protected Label etiquetaDNI;
	protected Label respuesta;
	protected TextBox textBoxDNI;
	
	public VentanaCreacionUsuario(ServicioAsync servicio) {
		super(servicio);
		
		inicializarComponentes();
		
		poblarPanel();
	}
	
	public void inicializarComponentes() {
		etiquetaDNI = new Label("DNI:");
		
		textBoxDNI = new TextBox();
		
		respuesta = new Label();
		
		respuesta.getElement().getStyle().setFontSize(2, Unit.EM);
		
		panelDNI = new HorizontalPanel();
		panelDNI.add(etiquetaDNI);
		panelDNI.add(textBoxDNI);
	}
	
	public void poblarPanel() {
		Panel.add(panelDNI);
		Panel.add(respuesta);
	}
}

	
