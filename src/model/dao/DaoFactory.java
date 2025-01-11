package model.dao;

import db.DB;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	public static SellerDao createSellerDao() { 
		// Este método é do tipo da interface SellerDao
		// e retorna a classe que instancia essa interface que é a 
		// SellerDaoJDBC, dessa forma não expomos a implementação nesse método
		// somente a interface
		// que ai para instanciar um dao no programa principal eu passo só interface e o metodo
		return new SellerDaoJDBC(DB.getConnection());
		// quando passamos um valor de conexao com o banco na classe SellerDaoJDBC
		// o método vai obrigar passarmos um parametro que faz a conexao com o banco
		// como argumento
	}
	
	public static DepartmentDao createDepartmentDao() {
		return new DepartmentDaoJDBC(DB.getConnection());
	}
	
}
