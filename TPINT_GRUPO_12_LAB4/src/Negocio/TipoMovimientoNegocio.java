package Negocio;

import java.util.ArrayList;


import Entidad.TipoMovimiento;

public interface TipoMovimientoNegocio {
	public boolean insertar (TipoMovimiento tipoMovimiento);
	public boolean modificar (TipoMovimiento tipoMovimiento);
	public boolean Eliminar (TipoMovimiento tipoMovimiento);
	public ArrayList<TipoMovimiento> listar();
}
