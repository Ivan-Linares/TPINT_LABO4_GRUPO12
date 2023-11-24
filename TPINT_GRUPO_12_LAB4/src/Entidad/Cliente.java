package Entidad;

import java.time.LocalDate;

public class Cliente {
	
	private String dni;
	private String cuil;
	private String apellido;
	private String nombre;
	private String sexo;
	private String direccion;
	//private Fecha fechaNac;
	private LocalDate fechaNac;
	private String telefono;
	private Localidad localidad;
	private String email;
	private String usuario;
	private String pass;
	private String estado;
	
	public Cliente() {
		
	}
	
	public Cliente(String d, String c, String a, String n, String s, LocalDate fechanac, String di, Localidad l, String mail, String u, String e, String ps, String tel) {
		
		this.dni=d;
		this.cuil=c;
		this.apellido=a;
		this.nombre=n;
		this.sexo=s;
		this.fechaNac=fechanac;
		this.telefono=tel;
		this.localidad=l;
		this.email=mail;
		this.usuario=u;
		this.pass=ps;
		this.estado=e;
		this.direccion=di;
		
	}
	
	public Cliente(String d, String c, String a, String n, String s, LocalDate fechanac, String di, int l, String mail, String u, String e) {
		
		this.dni=d;
		this.cuil=c;
		this.apellido=a;
		this.localidad.setCodLocalidad(l);
		this.nombre=n;
		this.sexo=s;
		this.fechaNac=fechanac;
		this.email=mail;
		this.usuario=u;
		this.estado=e;
		this.direccion=di;
		
	}



	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}


	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEstado() {
		return estado;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return apellido + ", " + nombre;
	}
	
	
	
}




