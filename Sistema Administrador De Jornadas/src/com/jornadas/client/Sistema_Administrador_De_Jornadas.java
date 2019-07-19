package com.jornadas.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.jornadas.client.Ventanas.VentanaLoggeo;

public class Sistema_Administrador_De_Jornadas implements EntryPoint {
	
	ServicioAsync Servicio = GWT.create(Servicio.class);
	
	public void onModuleLoad() {
		VentanaLoggeo logeo = new VentanaLoggeo(Servicio);
		RootLayoutPanel.get().add(logeo.obtenerPanel());
	}
}
