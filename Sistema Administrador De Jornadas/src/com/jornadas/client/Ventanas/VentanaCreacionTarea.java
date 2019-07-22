package com.jornadas.client.Ventanas;

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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.jornadas.client.ServicioAsync;
import com.jornadas.shared.excepciones.FechaInvalidaException;
import com.jornadas.shared.excepciones.HoraInvalidaException;
import com.jornadas.shared.excepciones.HorariosEventoInvalidosException;
import com.jornadas.shared.tarea.CreadorTarea;
import com.jornadas.shared.tarea.Tarea;

public class VentanaCreacionTarea extends VentanaPanelVerticalYServicio{
	
	protected Tarea NuevaTarea;

	protected HorizontalPanel panelTitulo, panelDescripcion, panelTipo, panelHorarioInicio, panelHorarioFin, panelLugar, panelFecha, panelRadio;
	protected Map<RadioButton,Tarea> tiposDeTareas;
	protected Label etiquetaTitulo, etiquetaDescripcion, etiquetaTipo, etiquetaHorarioInicio, etiquetaHorarioFin, etiquetaLugar, etiquetaFecha, etiquetaDosPuntosHI, etiquetaDosPuntosHF, etiquetaDia, etiquetaMes, etiquetaAnio;
	protected TextBox textBoxTitulo, textBoxDescripcion, textBoxHI, textBoxMI, textBoxHF, textBoxMF, textBoxLugar, textBoxDia, textBoxMes, textBoxAnio;
	protected Button botonCrear;
	
	public VentanaCreacionTarea(ServicioAsync servicio) {
		super(servicio);
		
		Panel.getElement().getStyle().setBackgroundColor("#711c91");
		
		Nombre = "Crear Tarea";
		
		inicializarLabels();
		inicializarTextBoxs();
		inicializarRadioButtons();
		inicializarPaneles();
		
		botonCrear = new Button("Crear Tarea");
		botonCrear.addClickHandler(new oyenteCrear());
		
		poblarPanel();
	}
	
	protected void inicializarLabels() {
		etiquetaTitulo = new Label("Titulo:");
		etiquetaDescripcion = new Label("Descripcion:");
		etiquetaTipo = new Label("Tipo de Tarea:");
		etiquetaHorarioInicio = new Label("Horario Inicio:");
		etiquetaHorarioFin = new Label("Horario Finalizacion:");
		etiquetaLugar = new Label("Lugar:");
		etiquetaFecha = new Label("Fecha: ");
		etiquetaDia = new Label("Dia:");
		etiquetaMes = new Label("Mes:");
		etiquetaAnio = new Label("Anio:");
		etiquetaDosPuntosHI = new Label(":");
		etiquetaDosPuntosHF = new Label(":");
		
		etiquetaTitulo.getElement().getStyle().setColor("#fffff0");
		etiquetaDescripcion.getElement().getStyle().setColor("#fffff0");
		etiquetaTipo.getElement().getStyle().setColor("#fffff0");
		etiquetaHorarioInicio.getElement().getStyle().setColor("#fffff0");
		etiquetaHorarioFin.getElement().getStyle().setColor("#fffff0");
		etiquetaLugar.getElement().getStyle().setColor("#fffff0");
		etiquetaFecha.getElement().getStyle().setColor("#fffff0");
		etiquetaDia.getElement().getStyle().setColor("#fffff0");
		etiquetaMes.getElement().getStyle().setColor("#fffff0");
		etiquetaAnio.getElement().getStyle().setColor("#fffff0");
		etiquetaDosPuntosHI.getElement().getStyle().setColor("#fffff0");
		etiquetaDosPuntosHF.getElement().getStyle().setColor("#fffff0");
	}
	
	protected void inicializarTextBoxs() {
		textBoxTitulo = new TextBox();
		textBoxDescripcion = new TextBox();
		textBoxHI = new TextBox();
		textBoxMI = new TextBox();
		textBoxHF = new TextBox();
		textBoxMF = new TextBox();
		textBoxLugar = new TextBox();
		textBoxDia = new TextBox();
		textBoxMes = new TextBox();
		textBoxAnio = new TextBox();
	}
	
