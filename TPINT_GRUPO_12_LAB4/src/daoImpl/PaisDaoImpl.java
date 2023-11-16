package daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Entidad.Pais;
import Entidad.TipoCuenta;
import dao.PaisDao;

public class PaisDaoImpl implements PaisDao {
	
	private Pais pais;

	@Override
	public boolean insertar(Pais p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Pais p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(Pais p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Pais> filtrar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Pais> listar() {
		ArrayList<Pais> lista = new ArrayList<Pais>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select codPais, NombrePais from Pais");
			while(rs.next()) {
				iniciar();
				pais.setCode(rs.getInt("codPais"));
				pais.setName(rs.getString("NombrePais"));
				
				lista.add(pais);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public void iniciar() {
		pais = new Pais();
	}

}
