package com.jornadas.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.jornadas.client.Servicio;
import com.jornadas.shared.excepciones.FechaInvalidaException;
import com.jornadas.shared.usuario.Asistente;
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
		
		if(jornada==null)
			jornada = new Jornada();
	}
	
	public Usuario obtenerUsuario(String ID, String DNI) {
		return jornada.recuperarUsuario(ID, DNI);
	}

	public Boolean registrarUsuario(String ID, String DNI) {
		if(jornada.recuperarUsuario(ID, DNI)==null) {
			jornada.agregarUsuario(new Asistente(ID, DNI));
			guardarJornada();
			return true;
		} else
			return false;
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
