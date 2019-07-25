package com.jornadas.client.Ventanas.VentanasDeConsultas;

import java.util.Collection;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TextArea;
import com.jornadas.client.ServicioAsync;
import com.jornadas.client.Ventanas.VentanasAbstractas.VentanaPanelVerticalYServicio;
import com.jornadas.shared.Visitor.Asistentes;
import com.jornadas.shared.usuario.Asistente;
import com.jornadas.shared.usuario.Usuario;

public class VentanaConsultaAsistentes extends VentanaPanelVerticalYServicio{

	protected TextArea textAreaAsistentes;
	
	public VentanaConsultaAsistentes(ServicioAsync servicio) {
		super(servicio);
		Panel.getElement().getStyle().setBackgroundColor("#E1E9b7");
		
		Nombre = "Asistentes Inscriptos";
		
		inicializarTextArea();
		
		Panel.add(textAreaAsistentes);
	}
	
	private void inicializarTextArea() {
		textAreaAsistentes = new TextArea();
		textAreaAsistentes.setReadOnly(true);
		textAreaAsistentes.setCharacterWidth(150);
		textAreaAsistentes.setVisibleLines(25);
		
		Servicio.obtenerUsuarios(new AsyncCallback<Collection<Usuario>>() {
			public void onFailure(Throwable caught) {
				Window.alert("Error al comunicarse con el servidor. Por favor vuelva a intentarlo");
			}

			public void onSuccess(Collection<Usuario> resultado) {
				Asistentes accionarAsistentes = new Asistentes();
				for(Usuario usuario : resultado) {
					usuario.accionar(accionarAsistentes);
				}
				
				Collection<Asistente> asistentes = accionarAsistentes.obtenerAsistentes();
				String Aux = "";
				
				for(Asistente asistente : asistentes) {
					Aux += asistente.obtenerID() + ": " + asistente.obtenerNombre() + " " + asistente.obtenerApellido() + ", " +
							asistente.obtenerDNI() + ", " + asistente.obtenerLU() + ", " +
							asistente.obtenerFechaDeNacimiento().obtenerDia() + "/" +
							asistente.obtenerFechaDeNacimiento().obtenerMes() + "/" + 
							asistente.obtenerFechaDeNacimiento().obtenerAnio() + ", " +
							asistente.obtenerUniversidad() + ", " + asistente.obtenerUniversidad() + ", " +
							asistente.obtenerTelefono() + ", " + asistente.obtenerMail() + "\r\n";
				}
				
				textAreaAsistentes.setText(Aux);
			}
		});
	}

}
