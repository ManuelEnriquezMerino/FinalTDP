package com.jornadas.client.Ventanas.VentanasDeAreas;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.jornadas.client.ServicioAsync;
import com.jornadas.client.Ventanas.VentanasAbstractas.VentanaPanelVerticalYServicio;
import com.jornadas.shared.Visitor.OrganizadoresConArea;
import com.jornadas.shared.actividad.Area;
import com.jornadas.shared.usuario.Organizador;
import com.jornadas.shared.usuario.Usuario;

public class VentanaAsignarAreaOrganizador extends VentanaPanelVerticalYServicio{

	protected HorizontalPanel panelRadio;
	protected Map<RadioButton,Area> Areas;
	protected Map<String, Organizador> Organizadores;
	protected ListBox listOrganizador;
	protected Button boton;
	
	public VentanaAsignarAreaOrganizador(ServicioAsync servicio) {
		super(servicio);
		Nombre = "Establecer Area";
		Panel.getElement().getStyle().setBackgroundColor("#dba77d");
		
		inicializarRadioButtons();
		inicializarLista();
		
		Panel.add(listOrganizador);
		Panel.add(panelRadio);
		
		boton = new Button("Establecer Area");
		boton.addClickHandler(new oyenteAsignar());
		
		Panel.add(boton);
	}
	
	protected void inicializarRadioButtons() {
		Areas = new HashMap<RadioButton,Area> ();
		panelRadio = new HorizontalPanel();
		
		Servicio.obtenerAreas(new AsyncCallback<Collection<Area>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
			}

			@Override
			public void onSuccess(Collection<Area> resultado) {
				for(Area area : resultado){
					Areas.put(new RadioButton("Areas", area.obtenerNombre()), area);
				}
				
				for(RadioButton boton : Areas.keySet()) {
					panelRadio.add(boton);
				}
			}
		});
	}
	
	protected void inicializarLista() {
		listOrganizador = new ListBox();
		listOrganizador.addChangeHandler(new cambioActividad());
		Organizadores = new HashMap<String,Organizador>();
		
		Servicio.obtenerUsuarios(new AsyncCallback<Collection<Usuario>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
			}

			@Override
			public void onSuccess(Collection<Usuario> resultado) {
				OrganizadoresConArea AccionOrganizadores = new OrganizadoresConArea();
				String Usuario;
				for(Usuario usuario : resultado) {
					usuario.accionar(AccionOrganizadores);
				}
				for(Organizador org : AccionOrganizadores.obtenerOrganizadores()) {
					Usuario = org.obtenerNombre() + " " + org.obtenerApellido();
					Organizadores.put(Usuario,org);
					listOrganizador.addItem(Usuario);
				}
			}
		});
	}
	
	private class cambioActividad implements ChangeHandler {
		public void onChange(ChangeEvent event) {
			Area AreaDelOrganizador = Organizadores.get(listOrganizador.getSelectedValue()).obtenerArea();
			
				if(AreaDelOrganizador!=null) {
					boolean encontre = false;
					String NombreDelArea = AreaDelOrganizador.obtenerNombre();
					Iterator<RadioButton> iterador = Areas.keySet().iterator();
					RadioButton radio = null;
					
					while(!encontre && iterador.hasNext()) {
						radio = iterador.next();
						radio.setValue(false);
						encontre = NombreDelArea.equals(radio.getText());
					}
					
					if(encontre) {
						radio.setValue(true);
						while(iterador.hasNext())
							iterador.next().setValue(false);
					}
			} else {
				for (RadioButton radio : Areas.keySet()) {
					radio.setValue(false);
				}
			}
		}
	}
	
	private Area obtenerAreaSeleccionada() {
		boolean encontre=false;
		Iterator<Entry<RadioButton,Area>> iterador = Areas.entrySet().iterator();
		Entry<RadioButton,Area> aux=null;
		
		while(!encontre && iterador.hasNext()) {
			aux=iterador.next();
			if(aux.getKey().getValue()) {
				encontre=true;
			}
		}
		
		if(encontre)
			return aux.getValue();
		else
			return null;
	}
	
	private class oyenteAsignar implements ClickHandler {

		public void onClick(ClickEvent event) {
			Organizador organizadorActual = Organizadores.get(listOrganizador.getSelectedValue());
			Area areaSeleccionada = obtenerAreaSeleccionada();
			
			if(organizadorActual!=null && areaSeleccionada!=null) {
				Servicio.asignarAreaAOrganizador(organizadorActual.obtenerID(), organizadorActual.obtenerDNI(), areaSeleccionada.obtenerID(), new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
					}

					@Override
					public void onSuccess(Boolean resultado) {
						if(resultado)
							Window.alert("Establecida el area del organizador correctamente");
						else
							Window.alert("Error al establecer el area del organizador");
					}
				});
			} else
				Window.alert("Seleccione un organizador y un area");
		}
	}	
}
