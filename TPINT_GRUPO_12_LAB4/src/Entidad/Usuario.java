package Entidad;

import Entidad.TipoUsuario;

public class Usuario {
	
	private Cliente cliente;
	private String User;
	private String Pass;
	private boolean Estado;
	private TipoUsuario tipoUsuario;
	
	public Usuario() {
		
	}

	public Usuario(Cliente cliente, String user, String pass, TipoUsuario tipo, boolean estado) {
		this.cliente = cliente;
		this.User = user;
		this.Pass = pass;
		this.tipoUsuario=tipo;
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

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario rol) {
		tipoUsuario = rol;
	}

	public boolean getEstado() {
		return Estado;
	}

	public void setEstado(boolean estado) {
		Estado = estado;
	}






}
