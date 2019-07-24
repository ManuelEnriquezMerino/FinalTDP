package com.jornadas.client.Ventanas.VentanasDeAreas;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.jornadas.client.ServicioAsync;
import com.jornadas.client.Ventanas.VentanasAbstractas.VentanaPanelVerticalYServicio;
import com.jornadas.shared.Visitor.CoordinadoresDeArea;
import com.jornadas.shared.actividad.Area;
import com.jornadas.shared.actividad.TipoActividad;
import com.jornadas.shared.usuario.CoordinadorDeArea;
import com.jornadas.shared.usuario.Usuario;

public class VentanaCrearArea extends VentanaPanelVerticalYServicio{
	
	protected HorizontalPanel PanelCheckBox, PanelID, PanelNombre, PanelCoordinador, PanelActividades;
	
	protected Map<String,CoordinadorDeArea> MapeoDeCoordinadores;
	
	private Label etiquetaID, etiquetaNombre, etiquetaCoordinador, etiquetaTiposDeActividades;
	private Label labelID;
	private TextBox textBoxNombre;
	private ListBox listBoxCoordinadores;
	private Map<TipoActividad,CheckBox> checkBoxTipoDeActividades;
	private Button botonGuardar;
	
	public VentanaCrearArea(ServicioAsync servicio) {
		super(servicio);
		
		Nombre = "Crear Area";
		
		Panel.getElement().getStyle().setBackgroundColor("#d6e2d5");
		
		cargarVentana();
	}
	
	protected void cargarVentana() {
		inicializarEtiquetas();
		inicializarLabel();
		inicializarListBox();
		inicializarTextBox();
		inicializarCheckBox();
		inicializarPaneles();
		
		botonGuardar = new Button("Guardar");
		
		botonGuardar.addClickHandler(new oyenteGuardarDatos());
		
		poblarPanel();
	}

	protected void inicializarEtiquetas() {
		etiquetaID = new Label("ID:");
		etiquetaNombre = new Label("Nombre:");
		etiquetaCoordinador = new Label("Coordinador:");
		etiquetaTiposDeActividades = new Label("Tipos de Actividades:");
	}
	
	protected void inicializarLabel() {
		labelID = new Label();
		
		Servicio.obtenerIDNuevaArea(new AsyncCallback<Integer>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
			}

			@Override
			public void onSuccess(Integer resultado) {
				String nuevoID = "Area_" + resultado;
				labelID.setText(nuevoID);
			}
		});
	}
	
	protected void inicializarListBox() {
		listBoxCoordinadores = new ListBox();
		final CoordinadoresDeArea coordinadores = new CoordinadoresDeArea();
		
		Servicio.obtenerUsuarios(new AsyncCallback<Collection<Usuario>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
			}

			@Override
			public void onSuccess(Collection<Usuario> resultado) {
				for(Usuario usuario : resultado) {
					usuario.accionar(coordinadores);
				}
				
				MapeoDeCoordinadores = coordinadores.obtenerColeccionCoordinadores();
				
				for(Entry<String, CoordinadorDeArea> Coordinador : MapeoDeCoordinadores.entrySet()) {
					if(!Coordinador.getValue().tieneArea())
						listBoxCoordinadores.addItem(Coordinador.getKey());
				}
			}
		});
	}
	
	protected void inicializarCheckBox() {
		checkBoxTipoDeActividades = new HashMap<TipoActividad,CheckBox>();
		PanelCheckBox = new HorizontalPanel();
		
		Servicio.obtenerTiposDeActividades(new AsyncCallback<Collection<TipoActividad>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
			}

			@Override
			public void onSuccess(Collection<TipoActividad> resultado) {
				for(TipoActividad tipo : resultado){
					checkBoxTipoDeActividades.put(tipo ,new CheckBox(tipo.obtenerTitulo()));
				}
				
				for(CheckBox checkBox : checkBoxTipoDeActividades.values()) {
					PanelCheckBox.add(checkBox);
				}
			}
		});
		
		
	}
	
	protected void inicializarTextBox() {
		textBoxNombre = new TextBox();
	}
	
	protected void inicializarPaneles() {
		PanelID = new HorizontalPanel();
		PanelID.add(etiquetaID);
		PanelID.add(labelID);
		
		PanelNombre = new HorizontalPanel();
		PanelNombre.add(etiquetaNombre);
		PanelNombre.add(textBoxNombre);
		
		PanelCoordinador = new HorizontalPanel();
		PanelCoordinador.add(etiquetaCoordinador);
		PanelCoordinador.add(listBoxCoordinadores);
		
		PanelActividades = new HorizontalPanel();
		PanelActividades.add(etiquetaTiposDeActividades);
		PanelActividades.add(PanelCheckBox);
	}
	
	protected void poblarPanel() {
		Panel.add(PanelID);
		
		Panel.add(PanelNombre);
		
		Panel.add(PanelCoordinador);
		
		Panel.add(PanelActividades);
		
		Panel.add(botonGuardar);
	}

	protected Area crearArea() {
		Area area = new Area();
		
		CoordinadorDeArea coordinador = MapeoDeCoordinadores.get(listBoxCoordinadores.getSelectedItemText());
		
		if(area.establecerCoordinador(coordinador)) {
			area.establecerID(labelID.getText());
			
			area.establecerNombre(textBoxNombre.getText());
			
			for(Entry<TipoActividad,CheckBox> entrada : checkBoxTipoDeActividades.entrySet()) {
				if(entrada.getValue().getValue()) {
					area.agregarTipoDeActividad(entrada.getKey());
				}
			}
			return area;
		} else {
			//Si no se puede establecer el nuevo coordinador
			Window.alert("ERROR: el coordinador ya tiene un area");
			return null;
		}
	}
	
	protected void limpiarPanel() {
		while(Panel.getWidgetCount()>0) {
			Panel.remove(0);
		}
	}
	
	protected class oyenteGuardarDatos implements ClickHandler {

		public void onClick(ClickEvent event) {
			Area Area = crearArea();
			if(Area!=null)
				Servicio.agregarArea(Area, new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
					}

					@Override
					public void onSuccess(Boolean resultado) {
						if(resultado)
							Window.alert("Area agregada correctamente");
						else
							Window.alert("Error al agregar el area");
						limpiarPanel();
						cargarVentana();
					}
				});
		}
	}
}
