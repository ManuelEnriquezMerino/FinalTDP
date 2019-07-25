package com.jornadas.client.Ventanas.VentanasGenerales;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.jornadas.client.Ventanas.VentanasAbstractas.Ventana;

public class VentanaPestanias extends Ventana{

	private TabLayoutPanel PanelPestanias;
	private LayoutPanel Panel;
	
	public VentanaPestanias() {
		Nombre = "Pestañas";
		
		Panel = new LayoutPanel();
		Panel.setSize("100%", "100%");
		PanelPestanias = new TabLayoutPanel(2, Unit.EM);

		Panel.add(PanelPestanias);
	}
	
	public void agregarPestania(Ventana ventana) {
		PanelPestanias.add(ventana.obtenerPanel(), ventana.obtenerNombre());
	}
	
	public Panel obtenerPanel() {
		return Panel;
	}
}
