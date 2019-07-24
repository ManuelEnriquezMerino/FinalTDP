package com.jornadas.client.Ventanas;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.jornadas.client.ServicioAsync;

public abstract class VentanaEventoUsuario extends Ventana{

	protected ServicioAsync Servicio;
	protected Label labelDescripcion, labelFecha, labelLugar, labelHora;
	protected HorizontalPanel Panel;
	protected VerticalPanel panelLabels;
	protected ListBox listBoxEvento;
	
	public VentanaEventoUsuario() {
		Panel = new HorizontalPanel();
		Panel.setSize("100%", "100%"); 
		Panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		inicializarLabels();
		inicializarListaDeActividades();
		
		poblarPanel();
	}
	
	protected void inicializarLabels() {
		labelDescripcion = new Label();
		labelFecha = new Label();
		labelLugar = new Label();
		labelHora = new Label();
		
		panelLabels = new VerticalPanel();
		panelLabels.add(labelDescripcion);
		panelLabels.add(labelFecha);
		panelLabels.add(labelHora);
		panelLabels.add(labelLugar);
		
		for(Widget w : panelLabels) {
			w.getElement().getStyle().setFontSize(1.5, Unit.EM);
		}
		
		panelLabels.setSize("50%", "50%"); 
	}
	
	protected void inicializarListaDeActividades() {
		listBoxEvento = new ListBox();
		listBoxEvento.getElement().getStyle().setWidth(200, Unit.PX);
		listBoxEvento.setVisibleItemCount(20);
	}
	
	protected void poblarPanel() {
		Panel.add(listBoxEvento);
		Panel.add(panelLabels);
	}
	
	public Panel obtenerPanel() {
		return Panel;
	}
}