package com.jornadas.client.Ventanas.VentanasDeTareas;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.jornadas.client.ServicioAsync;
import com.jornadas.client.Ventanas.VentanasAbstractas.VentanaPanelVerticalYServicio;
import com.jornadas.shared.excepciones.FechaInvalidaException;
import com.jornadas.shared.excepciones.HoraInvalidaException;
import com.jornadas.shared.excepciones.HorariosEventoInvalidosException;
import com.jornadas.shared.extras.Fecha;
import com.jornadas.shared.extras.Hora;
import com.jornadas.shared.tarea.Tarea;

public class VentanaModificacionTarea extends VentanaPanelVerticalYServicio{

	protected HorizontalPanel panelTitulo, panelDescripcion, panelHorarioInicio, panelHorarioFin, panelLugar, panelFecha;
	protected Label etiquetaTitulo, etiquetaDescripcion, etiquetaHorarioInicio, etiquetaHorarioFin, etiquetaLugar, etiquetaFecha,etiquetaDosPuntosHI, etiquetaDosPuntosHF, etiquetaDia, etiquetaMes, etiquetaAnio;
	protected TextBox textBoxTitulo, textBoxHI, textBoxMI, textBoxHF, textBoxMF, textBoxLugar, textBoxDia, textBoxMes, textBoxAnio;
	protected TextArea textAreaDescripcion;
	protected Button botonModificar;
	
	protected String IDActual;
	
	protected ListBox listBoxTarea;
	protected Map<String,Tarea> Tareas;
	
	public VentanaModificacionTarea(ServicioAsync servicio) {
		super(servicio);
		
		Panel.getElement().getStyle().setBackgroundColor("#E7E6A3");
		
		Nombre = "Modificar Tarea";
		
		inicializarLabels();
		inicializarTextBoxs();
		inicializarPaneles();
		inicializarListBox();
		
		botonModificar = new Button("Modificar Tarea");
		botonModificar.addClickHandler(new oyenteModificar());
		
		poblarPanel();
	}
	
	protected void inicializarLabels() {
		etiquetaTitulo = new Label("Titulo:");
		etiquetaDescripcion = new Label("Descripcion:");
		etiquetaHorarioInicio = new Label("Horario Inicio:");
		etiquetaHorarioFin = new Label("Horario Finalizacion:");
		etiquetaLugar = new Label("Lugar:");
		etiquetaFecha = new Label("Fecha: ");
		etiquetaDia = new Label("Dia:");
		etiquetaMes = new Label("Mes:");
		etiquetaAnio = new Label("Anio:");
		etiquetaDosPuntosHI = new Label(":");
		etiquetaDosPuntosHF = new Label(":");
	}
	
	protected void inicializarTextBoxs() {
		textBoxTitulo = new TextBox();
		textAreaDescripcion = new TextArea();
		textAreaDescripcion.setCharacterWidth(40);
		textAreaDescripcion.setVisibleLines(5);
		textBoxHI = new TextBox();
		textBoxMI = new TextBox();
		textBoxHF = new TextBox();
		textBoxMF = new TextBox();
		textBoxLugar = new TextBox();
		textBoxDia = new TextBox();
		textBoxMes = new TextBox();
		textBoxAnio = new TextBox();
	}
	
	protected void inicializarPaneles() {
		panelTitulo = new HorizontalPanel();
		panelTitulo.add(etiquetaTitulo);
		panelTitulo.add(textBoxTitulo);
		
		panelDescripcion = new HorizontalPanel();
		panelDescripcion.add(etiquetaDescripcion);
		panelDescripcion.add(textAreaDescripcion);
		
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
		Panel.add(listBoxTarea);
		Panel.add(panelTitulo);
		Panel.add(panelDescripcion);
		Panel.add(panelHorarioInicio);
		Panel.add(panelHorarioFin);
		Panel.add(panelLugar);
		Panel.add(etiquetaFecha);
		Panel.add(panelFecha);
		Panel.add(botonModificar);
	}

