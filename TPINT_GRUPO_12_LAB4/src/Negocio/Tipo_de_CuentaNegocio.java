package Negocio;

import java.util.ArrayList;


import Entidad.TipoCuenta;

public interface Tipo_de_CuentaNegocio {
	public boolean insertar (TipoCuenta tipocuenta);
	public boolean modificar (TipoCuenta tipocuenta);
	public boolean Eliminar (TipoCuenta tipocuenta);
	public ArrayList<TipoCuenta> listar();
}
