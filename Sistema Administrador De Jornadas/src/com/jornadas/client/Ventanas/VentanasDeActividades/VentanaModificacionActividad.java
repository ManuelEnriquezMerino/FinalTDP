package com.jornadas.client.Ventanas.VentanasDeActividades;

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
import com.jornadas.shared.actividad.Actividad;
import com.jornadas.shared.excepciones.CupoInvalidoException;
import com.jornadas.shared.excepciones.FechaInvalidaException;
import com.jornadas.shared.excepciones.HoraInvalidaException;
import com.jornadas.shared.excepciones.HorariosEventoInvalidosException;
import com.jornadas.shared.extras.Fecha;
import com.jornadas.shared.extras.Hora;

public class VentanaModificacionActividad extends VentanaPanelVerticalYServicio{

	protected Actividad ActividadModificada;
	
	protected HorizontalPanel panelTitulo, panelDescripcion, panelHorarioInicio, panelHorarioFin, panelLugar, panelFecha, panelCupo;
	protected Label etiquetaTitulo, etiquetaDescripcion, etiquetaHorarioInicio, etiquetaHorarioFin, etiquetaLugar, etiquetaFecha, etiquetaCupo,etiquetaDosPuntosHI, etiquetaDosPuntosHF, etiquetaDia, etiquetaMes, etiquetaAnio;
	protected TextBox textBoxTitulo, textBoxHI, textBoxMI, textBoxHF, textBoxMF, textBoxLugar, textBoxCupo, textBoxDia, textBoxMes, textBoxAnio;
	protected TextArea textAreaDescripcion;
	protected Button botonModificar;
	
	protected String IDActual;
	
	protected ListBox listBoxActividades;
	protected Map<String,Actividad> Actividades;
	
	public VentanaModificacionActividad(ServicioAsync servicio) {
		super(servicio);
		
		Panel.getElement().getStyle().setBackgroundColor("#ccffff");
		
		Nombre = "Modificar Actividad";
		
		inicializarLabels();
		inicializarTextBoxs();
		inicializarPaneles();
		inicializarListBox();
		
		botonModificar = new Button("Modificar Actividad");
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
		etiquetaCupo = new Label("Cupo:");
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
		textBoxCupo = new TextBox();
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
		
		panelCupo = new HorizontalPanel();
		panelCupo.add(etiquetaCupo);
		panelCupo.add(textBoxCupo);
	}
	
	protected void poblarPanel() {
		Panel.add(listBoxActividades);
		Panel.add(panelTitulo);
		Panel.add(panelDescripcion);
		Panel.add(panelHorarioInicio);
		Panel.add(panelHorarioFin);
		Panel.add(panelLugar);
		Panel.add(etiquetaFecha);
		Panel.add(panelFecha);
		Panel.add(panelCupo);
		Panel.add(botonModificar);
	}

	protected void inicializarListBox() {
		Actividades = new HashMap<String,Actividad>();
		listBoxActividades = new ListBox();
		listBoxActividades.addItem("Actividades");
		listBoxActividades.addChangeHandler(new cambioActividad());
		Servicio.obtenerActividades(new AsyncCallback<Collection<Actividad>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
			}

			@Override
			public void onSuccess(Collection<Actividad> resultado) {
				for(Actividad act : resultado) {
					Actividades.put(act.obtenerTitulo(), act);
					listBoxActividades.addItem(act.obtenerTitulo());
				}
			}
		});
	}
	
	protected boolean crearActividad() {
		ActividadModificada = new Actividad();
		boolean resultado = true;
		
		ActividadModificada.establecerID(IDActual);
		
		ActividadModificada.establecerTitulo(textBoxTitulo.getText());
		ActividadModificada.establecerDescripcion(textAreaDescripcion.getText());
				
		ActividadModificada.establecerLugar(textBoxLugar.getText());
		
		try {
			int dia = Integer.parseInt(textBoxDia.getText());
			int mes = Integer.parseInt(textBoxMes.getText());
			int anio = Integer.parseInt(textBoxAnio.getText());
			
			ActividadModificada.establecerFecha(dia, mes, anio);
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
			ActividadModificada.establecerHorarioInicio(horaInicio, minutoInicio);
		} catch (HorariosEventoInvalidosException e) {
			Window.alert("ERROR: El Inicio de la actividad debe ser despues del fin");
			resultado=false;
		} catch (HoraInvalidaException e) {
			Window.alert("ERROR: Hora de Inicio ingresada Invalida");
			resultado=false;
		}
		
		try {
			int horaFin = Integer.parseInt(textBoxHF.getText());
			int minutoFin = Integer.parseInt(textBoxMF.getText());
			ActividadModificada.establecerHorarioFin(horaFin, minutoFin);
		} catch (HorariosEventoInvalidosException e) {
			Window.alert("ERROR: El Inicio de la actividad debe ser despues del fin");
			resultado=false;
		} catch (HoraInvalidaException e) {
			Window.alert("ERROR: Hora de Fin ingresada Invalida");
			resultado=false;
		}
		
		try {
			ActividadModificada.establecerCupo(Integer.parseInt(textBoxCupo.getText()));
		} catch (CupoInvalidoException e) {
			Window.alert("ERROR: El cupo debe ser mayor a 0");
			resultado=false;
		} catch (NumberFormatException e) {
			Window.alert("ERROR: El cupo debe ser un valor numerico.");
			resultado=false;
		}

		return resultado;
	}
	
	private class cambioActividad implements ChangeHandler {
		public void onChange(ChangeEvent event) {
			Actividad actividadElegida = Actividades.get(listBoxActividades.getSelectedItemText());
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
				textBoxCupo.setText(Integer.toString(actividadElegida.obtenerCupo()));
				Fecha fecha = actividadElegida.obtenerFecha();
				textBoxDia.setText(Integer.toString(fecha.obtenerDia()));
				textBoxMes.setText(Integer.toString(fecha.obtenerMes()));
				textBoxAnio.setText(Integer.toString(fecha.obtenerAnio()));
			}
		}
	}
	
	private class oyenteModificar implements ClickHandler {

		public void onClick(ClickEvent event) {	
			if(crearActividad()) {
				Servicio.modificarActividad(ActividadModificada, new AsyncCallback<Boolean>() {

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