	protected void inicializarListBox() {
		Tareas = new HashMap<String,Tarea>();
		listBoxTarea = new ListBox();
		listBoxTarea.addItem("Tareas");
		listBoxTarea.addChangeHandler(new cambioTarea());
		Servicio.obtenerTareas(new AsyncCallback<Collection<Tarea>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
			}

			@Override
			public void onSuccess(Collection<Tarea> resultado) {
				for(Tarea act : resultado) {
					Tareas.put(act.obtenerTitulo(), act);
					listBoxTarea.addItem(act.obtenerTitulo());
				}
			}
		});
	}
	
	protected Tarea modificarTarea() {
		Tarea tareaModificada = Tareas.get(listBoxTarea.getSelectedItemText());
		
		if(tareaModificada!=null) {
		
			tareaModificada.establecerTitulo(textBoxTitulo.getText());
			tareaModificada.establecerDescripcion(textAreaDescripcion.getText());
				
			tareaModificada.establecerLugar(textBoxLugar.getText());
		
			try {
				int dia = Integer.parseInt(textBoxDia.getText());
				int mes = Integer.parseInt(textBoxMes.getText());
				int anio = Integer.parseInt(textBoxAnio.getText());
				
				tareaModificada.establecerFecha(dia, mes, anio);
			} catch (NumberFormatException e) {
				Window.alert("Ingresar Numeros en la Fecha");
			} catch (FechaInvalidaException e) {
				Window.alert("Fecha de Invalida");
			}
		
			try {
				int horaInicio = Integer.parseInt(textBoxHI.getText());
				int minutoInicio = Integer.parseInt(textBoxMI.getText());
				tareaModificada.establecerHorarioInicio(horaInicio, minutoInicio);
			} catch (HorariosEventoInvalidosException e) {
				Window.alert("ERROR: El Inicio de la actividad debe ser despues del fin");
			} catch (HoraInvalidaException e) {
				Window.alert("ERROR: Hora de Inicio ingresada Invalida");
			}
		
			try {
				int horaFin = Integer.parseInt(textBoxHF.getText());
				int minutoFin = Integer.parseInt(textBoxMF.getText());
				tareaModificada.establecerHorarioFin(horaFin, minutoFin);
			} catch (HorariosEventoInvalidosException e) {
				Window.alert("ERROR: El Inicio de la actividad debe ser despues del fin");
			} catch (HoraInvalidaException e) {
				Window.alert("ERROR: Hora de Fin ingresada Invalida");
			}
		}
		return tareaModificada;
	}
	
	private class cambioTarea implements ChangeHandler {
		public void onChange(ChangeEvent event) {
			Tarea actividadElegida = Tareas.get(listBoxTarea.getSelectedItemText());
			if(actividadElegida!=null) {
				IDActual = actividadElegida.obtenerID();
				textBoxTitulo.setText(actividadElegida.obtenerTitulo());
				textAreaDescripcion.setText(actividadElegida.obtenerDescripcion());
				Hora HI = actividadElegida.obtenerHorarioInicio();
				Hora HF = actividadElegida.obtenerHorarioFin();
				textBoxHI.setText(Integer.toString(HI.obtenerHora()));
				textBoxMI.setText(Integer.toString(HI.obtenerMinutos()));
				textBoxHF.setText(Integer.toString(HF.obtenerHora()));
				textBoxMF.setText(Integer.toString(HF.obtenerMinutos()));
				textBoxLugar.setText(actividadElegida.obtenerLugar());
				Fecha fecha = actividadElegida.obtenerFecha();
				textBoxDia.setText(Integer.toString(fecha.obtenerDia()));
				textBoxMes.setText(Integer.toString(fecha.obtenerMes()));
				textBoxAnio.setText(Integer.toString(fecha.obtenerAnio()));
			}
		}
	}
	
	private class oyenteModificar implements ClickHandler {

		public void onClick(ClickEvent event) {	
			Tarea tareaModificada = modificarTarea();
			if(tareaModificada!=null) {
				Servicio.modificarTarea(tareaModificada, new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
					}

					@Override
					public void onSuccess(Boolean resultado) {
						if(resultado)
							Window.alert("Actividad modificada con exito");
						else
							Window.alert("Error al modificar la actividad");
					}
				});
			}
		}
    }
}
