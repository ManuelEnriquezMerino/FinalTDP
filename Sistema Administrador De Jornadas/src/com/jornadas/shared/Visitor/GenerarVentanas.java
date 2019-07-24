package com.jornadas.shared.Visitor;

import com.jornadas.client.ServicioAsync;
import com.jornadas.client.Ventanas.VentanaCrearArea;
import com.jornadas.client.Ventanas.VentanaDatos;
import com.jornadas.client.Ventanas.VentanaPestanias;
import com.jornadas.client.Ventanas.VentanaSistema;
import com.jornadas.client.Ventanas.VentanasDeCreacionDeEventos.VentanaCreacionActividad;
import com.jornadas.client.Ventanas.VentanasDeCreacionDeEventos.VentanaCreacionTarea;
import com.jornadas.client.Ventanas.VentanasDeCreacionDeUsuarios.VentanaCreacionAsistente;
import com.jornadas.client.Ventanas.VentanasDeCreacionDeUsuarios.VentanaCreacionAyudante;
import com.jornadas.client.Ventanas.VentanasInscripcionEventos.VentanaInscripcionActividad;
import com.jornadas.client.Ventanas.VentanasInscripcionEventos.VentanaInscripcionTarea;
import com.jornadas.client.Ventanas.VentanasVerYDesinscribirseEvento.VentanaActividadesDeAsistente;
import com.jornadas.client.Ventanas.VentanasVerYDesinscribirseEvento.VentanaTareasDeAyudante;
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
		Ventana.agregarPestania(new VentanaActividadesDeAsistente(usuario, Servicio));
		Ventana.agregarPestania(new VentanaSistema(usuario, Servicio));
	}


	public void Accionar(Voluntario usuario) {
		Ventana.agregarPestania(new VentanaDatos(usuario, Servicio));
		Ventana.agregarPestania(new VentanaInscripcionTarea(usuario, Servicio));
		Ventana.agregarPestania(new VentanaTareasDeAyudante(usuario, Servicio));
		Ventana.agregarPestania(new VentanaSistema(usuario, Servicio));
	}

	public void Accionar(Organizador usuario) {
		Ventana.agregarPestania(new VentanaDatos(usuario, Servicio));
		Ventana.agregarPestania(new VentanaCreacionAsistente(Servicio));
		Ventana.agregarPestania(new VentanaInscripcionTarea(usuario, Servicio));
		Ventana.agregarPestania(new VentanaTareasDeAyudante(usuario, Servicio));
		Ventana.agregarPestania(new VentanaSistema(usuario, Servicio));
	}

	public void Accionar(CoordinadorDeArea usuario) {
		Ventana.agregarPestania(new VentanaDatos(usuario, Servicio));
		Ventana.agregarPestania(new VentanaCreacionAsistente(Servicio));
		Ventana.agregarPestania(new VentanaCreacionActividad(Servicio));
		Ventana.agregarPestania(new VentanaCreacionTarea(Servicio));
		Ventana.agregarPestania(new VentanaInscripcionTarea(usuario, Servicio));
		Ventana.agregarPestania(new VentanaTareasDeAyudante(usuario, Servicio));
		Ventana.agregarPestania(new VentanaSistema(usuario, Servicio));
	}

	public void Accionar(OrganizadorGeneral usuario) {
		Ventana.agregarPestania(new VentanaDatos(usuario, Servicio));
		Ventana.agregarPestania(new VentanaCreacionAsistente(Servicio));
		Ventana.agregarPestania(new VentanaCreacionActividad(Servicio));
		Ventana.agregarPestania(new VentanaCreacionTarea(Servicio));
		Ventana.agregarPestania(new VentanaInscripcionTarea(usuario, Servicio));
		Ventana.agregarPestania(new VentanaTareasDeAyudante(usuario, Servicio));
		Ventana.agregarPestania(new VentanaCreacionAyudante(Servicio));
		Ventana.agregarPestania(new VentanaCrearArea(Servicio));
		Ventana.agregarPestania(new VentanaSistema(usuario, Servicio));
	}
	
	public VentanaPestanias obtenerVentana() {
		return Ventana;
	}

}
