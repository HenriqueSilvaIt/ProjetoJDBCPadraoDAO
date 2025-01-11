package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Departament;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	// Criando conexão com o banco
	
	private Connection conn;
	
	
	// Fazemos aqui um construtor para passar no programa principal
	// o parametro da variavel com para ter uma conexão(e uso ele para conectar
	// ao banco nas operações
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			// Passando query de consulta com WHERE ?
		ps = conn.prepareStatement(
					"SELECT seller.*, departament.Name as DepName"
					+ " FROM seller INNER JOIN departament"
					+ " ON seller.DepartamentId = departament.Id"
					+ " WHERE seller.Id = ?"); // Interrogação só funciona no Java n no SQL 
		// Escolhendo o ? e passando um valor nele
		ps.setInt(1, 3);	
		
		// Abaixo estamos fazendo o ResultSet rs = executar o ps
		// e o resultado do PreparedStament ps, vai ser armazenado no resultset Rs
		rs = ps.executeQuery(); // ResultSet sempre começa na posição 0
		
		// .next verifica se o Result set retorna alguma coisa
		// se retorna quer dizer que o resultado da nossa query não é nula
		// e retornou os dados que eu consultei

		
		//Associação de objeto (associated objetcts)
		// o ResultSet retorna os dados da query em forma de tabela
		// temos que transforma esses dados em um objeto

				if (rs.next()) {
			// Criando o Objeto Departament a partir do resultado da query
			// usando o istancianteNomeDaObjeto para instanciar a função
			// pois para deixar o código mais organizado
			// vamos deixar as informaçõe que pega os dados da tabela
			// e transforma em objeto dentro de uma função
			// abaixp só declaramos o objeto chamando a função e pasando
			// o rs ResultSet como argumento que traz o resultado da query
			// e a função vai pegar esse resultado da query e armazena
			// de forma consolidada no objeto
			Departament dep = instancianteDepartment(rs);
			//Criando o objeto seller a partir do resultado da query
			Seller obj = instanciateSeller(rs, dep); //aqui eu passo o dep
			//  que é o objeto filho que eu instanciei acim
			
			return obj;
		}
		else {
		return null;
		}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
			// aqui nao fechamos a conexão, poois temos outras operações
			// nessa classe que pode usar a conexao, só fechamos a conexao
			// no programa principal depois
		}
		
		
		
		
	}

	private Departament instancianteDepartment(ResultSet rs) throws SQLException {
		// nesse método de função que pega a tabela e transforma em objeto
		// nós propragamos a exceção pr que ela seja tratada no método
		// de ação findById
		Departament dep =  new Departament();
		 new Departament();
			dep.setId(rs.getInt("DepartamentId"));// estamos pegando o ID do departamento
			// na tabela seller pelo ResultSet, usamos getInt porque o campo
			// DepartamentId tem valores inteiros
			dep.setName(rs.getString("DepName"));
			return dep;
	}
	
	private Seller instanciateSeller(ResultSet rs, Departament dep) throws SQLException {
		Seller obj = new Seller();
		new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate")); // é só passa o nome da coluna
		obj.setDepartment(dep); // aqui no objeto filho
		// colocamos o objeto inteiro
		return obj;
	}
	

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
