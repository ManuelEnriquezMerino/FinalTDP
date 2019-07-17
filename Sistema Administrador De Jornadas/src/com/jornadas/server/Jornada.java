package com.jornadas.server;

import java.util.HashMap;
import java.util.Map;

import com.jornadas.shared.usuario.Asistente;
import com.jornadas.shared.usuario.Usuario;

public class Jornada {

	private Map<String, Usuario> ColeccionDeUsuarios;
	
	public Jornada() {
		ColeccionDeUsuarios= new HashMap<String,Usuario>();
	}
	
	public void agregarUsuario(Usuario usuario) {
		String Clave = usuario.obtenerID() + usuario.obtenerDNI();
		ColeccionDeUsuarios.put(Clave, usuario);
	}
	
	public Usuario recuperarUsuario(String ID, String DNI) {
		String Clave = ID+DNI;
		return ColeccionDeUsuarios.get(Clave);
	}
	
	public static void main(String agr[]) {
		Jornada j = new Jornada();
		j.agregarUsuario(new Asistente("1111","1234"));
		System.out.println(j.recuperarUsuario("1111", "1234").obtenerDNI());
	}
}
