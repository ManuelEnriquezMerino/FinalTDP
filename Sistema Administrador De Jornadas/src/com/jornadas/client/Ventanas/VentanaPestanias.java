package com.jornadas.client.Ventanas;

import java.util.Collection;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

public class VentanaPestanias extends Ventana{

	private TabLayoutPanel PanelPestanias;
	private LayoutPanel Panel;
	
	public VentanaPestanias(Collection<Ventana> ventanas) {
		Nombre = "Pestañas";
		
		Panel = new LayoutPanel();
		Panel.setSize("100%", "100%");
		PanelPestanias = new TabLayoutPanel(1.5, Unit.EM);
		
		for(Ventana p : ventanas) {
			PanelPestanias.add(p.obtenerPanel(), p.obtenerNombre());
		}
		
		Panel.add(PanelPestanias);
	}
	
	public Panel obtenerPanel() {
		return Panel;
	}
}
