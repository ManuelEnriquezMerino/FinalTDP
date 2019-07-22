package com.jornadas.client.Ventanas;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jornadas.client.ServicioAsync;

public class VentanaCreacionOrganizador extends Ventana{

	protected VerticalPanel Panel;
	protected ServicioAsync Servicio;
	
	public VentanaCreacionOrganizador(ServicioAsync servicio) {
		Servicio=servicio;
	}
	
	public Panel obtenerPanel() {
		return Panel;
	}

}
