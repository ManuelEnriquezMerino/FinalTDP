package com.jornadas.client.Ventanas;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.jornadas.client.ServicioAsync;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Panel;

public abstract class VentanaPanelVerticalYServicio extends Ventana{

	protected ServicioAsync Servicio;
	protected VerticalPanel Panel;
	
	protected VentanaPanelVerticalYServicio(ServicioAsync servicio) {
		Servicio = servicio;
		Panel = new VerticalPanel();
		Panel.setSize("100%", "100%"); 
		Panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		Panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	}
	
	public Panel obtenerPanel() {
		return Panel;
	}
}
