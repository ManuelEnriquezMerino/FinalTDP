package com.jornadas.client.Ventanas.VentanasDeCreacionDeUsuarios;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.jornadas.client.ServicioAsync;
import com.jornadas.client.Ventanas.VentanasAbstractas.VentanaCreacionUsuario;
import com.jornadas.shared.usuario.creadoresDeOrganizadores.CreadorAyudanteEvento;

public class VentanaCreacionAyudante extends VentanaCreacionUsuario{

	private Button botonAgregar;
	protected HorizontalPanel panelRadio;
	protected Map<RadioButton,CreadorAyudanteEvento> tiposDeAyudantes;
	
	public VentanaCreacionAyudante(ServicioAsync servicio) {
		super(servicio);
		Panel.getElement().getStyle().setBackgroundColor("#ffdbbe");
		
		Nombre = "Agregar Ayudante";
		
		botonAgregar = new Button("Agregar Ayudante");
		botonAgregar.addClickHandler(new oyenteAgregar());
		
		inicializarRadioButtons();
		
		Panel.add(panelRadio);
		
		Panel.add(botonAgregar);
	}
	
	protected void inicializarRadioButtons() {
		tiposDeAyudantes = new HashMap<RadioButton,CreadorAyudanteEvento> ();
		panelRadio = new HorizontalPanel();
		
		Servicio.obtenerTiposDeAyudantes(new AsyncCallback<Collection<CreadorAyudanteEvento>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
			}

			@Override
			public void onSuccess(Collection<CreadorAyudanteEvento> resultado) {
				RadioButton boton;
				for(CreadorAyudanteEvento creador : resultado) {
					boton = new RadioButton("TiposDeAyudantes", creador.obtenerNombre());
					panelRadio.add(boton);
					tiposDeAyudantes.put(boton, creador);
				}
			}
		});
	}
	
	public Panel obtenerPanel() {
		return Panel;
	}
	
	protected CreadorAyudanteEvento obtenerTipoAyudante() {
		CreadorAyudanteEvento retorno = null;
		boolean encontre = false;
		Iterator<Entry<RadioButton,CreadorAyudanteEvento>> iterador = tiposDeAyudantes.entrySet().iterator();
		Entry<RadioButton,CreadorAyudanteEvento> aux = null;
		
		while(!encontre && iterador.hasNext()) {
			aux = iterador.next();
			encontre = aux.getKey().getValue();
		}
		
		if(encontre)
			retorno = aux.getValue();
	
		return retorno;
	}

	private class oyenteAgregar implements ClickHandler {

		public void onClick(ClickEvent event) {			
			CreadorAyudanteEvento creador = obtenerTipoAyudante();
			if(creador!=null)
				Servicio.registrarAyudante(textBoxDNI.getText(),creador, new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
					}

					@Override
					public void onSuccess(String resultado) {
						if(resultado!=null)
							respuesta.setText("Usuario agregado con exito, su id es " + resultado);
						else
							respuesta.setText("Error: El usuario ya existe");
					}
				});
			else
				Window.alert("Seleccione un tipo de Ayudante");
		}
    }
}
