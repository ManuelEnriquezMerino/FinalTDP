package com.jornadas.shared.Visitor;

import com.jornadas.client.ServicioAsync;
import com.jornadas.client.Ventanas.VentanasDeActividades.VentanaActividadesDeAsistente;
import com.jornadas.client.Ventanas.VentanasDeActividades.VentanaCreacionActividad;
import com.jornadas.client.Ventanas.VentanasDeActividades.VentanaInscripcionActividad;
import com.jornadas.client.Ventanas.VentanasDeActividades.VentanaModificacionActividad;
import com.jornadas.client.Ventanas.VentanasDeAreas.VentanaAsignarAreaOrganizador;
import com.jornadas.client.Ventanas.VentanasDeAreas.VentanaCrearArea;
import com.jornadas.client.Ventanas.VentanasDeCreacionDeUsuarios.VentanaCreacionAsistente;
import com.jornadas.client.Ventanas.VentanasDeCreacionDeUsuarios.VentanaCreacionAyudante;
import com.jornadas.client.Ventanas.VentanasDeTareas.VentanaCreacionTarea;
import com.jornadas.client.Ventanas.VentanasDeTareas.VentanaInscripcionTarea;
import com.jornadas.client.Ventanas.VentanasDeTareas.VentanaTareasDeAyudante;
import com.jornadas.client.Ventanas.VentanasGenerales.VentanaDatos;
import com.jornadas.client.Ventanas.VentanasGenerales.VentanaPestanias;
import com.jornadas.client.Ventanas.VentanasGenerales.VentanaSistema;
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
		Ventana.agregarPestania(new VentanaCreacionActividad(usuario, Servicio));
		Ventana.agregarPestania(new VentanaModificacionActividad(Servicio));
		Ventana.agregarPestania(new VentanaCreacionTarea(Servicio));
		Ventana.agregarPestania(new VentanaInscripcionTarea(usuario, Servicio));
		Ventana.agregarPestania(new VentanaTareasDeAyudante(usuario, Servicio));
		Ventana.agregarPestania(new VentanaSistema(usuario, Servicio));
	}

	public void Accionar(OrganizadorGeneral usuario) {
		Ventana.agregarPestania(new VentanaDatos(usuario, Servicio));
		Ventana.agregarPestania(new VentanaCreacionAsistente(Servicio));
		Ventana.agregarPestania(new VentanaCreacionActividad(usuario, Servicio));
		Ventana.agregarPestania(new VentanaModificacionActividad(Servicio));
		Ventana.agregarPestania(new VentanaCreacionTarea(Servicio));
		Ventana.agregarPestania(new VentanaInscripcionTarea(usuario, Servicio));
		Ventana.agregarPestania(new VentanaTareasDeAyudante(usuario, Servicio));
		Ventana.agregarPestania(new VentanaCreacionAyudante(Servicio));
		Ventana.agregarPestania(new VentanaCrearArea(Servicio));
		Ventana.agregarPestania(new VentanaAsignarAreaOrganizador(Servicio));
		Ventana.agregarPestania(new VentanaSistema(usuario, Servicio));
	}
	
	public VentanaPestanias obtenerVentana() {
		return Ventana;
	}

}
