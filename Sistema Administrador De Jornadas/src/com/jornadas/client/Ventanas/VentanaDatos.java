package com.jornadas.client.Ventanas;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.jornadas.client.ServicioAsync;
import com.jornadas.shared.excepciones.FechaInvalidaException;
import com.jornadas.shared.usuario.Usuario;

public class VentanaDatos{
	
	protected FlowPanel Panel;
	
	protected Usuario Usuario;
	protected ServicioAsync Servicio;
	
	private Label etiquetaDNI, etiquetaID, etiquetaNombre, etiquetaApellido, etiquetaFechaNacimiento, etiquetaLU, etiquetaUniversidad, etiquetaCarrera, etiquetaTelefono, etiquetaMail, etiquetaDia, etiquetaMes, etiquetaAnio;
	private Label labelDNI, labelID;
	private TextBox textBoxNombre, textBoxApellido, textBoxUniversidad, textBoxCarrera, textBoxTelefono, textBoxMail, textBoxDia, textBoxMes, textBoxAnio, textBoxLU;
	private Button botonGuardar;

	public VentanaDatos(Usuario usuario, ServicioAsync servicio) {
		Usuario = usuario;
		Servicio = servicio;
		
		Panel = new FlowPanel();
		Panel.setSize("100%", "100%"); 
		Panel.getElement().getStyle().setBackgroundColor("#c0e7f6");
				
		inicializarEtiquetas();
		inicializarLabels();
		inicializarTextBoxs();
		
		botonGuardar = new Button("Guardar");
		botonGuardar.addClickHandler(new oyenteGuardarDatos());
		
		poblarPanel();
	}
	
	public Panel obtenerPanel() {
		return Panel;
	}
	
	private void inicializarEtiquetas() {
		etiquetaDNI = new Label("DNI:");
		etiquetaID = new Label("ID:");
		etiquetaNombre = new Label("Nombre:");
		etiquetaApellido = new Label("Apellido:");
		etiquetaFechaNacimiento = new Label("Fecha de Nacimiento");
		etiquetaLU = new Label("LU:");
		etiquetaUniversidad = new Label("Universidad:");
		etiquetaCarrera = new Label("Carrera:");
		etiquetaDia = new Label("Dia:");
		etiquetaMes = new Label("Mes:");
		etiquetaAnio = new Label("Año");
		etiquetaTelefono = new Label("Telefono");
		etiquetaMail = new Label("Mail");
	}
	
	private void inicializarLabels() {
		labelDNI = new Label(Usuario.obtenerDNI());
		labelID = new Label(Usuario.obtenerID());
	}
	
	private void inicializarTextBoxs() {
		textBoxNombre = new TextBox();
		textBoxApellido = new TextBox();
		textBoxUniversidad = new TextBox();
		textBoxCarrera = new TextBox();
		textBoxTelefono = new TextBox();
		textBoxMail = new TextBox();
		textBoxDia = new TextBox();
		textBoxMes = new TextBox();
		textBoxAnio = new TextBox();
		textBoxLU = new TextBox();
		
		textBoxNombre.setText(Usuario.obtenerNombre());
		textBoxApellido.setText(Usuario.obtenerApellido());
		textBoxUniversidad.setText(Usuario.obtenerUniversidad());
		textBoxCarrera.setText(Usuario.obtenerCarrera());
		textBoxTelefono.setText(Usuario.obtenerTelefono());
		textBoxMail.setText(Usuario.obtenerMail());
		textBoxLU.setText(Usuario.obtenerLU());
		
		String diaNacimiento = new Integer(Usuario.obtenerFechaDeNacimiento().obtenerDia()).toString();
		String mesNacimiento = new Integer(Usuario.obtenerFechaDeNacimiento().obtenerMes()).toString();
		String anioNacimiento = new Integer(Usuario.obtenerFechaDeNacimiento().obtenerAnio()).toString();
		
		textBoxDia.setText(diaNacimiento);
		textBoxMes.setText(mesNacimiento);
		textBoxAnio.setText(anioNacimiento);
	}
	
	private void poblarPanel() {
		//DNI
		Panel.add(etiquetaDNI);
		Panel.add(labelDNI);
		
		//ID
		Panel.add(etiquetaID);
		Panel.add(labelID);
		
		//Nombre
		Panel.add(etiquetaNombre);
		Panel.add(textBoxNombre);
		
		//Apellido
		Panel.add(etiquetaApellido);
		Panel.add(textBoxApellido);
		
		//Fecha de Nacimiento
		Panel.add(etiquetaFechaNacimiento);
		Panel.add(etiquetaDia);
		Panel.add(textBoxDia);
		Panel.add(etiquetaMes);
		Panel.add(textBoxMes);
		Panel.add(etiquetaAnio);
		Panel.add(textBoxAnio);
		
		//Universidad
		Panel.add(etiquetaUniversidad);
		Panel.add(textBoxUniversidad);
		
		//Carrera
		Panel.add(etiquetaCarrera);
		Panel.add(textBoxCarrera);
		
		//LU
		Panel.add(etiquetaLU);
		Panel.add(textBoxLU);
		
		//Telefono
		Panel.add(etiquetaTelefono);
		Panel.add(textBoxTelefono);
		
		//Mail
		Panel.add(etiquetaMail);
		Panel.add(textBoxMail);
		
		Panel.add(botonGuardar);
	}
	
	private void modificarUsuario() {
		Usuario.establecerNombre(textBoxNombre.getText());
		Usuario.establecerApellido(textBoxApellido.getText());
		Usuario.establecerUniversidad(textBoxUniversidad.getText());
		Usuario.establecerCarrera(textBoxCarrera.getText());
		Usuario.establecerLU(textBoxLU.getText());
		Usuario.establecerTelefono(textBoxTelefono.getText());
		Usuario.establecerMail(textBoxMail.getText());
		
		try {
			int dia = Integer.parseInt(textBoxDia.getText());
			int mes = Integer.parseInt(textBoxMes.getText());
			int anio = Integer.parseInt(textBoxAnio.getText());
			
			Usuario.establecerFechaDeNacimiento(dia, mes, anio);
		} catch (NumberFormatException e) {
			Window.alert("Ingresar Numeros en la Fecha de Nacimiento");
		} catch (FechaInvalidaException e) {
			Window.alert("Fecha de Nacimiento Invalida");
		}
	}

	private class oyenteGuardarDatos implements ClickHandler {

		public void onClick(ClickEvent event) {
			modificarUsuario();

			Servicio.actualizarUsuario(Usuario, new AsyncCallback<Boolean>() {

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
				}

				@Override
				public void onSuccess(Boolean resultado) {
					if(resultado)
						Window.alert("Datos Guardados Correctamente");
					else
						Window.alert("Los datos no fueron guardados");
				}
			});
		}
    }


}
