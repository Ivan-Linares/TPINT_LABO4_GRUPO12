package Entidad;

import entidad.Rol;
import entidad.TipoUsuario;

public class Usuario {
	
	private Cliente cliente;
	private String User;
	private String Pass;
	private Rol Rol;
	private boolean Estado;
	private TipoUsuario tipoUsuario;
	
	public Usuario() {
		
	}

	public Usuario(Cliente cliente, String user, String pass, entidad.Rol rol, boolean estado) {
		this.cliente = cliente;
		this.User = user;
		this.Pass = pass;
		this.Rol = rol;
		this.Estado = estado;
	}

	public Cliente getPersona() {
		return cliente;
	}

	public void setPersona(Cliente object) {
		this.cliente = object;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	public String getPass() {
		return Pass;
	}

	public void setPass(String pass) {
		Pass = pass;
	}

	public Rol getRol() {
		return Rol;
	}

	public void setRol(Rol rol) {
		Rol = rol;
	}

	public boolean getEstado() {
		return Estado;
	}

	public void setEstado(boolean estado) {
		Estado = estado;
	}

	@Override
	public String toString() {
		return "Usuario [Persona=" + Persona + ", User=" + User + ", Pass=" + Pass + ", Rol=" + Rol + ", Estado="
				+ Estado + "]";
	}



}
