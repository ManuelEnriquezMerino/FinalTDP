package com.jornadas.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.jornadas.client.Servicio;
import com.jornadas.shared.actividad.Actividad;
import com.jornadas.shared.actividad.Area;
import com.jornadas.shared.actividad.TipoActividad;
import com.jornadas.shared.excepciones.FechaInvalidaException;
import com.jornadas.shared.tarea.Tarea;
import com.jornadas.shared.tarea.creadoresDeTareas.CreadorTarea;
import com.jornadas.shared.usuario.Asistente;
import com.jornadas.shared.usuario.*;
import com.jornadas.shared.usuario.Usuario;
import com.jornadas.shared.usuario.creadoresDeOrganizadores.CreadorAyudanteEvento;

@SuppressWarnings("serial")
public class ServicioImpl extends RemoteServiceServlet implements Servicio {
	
	private Jornada jornada;
	
	public ServicioImpl() {
		ObjectInputStream ois = null;
		try {
			File tempFile = new File("DatosDeJornada");
			if(tempFile.exists()) {
				FileInputStream fin = new FileInputStream("DatosDeJornada");
		    	ois = new ObjectInputStream(fin);
		    	if(ois!=null) {
		    		jornada = (Jornada) ois.readObject();
		    		ois.close();
		    	}
		    	fin.close();
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		if(jornada==null) {
			jornada = new Jornada();
			TipoActividad tipo;
			for(int i=0; i<5 ; i++) {
				tipo = new TipoActividad();
				tipo.establecerDescripcion("Descripcion: "+i);
				tipo.establecerID("ID: "+i);
				tipo.establecerTitulo("Titulo: "+i);
				jornada.agregarTipoActividad(tipo);
			}
			Organizador o = new Organizador();
			o.establecerApellido("o");
			o.establecerCarrera("o");
			o.establecerDNI("o");
			try {
				o.establecerFechaDeNacimiento(1, 1, 1);
			} catch (FechaInvalidaException e) {
				e.printStackTrace();
			}
			o.establecerID("o");
			o.establecerLU("o");
			o.establecerMail("o");
			o.establecerNombre("o");
			o.establecerTelefono("o");
			o.establecerUniversidad("o");
			
			CoordinadorDeArea c = new CoordinadorDeArea();
			c.establecerApellido("c");
			c.establecerCarrera("c");
			c.establecerDNI("c");
			try {
				c.establecerFechaDeNacimiento(1, 1, 1);
			} catch (FechaInvalidaException e) {
				e.printStackTrace();
			}
			c.establecerID("c");
			c.establecerLU("c");
			c.establecerMail("c");
			c.establecerNombre("c");
			c.establecerTelefono("c");
			c.establecerUniversidad("c");
			
			Asistente a = new Asistente();
			a.establecerApellido("a");
			a.establecerCarrera("a");
			a.establecerDNI("a");
			try {
				a.establecerFechaDeNacimiento(1, 1, 1);
			} catch (FechaInvalidaException e) {
				e.printStackTrace();
			}
			a.establecerID("a");
			a.establecerLU("a");
			a.establecerMail("a");
			a.establecerNombre("a");
			a.establecerTelefono("a");
			a.establecerUniversidad("a");
			
			OrganizadorGeneral og = new OrganizadorGeneral();
			og.establecerApellido("og");
			og.establecerCarrera("og");
			og.establecerDNI("og");
			try {
				og.establecerFechaDeNacimiento(1, 1, 1);
			} catch (FechaInvalidaException e) {
				e.printStackTrace();
			}
			og.establecerID("og");
			og.establecerLU("og");
			og.establecerMail("og");
			og.establecerNombre("og");
			og.establecerTelefono("og");
			og.establecerUniversidad("og");
			
			Voluntario v = new Voluntario();
			v.establecerApellido("v");
			v.establecerCarrera("v");
			v.establecerDNI("v");
			try {
				v.establecerFechaDeNacimiento(1, 1, 1);
			} catch (FechaInvalidaException e) {
				e.printStackTrace();
			}
			v.establecerID("v");
			v.establecerLU("v");
			v.establecerMail("v");
			v.establecerNombre("v");
			v.establecerTelefono("v");
			v.establecerUniversidad("v");
			
			jornada.agregarUsuario(o);
			jornada.agregarUsuario(a);
			jornada.agregarUsuario(og);
			jornada.agregarUsuario(v);
			jornada.agregarUsuario(c);
		}
	}
	
	public Usuario obtenerUsuario(String ID, String DNI) {
		return jornada.recuperarUsuario(ID, DNI);
	}

	public String registrarAsistente(String DNI) {
		if(!jornada.existeUsuario(DNI)) {
			String ID = "A" + jornada.obtenerNuevoIDUsuario();
			jornada.agregarUsuario(new Asistente(ID, DNI));
			guardarJornada();
			return ID;
		} else
			return null;
	}
	
	public String registrarAyudante(String DNI, CreadorAyudanteEvento Creador) {
		if(!jornada.existeUsuario(DNI)) {
			String ID = Creador.obtenerPrefijoID() + jornada.obtenerNuevoIDUsuario();
			Voluntario nuevoAyudante = Creador.crearAyudante();
			nuevoAyudante.establecerDNI(DNI);
			nuevoAyudante.establecerID(ID);
			jornada.agregarUsuario(nuevoAyudante);
			guardarJornada();
			return ID;
		} else
			return null;
	}
	
	public Boolean actualizarUsuario(Usuario usuario) {
		Usuario usuarioServidor = jornada.recuperarUsuario(usuario.obtenerID(), usuario.obtenerDNI());
		
		if(usuarioServidor!=null) {
			usuarioServidor.establecerNombre(usuario.obtenerNombre());
			usuarioServidor.establecerApellido(usuario.obtenerApellido());
			usuarioServidor.establecerUniversidad(usuario.obtenerUniversidad());
			usuarioServidor.establecerCarrera(usuario.obtenerCarrera());
			usuarioServidor.establecerLU(usuario.obtenerLU());
			usuarioServidor.establecerTelefono(usuario.obtenerTelefono());
			usuarioServidor.establecerMail(usuario.obtenerMail());
			try {
				usuarioServidor.establecerFechaDeNacimiento(usuario.obtenerFechaDeNacimiento().obtenerDia(), usuario.obtenerFechaDeNacimiento().obtenerMes(), usuario.obtenerFechaDeNacimiento().obtenerAnio());
			} catch (FechaInvalidaException e) {
				e.printStackTrace();
				return false;
			}
			guardarJornada();
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean inscribirAsistenteAActividad(Asistente asistente, Actividad actividad) {
		boolean resultado;
		if(asistente != null && actividad != null) {
			resultado = asistente.agregarActividad(actividad) && actividad.agregarAsistente(asistente);
		} else
			resultado = false;
		return resultado;
	}
	
	public int obtenerIDNuevaArea() {
		return jornada.obtenerNuevoIDArea();
	}
	
	public Collection<Usuario> obtenerUsuarios() {
		return jornada.obtenerUsuarios();
	}
	
	public Collection<Actividad> obtenerActividades() {
		return jornada.obtenerActividades();
	}
	
	public Collection<TipoActividad> obtenerTiposDeActividades(){
		return jornada.obtenerTiposDeActividades();
	}
	
	public Collection<CreadorTarea> obtenerTiposDeTareas(){
		return jornada.obtenerTiposDeTareas();
	}
	
	public Collection<CreadorAyudanteEvento> obtenerTiposDeAyudantes(){
		return jornada.obtenerTiposDeAyudantes();
	}
	
	public Boolean agregarArea(Area NuevaArea) {
		Boolean resultado = jornada.agregarArea(NuevaArea);
		if(resultado)
			guardarJornada();
		return resultado;
	}
	
	public Boolean modificarArea(Area AreaModificada) {
		Boolean resultado = jornada.modificarArea(AreaModificada);
		if(resultado)
			guardarJornada();
		return resultado;
	}
	
	public String agregarActividad(Actividad NuevaActividad) {
		String ID = "Act_" + jornada.obtenerNuevoIDActividad();
		NuevaActividad.establecerID(ID);
		jornada.agregarActividad(NuevaActividad);
		guardarJornada();
		return ID;
	}
	
	public String agregarTarea(Tarea NuevaTarea) {
		String ID = "Tarea_" + jornada.obtenerNuevoIDTarea();
		NuevaTarea.establecerID(ID);
		jornada.agregarTarea(NuevaTarea);
		guardarJornada();
		return ID;
	}
	
	private void guardarJornada() {
		try {
			FileOutputStream fout = new FileOutputStream("DatosDeJornada");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(jornada);
			fout.close();
			oos.close();
		} catch (Exception e) {
		}
	}
}