	protected void inicializarRadioButtons() {
		tiposDeTareas = new HashMap<RadioButton,Tarea> ();
		panelRadio = new HorizontalPanel();
		
		Servicio.obtenerTiposDeTareas(new AsyncCallback<Collection<CreadorTarea>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
			}

			@Override
			public void onSuccess(Collection<CreadorTarea> resultado) {
				RadioButton boton;
				for(CreadorTarea creador : resultado) {
					boton = new RadioButton("TiposDeTareas", creador.obtenerNombre());
					boton.getElement().getStyle().setColor("#fffff0");
					panelRadio.add(boton);
					tiposDeTareas.put(boton, creador.obtenerTarea());
				}
			}
		});
	}
	
	protected void inicializarPaneles() {
		panelTitulo = new HorizontalPanel();
		panelTitulo.add(etiquetaTitulo);
		panelTitulo.add(textBoxTitulo);
		
		panelDescripcion = new HorizontalPanel();
		panelDescripcion.add(etiquetaDescripcion);
		panelDescripcion.add(textBoxDescripcion);
		
		panelTipo = new HorizontalPanel();
		panelTipo.add(etiquetaTipo);
		panelTipo.add(panelRadio);
		
		panelHorarioInicio = new HorizontalPanel();
		panelHorarioInicio.add(etiquetaHorarioInicio);
		panelHorarioInicio.add(textBoxHI);
		panelHorarioInicio.add(etiquetaDosPuntosHI);
		panelHorarioInicio.add(textBoxMI);
		
		panelHorarioFin = new HorizontalPanel();
		panelHorarioFin.add(etiquetaHorarioFin);
		panelHorarioFin.add(textBoxHF);
		panelHorarioFin.add(etiquetaDosPuntosHF);
		panelHorarioFin.add(textBoxMF);
		
		panelLugar = new HorizontalPanel();
		panelLugar.add(etiquetaLugar);
		panelLugar.add(textBoxLugar);
		
		panelFecha = new HorizontalPanel();
		panelFecha.add(etiquetaDia);
		panelFecha.add(textBoxDia);
		panelFecha.add(etiquetaMes);
		panelFecha.add(textBoxMes);
		panelFecha.add(etiquetaAnio);
		panelFecha.add(textBoxAnio);
	}
	
	protected void poblarPanel() {
		Panel.add(panelTitulo);
		Panel.add(panelDescripcion);
		Panel.add(panelTipo);
		Panel.add(panelHorarioInicio);
		Panel.add(panelHorarioFin);
		Panel.add(panelLugar);
		Panel.add(etiquetaFecha);
		Panel.add(panelFecha);
		Panel.add(botonCrear);
	}

	protected boolean crearTarea() {
		boolean resultado = establecerTipoTarea();
		
		if(resultado) {
			NuevaTarea.establecerTitulo(textBoxTitulo.getText());
			NuevaTarea.establecerDescripcion(textBoxDescripcion.getText());
		
			NuevaTarea.establecerLugar(textBoxLugar.getText());
		
			try {
				int dia = Integer.parseInt(textBoxDia.getText());
				int mes = Integer.parseInt(textBoxMes.getText());
				int anio = Integer.parseInt(textBoxAnio.getText());
			
				NuevaTarea.establecerFecha(dia, mes, anio);
			} catch (NumberFormatException e) {
				Window.alert("Ingresar Numeros en la Fecha");
				resultado=false;
			} catch (FechaInvalidaException e) {
				Window.alert("Fecha de Invalida");
				resultado=false;
			}
		
			try {
				int horaInicio = Integer.parseInt(textBoxHI.getText());
				int minutoInicio = Integer.parseInt(textBoxMI.getText());
				NuevaTarea.establecerHorarioInicio(horaInicio, minutoInicio);
			} catch (HorariosEventoInvalidosException e) {
				Window.alert("ERROR: El Inicio de la tarea debe ser despues del fin");
				resultado=false;
			} catch (HoraInvalidaException e) {
				Window.alert("ERROR: Hora de Inicio ingresada Invalida");
				resultado=false;
			}
		
			try {
				int horaFin = Integer.parseInt(textBoxHF.getText());
				int minutoFin = Integer.parseInt(textBoxMF.getText());
				NuevaTarea.establecerHorarioFin(horaFin, minutoFin);
			} catch (HorariosEventoInvalidosException e) {
				Window.alert("ERROR: El Inicio de la tarea debe ser despues del fin");
				resultado=false;
			} catch (HoraInvalidaException e) {
				Window.alert("ERROR: Hora de Fin ingresada Invalida");
				resultado=false;
			}
		}
		return resultado;
	}
	
	protected boolean establecerTipoTarea() {
		boolean encontre=false;
		Iterator<Entry<RadioButton,Tarea>> iterador = tiposDeTareas.entrySet().iterator();
		Entry<RadioButton,Tarea> aux=null;
		
		while(!encontre && iterador.hasNext()) {
			aux=iterador.next();
			if(aux.getKey().getValue()) {
				encontre=true;
			}
		}
		
		if(encontre)
			NuevaTarea = aux.getValue();
		
		return encontre;
	}
	
	private class oyenteCrear implements ClickHandler {

		public void onClick(ClickEvent event) {	
			if(crearTarea()) {
				Servicio.agregarTarea(NuevaTarea, new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
					}

					@Override
					public void onSuccess(String resultado) {
						Window.alert("Tarea creada con exito, su ID es: " + resultado);
					}
				});
			}
		}
    }
}
