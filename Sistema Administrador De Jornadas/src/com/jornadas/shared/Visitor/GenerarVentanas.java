package com.jornadas.shared.Visitor;

import com.jornadas.client.ServicioAsync;
import com.jornadas.client.Ventanas.VentanaArea;
import com.jornadas.client.Ventanas.VentanaCreacionActividad;
import com.jornadas.client.Ventanas.VentanaCreacionAsistente;
import com.jornadas.client.Ventanas.VentanaCreacionAyudante;
import com.jornadas.client.Ventanas.VentanaCreacionTarea;
import com.jornadas.client.Ventanas.VentanaDatos;
import com.jornadas.client.Ventanas.VentanaInscripcionActividad;
import com.jornadas.client.Ventanas.VentanaInscripcionTarea;
import com.jornadas.client.Ventanas.VentanaPestanias;
import com.jornadas.shared.usuario.Asistente;
import com.jornadas.shared.usuario.CoordinadorDeArea;
import com.jornadas.shared.usuario.Organizador;
import com.jornadas.shared.usuario.OrganizadorGeneral;
import com.jornadas.shared.usuario.Voluntario;

public class GenerarVentanas extends Accion{

	private static final long serialVersionUID = 2989143729819179828L;
	protected VentanaPestanias Ventana;
	protected ServicioAsync Servicio;
	
	public GenerarVentanas(ServicioAsync servicio) {
		Ventana = new VentanaPestanias();
		Servicio = servicio;
	}

	public void Accionar(Asistente usuario) {
		Ventana.agregarPestania(new VentanaDatos(usuario, Servicio));
		Ventana.agregarPestania(new VentanaInscripcionActividad(usuario, Servicio));
	}


	public void Accionar(Voluntario usuario) {
		Ventana.agregarPestania(new VentanaDatos(usuario, Servicio));
		Ventana.agregarPestania(new VentanaInscripcionTarea(usuario, Servicio));
	}

	public void Accionar(Organizador usuario) {
		Ventana.agregarPestania(new VentanaDatos(usuario, Servicio));
		Ventana.agregarPestania(new VentanaCreacionAsistente(Servicio));
		Ventana.agregarPestania(new VentanaInscripcionTarea(usuario, Servicio));
	}

	public void Accionar(CoordinadorDeArea usuario) {
		Ventana.agregarPestania(new VentanaDatos(usuario, Servicio));
		Ventana.agregarPestania(new VentanaCreacionAsistente(Servicio));
		Ventana.agregarPestania(new VentanaCreacionActividad(Servicio));
		Ventana.agregarPestania(new VentanaCreacionTarea(Servicio));
		Ventana.agregarPestania(new VentanaInscripcionTarea(usuario, Servicio));
	}

	public void Accionar(OrganizadorGeneral usuario) {
		Ventana.agregarPestania(new VentanaDatos(usuario, Servicio));
		Ventana.agregarPestania(new VentanaCreacionAsistente(Servicio));
		Ventana.agregarPestania(new VentanaCreacionActividad(Servicio));
		Ventana.agregarPestania(new VentanaCreacionTarea(Servicio));
		Ventana.agregarPestania(new VentanaInscripcionTarea(usuario, Servicio));
		Ventana.agregarPestania(new VentanaCreacionAyudante(Servicio));
		Ventana.agregarPestania(new VentanaArea(Servicio));
	}
	
	public VentanaPestanias obtenerVentana() {
		return Ventana;
	}

}
