package com.jornadas.client.Ventanas;

import java.util.Collection;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

public class VentanaPestanias {

	private TabLayoutPanel PanelPestanias;
	private LayoutPanel Panel;
	
	public VentanaPestanias(Collection<Panel> paneles) {
		Panel = new LayoutPanel();
		Panel.setSize("100%", "100%");
		PanelPestanias = new TabLayoutPanel(1.5, Unit.EM);
		
		for(Panel p : paneles) {
			PanelPestanias.add(p, "NOMBRE");
		}
		
		Panel.add(PanelPestanias);
	}
	
	public Panel obtenerPanel() {
		return Panel;
	}
}
