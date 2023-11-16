package daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Entidad.Pais;
import Entidad.Provincia;
import dao.ProvinciaDao;

public class ProvinciaDaoImpl implements ProvinciaDao{
	
	private Provincia provincia;

	@Override
	public boolean insertar(Provincia p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Provincia p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificar(Provincia p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Provincia> filtrar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Provincia> listar() {
		ArrayList<Provincia> lista = new ArrayList<Provincia>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select codPais, codProvincia, NombreProvincia from Provincia");
			while(rs.next()) {
				iniciar();
				provincia.setCodPais(rs.getInt("codPais"));
				provincia.setCodProvincia(rs.getInt("codProvincia"));
				provincia.setNombreProvincia(rs.getString("NombreProvincia"));
				lista.add(provincia);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public void iniciar() {
		provincia = new Provincia();
	}

}
