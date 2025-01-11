package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.DepartmentDao;
import model.entities.Departament;
import model.entities.Seller;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;

	// Construtor de conexão com o banco

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	private Departament instanciateDepartment(ResultSet rs) throws SQLException {
		Departament dep = new Departament();
		new Departament();
			dep.setId(rs.getInt("DepartamentId"));
			dep.setName(rs.getString("Name"));
			return dep;
	}
	
	private Seller instanciateSeller(ResultSet rs, Departament dep) throws SQLException{
		Seller seller = new Seller();
		
			seller.setId(rs.getInt("Id"));
			seller.setName(rs.getString("Name"));
			seller.setEmail(rs.getString("Email"));
			seller.setBaseSalary(rs.getDouble("BaseSalary"));
			seller.setBirthDate(rs.getDate("BirthDate")); // nesse caso de get  pega n precisa instaciar
			// o java.sql.Date
			seller.setDepartment(dep);
			
			return seller;
		}

	@Override
	public void insert(Departament obj) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement( 
					"INSERT INTO departament "
					+ "(Name) "
					+ "VALUES (?)", Statement.RETURN_GENERATED_KEYS
					);
	
			ps.setString(1, obj.getName());
		
		int rows =  ps.executeUpdate();
		
		if ( rows > 0) {
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				obj.setId(id);
			}
			System.out.println("Done, rows affected" + rows);
		} else {
			System.out.println("Unexpected erro, no rows affected");
		}
		
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
 
	}


	@Override
	public void update(Departament obj) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
				"UPDATE departament " +
				"SET Name = ? " +
				"WHERE Id = ?");
			ps.setString(1, obj.getName());
			ps.setInt(2, obj.getId());
			ps.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public Departament findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(
					"SELECT seller.*, Departament.name as DepName"
					+ " FROM seller"
					+ " INNER JOIN departament"
					+ " ON seller.DepartamentId = departament.id"
					+ " WHERE seller.Id = ?");
				
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				Departament dep = instanciateDepartment(rs);
				
				return dep;
			} else {
			return null;
			} 
	}catch (SQLException e ) {
		throw new DbException(e.getMessage());		
} finally {
		DB.closeStatement(ps);
		DB.closeResultSet(rs);
}
	
}
	
	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
				"DELETE FROM departament WHERE Id = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} 
		finally {
			DB.closeStatement(ps);
		}
	}

	@Override
	public List<Departament> findall() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			ps = conn.prepareStatement(
					" SELECT seller.*, departament.Name as DepName "
					+ " FROM seller INNER JOIN departament "
					+ " ON seller.DepartamentId = departament.Id "
					+ " ORDER BY Name");
				
			rs = ps.executeQuery();
			
			List<Departament> list = new ArrayList<>();
			
			while (rs.next()) {
				Departament dep = new Departament();
				dep.setId(rs.getInt("Id"));
				dep.setName(rs.getString("Name"));
				list.add(dep);
			
			}
			return list;
			
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
		
	}
	

}

