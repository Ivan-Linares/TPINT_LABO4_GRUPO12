package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Entidad.Pais;
import Entidad.Provincia;
import dao.ProvinciaDao;

public class ProvinciaDaoImpl implements ProvinciaDao{
	
	private Provincia provincia;
	private Pais pais;

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
			ResultSet rs = st.executeQuery("Select p.codPais, p.codProvincia, p.NombreProvincia, pa.NombrePais from Provincia p inner join Pais pa on p.codpais=pa.codpais;");
			while(rs.next()) {
				iniciar();
				pais.setCode(rs.getInt("p.codPais"));
				pais.setName(rs.getString("pa.NombrePais"));
				provincia.setPais(pais);
				provincia.setCodProvincia(rs.getInt("p.codProvincia"));
				provincia.setNombreProvincia(rs.getString("p.NombreProvincia"));
				lista.add(provincia);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public void iniciar() {
		provincia = new Provincia();
		pais=new Pais();
	}

	@Override
	public ArrayList<Provincia> filtrarPorPais(int id) {
		
		PreparedStatement statement;
		ArrayList<Provincia> lista = new ArrayList<Provincia>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			statement = cn.prepareStatement("Select * From Provincia p inner join pais pa on pa.codPais=p.codPais where p.codPais=?");
			statement.setInt(1, id);
			
			ResultSet r=statement.executeQuery();
			while(r.next()) {
				iniciar();
				pais.setCode(r.getInt("p.codPais"));
				pais.setName(r.getString("pa.NombrePais"));
				provincia.setPais(pais);
				provincia.setCodProvincia(r.getInt("p.codProvincia"));
				provincia.setNombreProvincia(r.getString("p.NombreProvincia"));
				lista.add(provincia);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;

	}

}
