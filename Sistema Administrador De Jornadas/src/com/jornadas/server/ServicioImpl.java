package com.jornadas.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Random;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.jornadas.client.Servicio;
import com.jornadas.shared.actividad.Actividad;
import com.jornadas.shared.actividad.Area;
import com.jornadas.shared.actividad.TipoActividad;
import com.jornadas.shared.excepciones.FechaInvalidaException;
import com.jornadas.shared.tarea.CreadorTarea;
import com.jornadas.shared.tarea.Tarea;
import com.jornadas.shared.usuario.Asistente;
import com.jornadas.shared.usuario.*;
import com.jornadas.shared.usuario.Usuario;

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
			o.establecerApellido("a");
			o.establecerCarrera("a");
			o.establecerDNI("a");
			try {
				o.establecerFechaDeNacimiento(1, 1, 1);
			} catch (FechaInvalidaException e) {
				e.printStackTrace();
			}
			o.establecerID("a");
			o.establecerLU("a");
			o.establecerMail("a");
			o.establecerNombre("a");
			o.establecerTelefono("a");
			o.establecerUniversidad("a");
			
			CoordinadorDeArea c = new CoordinadorDeArea();
			c.establecerApellido("b");
			c.establecerCarrera("b");
			c.establecerDNI("b");
			try {
				c.establecerFechaDeNacimiento(1, 1, 1);
			} catch (FechaInvalidaException e) {
				e.printStackTrace();
			}
			c.establecerID("b");
			c.establecerLU("b");
			c.establecerMail("b");
			c.establecerNombre("b");
			c.establecerTelefono("b");
			c.establecerUniversidad("b");
			
			Asistente a = new Asistente();
			a.establecerApellido("c");
			a.establecerCarrera("c");
			a.establecerDNI("c");
			try {
				a.establecerFechaDeNacimiento(1, 1, 1);
			} catch (FechaInvalidaException e) {
				e.printStackTrace();
			}
			a.establecerID("c");
			a.establecerLU("c");
			a.establecerMail("c");
			a.establecerNombre("c");
			a.establecerTelefono("c");
			a.establecerUniversidad("c");
			
			OrganizadorGeneral og = new OrganizadorGeneral();
			og.establecerApellido("d");
			og.establecerCarrera("d");
			og.establecerDNI("d");
			try {
				og.establecerFechaDeNacimiento(1, 1, 1);
			} catch (FechaInvalidaException e) {
				e.printStackTrace();
			}
			og.establecerID("d");
			og.establecerLU("d");
			og.establecerMail("d");
			og.establecerNombre("d");
			og.establecerTelefono("d");
			og.establecerUniversidad("d");
			
			Voluntario v = new Voluntario();
			v.establecerApellido("e");
			v.establecerCarrera("e");
			v.establecerDNI("e");
			try {
				v.establecerFechaDeNacimiento(1, 1, 1);
			} catch (FechaInvalidaException e) {
				e.printStackTrace();
			}
			v.establecerID("e");
			v.establecerLU("e");
			v.establecerMail("e");
			v.establecerNombre("e");
			v.establecerTelefono("e");
			v.establecerUniversidad("e");
			
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
		if(!jornada.existeAsistente(DNI)) {
			Random aleatorio = new Random(System.currentTimeMillis());
			String ID = "A" + aleatorio.nextInt(1000);
			jornada.agregarUsuario(new Asistente(ID, DNI));
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
	
	public int obtenerIDNuevaArea() {
		return jornada.obtenerNuevoIDArea();
	}
	
	public Collection<Usuario> obtenerUsuarios() {
		return jornada.obtenerUsuarios();
	}
	
	public Collection<TipoActividad> obtenerTiposDeActividades(){
		return jornada.obtenerTiposDeActividades();
	}
	
	public Collection<CreadorTarea> obtenerTiposDeTareas(){
		return jornada.obtenerTiposDeTareas();
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
