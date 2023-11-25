package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Entidad.Localidad;
import Entidad.Pais;
import Entidad.Provincia;
import dao.LocalidadDAO;

public class LocalidadDAOImpl implements LocalidadDAO {
	
	private Provincia provincia;
	private Pais pais;
	private Localidad localidad;

	@Override
	public ArrayList<Localidad> filtrar(int id) {
		
		PreparedStatement statement;
		ArrayList<Localidad> lista = new ArrayList<Localidad>();
		Connection cn = Conexion.getConexion().getSQLConexion();
		try {
			statement = cn.prepareStatement("Select * From Localidades l inner join Provincia p on p.codProvincia=l.codProvincia inner join pais pa on pa.codPais=p.codPais where l.codProvincia=?");
			statement.setInt(1, id);
			
			ResultSet r=statement.executeQuery();
			while(r.next()) {
				iniciar();
				pais.setCode(r.getInt("p.codPais"));
				pais.setName(r.getString("pa.NombrePais"));
				provincia.setPais(pais);
				provincia.setCodProvincia(r.getInt("l.codProvincia"));
				provincia.setNombreProvincia(r.getString("p.NombreProvincia"));
				localidad.setProvincia(provincia);
				localidad.setCodLocalidad(r.getInt("l.codLocalidad"));
				localidad.setNombreLocalidad(r.getString("l.NombreLocalidad"));
				lista.add(localidad);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public void iniciar() {
		
		localidad = new Localidad();
		provincia = new Provincia();
		pais=new Pais();
	}

	@Override
	public ArrayList<Localidad> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
