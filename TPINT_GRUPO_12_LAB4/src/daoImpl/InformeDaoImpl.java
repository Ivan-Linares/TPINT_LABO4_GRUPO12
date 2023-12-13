package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Entidad.Informe;
import dao.InformeDao;

public class InformeDaoImpl implements InformeDao {

	@Override
	public ArrayList<Informe> CargarInforme() {
		
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		Informe f=new Informe();
		ArrayList<Informe> listado = new ArrayList<Informe>();
		
		try {
			statement = con.prepareStatement("call sp_resumentotal;");
			
			ResultSet r=statement.executeQuery();
			while(r.next()) {
				f = cargarInforme(r);
				listado.add(f);
			}
						
		}
		
		catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		finally {
			Conexion.instancia.cerrarConexion();
		}
		
		return listado;
	}

	private Informe cargarInforme(ResultSet r) {
		try {
			Informe f = new Informe();
			
			f.setRegion(r.getString("region"));
			f.setClientesActivos(r.getInt("clientes_activos"));
			f.setClientesInactivos(r.getInt("clientes_inactivos"));
			f.setClientesPendientes(r.getInt("clientes_pendientes"));
			f.setSaldoTotal(r.getFloat("Saldo_total"));
			f.setMovimientosAltasCuenta(r.getFloat("movimientos_altas_cuenta"));
			f.setMovimientosAltasPrestamo(r.getFloat("movimientos_altas_prestamo"));
			f.setMovimientosPagoPrestamo(r.getFloat("movimientos_pago_prestamo"));
			f.setMovimientosExtracciones(r.getFloat("movimientos_extracciones"));
			f.setMovimientosDeposito(r.getFloat("movimientos_deposito"));
	
			return f;			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Informe> CargarInforme(int pais) {
		
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		Informe f=new Informe();
		ArrayList<Informe> listado = new ArrayList<Informe>();
		
		try {
			statement = con.prepareStatement("call sp_resumenpais(?);");
			statement.setInt(1, pais);
			
			ResultSet r=statement.executeQuery();
			while(r.next()) {
				f = cargarInforme(r);
				listado.add(f);
			}
						
		}
		
		catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		finally {
			Conexion.instancia.cerrarConexion();
		}
		
		return listado;
	}

	@Override
	public ArrayList<Informe> CargarInforme(int pais, int provincia) {
		PreparedStatement statement;
		Connection con = Conexion.getConexion().getSQLConexion();
		Informe f=new Informe();
		ArrayList<Informe> listado = new ArrayList<Informe>();
		
		try {
			statement = con.prepareStatement("call sp_resumenprovincia(?,?);");
			statement.setInt(1, provincia);
			statement.setInt(2, pais);
			
			ResultSet r=statement.executeQuery();
			while(r.next()) {
				f = cargarInforme(r);
				listado.add(f);
			}
						
		}
		
		catch (Exception e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		finally {
			Conexion.instancia.cerrarConexion();
		}
		
		return listado;
	}

}
