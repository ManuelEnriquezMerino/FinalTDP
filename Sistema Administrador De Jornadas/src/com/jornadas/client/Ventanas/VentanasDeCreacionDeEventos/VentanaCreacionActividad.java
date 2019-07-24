package com.jornadas.client.Ventanas.VentanasDeCreacionDeEventos;

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
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.jornadas.client.ServicioAsync;
import com.jornadas.client.Ventanas.VentanasAbstractas.VentanaPanelVerticalYServicio;
import com.jornadas.shared.actividad.Actividad;
import com.jornadas.shared.actividad.TipoActividad;
import com.jornadas.shared.excepciones.CupoInvalidoException;
import com.jornadas.shared.excepciones.FechaInvalidaException;
import com.jornadas.shared.excepciones.HoraInvalidaException;
import com.jornadas.shared.excepciones.HorariosEventoInvalidosException;
import com.jornadas.shared.usuario.CoordinadorDeArea;

public class VentanaCreacionActividad extends VentanaPanelVerticalYServicio{
	
	protected Actividad NuevaActividad;

	protected CoordinadorDeArea coordinador;
	protected HorizontalPanel panelTitulo, panelDescripcion, panelTipo, panelHorarioInicio, panelHorarioFin, panelLugar, panelFecha, panelCupo, panelRadio;
	protected Map<RadioButton,TipoActividad> tiposDeActividades;
	protected Label etiquetaTitulo, etiquetaDescripcion, etiquetaTipo, etiquetaHorarioInicio, etiquetaHorarioFin, etiquetaLugar, etiquetaFecha, etiquetaCupo,etiquetaDosPuntosHI, etiquetaDosPuntosHF, etiquetaDia, etiquetaMes, etiquetaAnio;
	protected TextBox textBoxTitulo, textBoxHI, textBoxMI, textBoxHF, textBoxMF, textBoxLugar, textBoxCupo, textBoxDia, textBoxMes, textBoxAnio;
	protected TextArea textAreaDescripcion;
	protected Button botonCrear;
	
	public VentanaCreacionActividad(ServicioAsync servicio) {
		super(servicio);
		
		Panel.getElement().getStyle().setBackgroundColor("#E7E6A3");
		
		Nombre = "Crear Actividad";
		
		inicializarLabels();
		inicializarTextBoxs();
		inicializarRadioButtons();
		inicializarPaneles();
		
		botonCrear = new Button("Crear Actividad");
		botonCrear.addClickHandler(new oyenteCrear());
		
		poblarPanel();
	}
	
	protected void inicializarLabels() {
		etiquetaTitulo = new Label("Titulo:");
		etiquetaDescripcion = new Label("Descripcion:");
		etiquetaTipo = new Label("Tipo de Actividad:");
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
	
	protected void inicializarRadioButtons() {
		tiposDeActividades = new HashMap<RadioButton,TipoActividad> ();
		panelRadio = new HorizontalPanel();
		
		Servicio.obtenerTiposDeActividades(new AsyncCallback<Collection<TipoActividad>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
			}

			@Override
			public void onSuccess(Collection<TipoActividad> resultado) {
				for(TipoActividad tipo : resultado){
					tiposDeActividades.put(new RadioButton("TiposDeActividades", tipo.obtenerTitulo()), tipo);
				}
				
				for(RadioButton boton : tiposDeActividades.keySet()) {
					panelRadio.add(boton);
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
		panelDescripcion.add(textAreaDescripcion);
		
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
		
		panelCupo = new HorizontalPanel();
		panelCupo.add(etiquetaCupo);
		panelCupo.add(textBoxCupo);
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
		Panel.add(panelCupo);
		Panel.add(botonCrear);
	}
	
	public Panel obtenerPanel() {
		return Panel;
	}

	protected boolean crearActividad() {
		NuevaActividad = new Actividad();
		boolean resultado = true;
		
		NuevaActividad.establecerTitulo(textBoxTitulo.getText());
		NuevaActividad.establecerDescripcion(textAreaDescripcion.getText());
		
		establecerTipoActividad();
		
		NuevaActividad.establecerLugar(textBoxLugar.getText());
		
		try {
			int dia = Integer.parseInt(textBoxDia.getText());
			int mes = Integer.parseInt(textBoxMes.getText());
			int anio = Integer.parseInt(textBoxAnio.getText());
			
			NuevaActividad.establecerFecha(dia, mes, anio);
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
			NuevaActividad.establecerHorarioInicio(horaInicio, minutoInicio);
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
			NuevaActividad.establecerHorarioFin(horaFin, minutoFin);
		} catch (HorariosEventoInvalidosException e) {
			Window.alert("ERROR: El Inicio de la actividad debe ser despues del fin");
			resultado=false;
		} catch (HoraInvalidaException e) {
			Window.alert("ERROR: Hora de Fin ingresada Invalida");
			resultado=false;
		}
		
		try {
			NuevaActividad.establecerCupo(Integer.parseInt(textBoxCupo.getText()));
		} catch (CupoInvalidoException e) {
			Window.alert("ERROR: El cupo debe ser mayor a 0");
			resultado=false;
		} catch (NumberFormatException e) {
			Window.alert("ERROR: El cupo debe ser un valor numerico.");
			resultado=false;
		}

		return resultado;
	}
	
	protected void establecerTipoActividad() {
		boolean encontre=false;
		Iterator<Entry<RadioButton,TipoActividad>> iterador = tiposDeActividades.entrySet().iterator();
		Entry<RadioButton,TipoActividad> aux=null;
		
		while(!encontre && iterador.hasNext()) {
			aux=iterador.next();
			if(aux.getKey().getValue()) {
				encontre=true;
			}
		}
		
		if(encontre)
			NuevaActividad.establecerTipoActividad(aux.getValue());
	}
	
	private class oyenteCrear implements ClickHandler {

		public void onClick(ClickEvent event) {	
			if(crearActividad()) {
				Servicio.agregarActividad(NuevaActividad, coordinador.obtenerArea().obtenerID(), new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
					}

					@Override
					public void onSuccess(String resultado) {
						Window.alert("Actividad creada con exito, su ID es: " + resultado);
					}
				});
			}
		}
    }
}
