package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Departament;
import model.entities.Seller;

public class ProgramJDBC {

	public static void main(String[] args) {
		
		Departament obj = new Departament(1, "Books");
		System.out.println(obj);
		
		// essa é uma forma de declarar o objeto
		// passando a classe DaoFactory que é a classe de responsável por instanciar
		// as classes de implementacao DAO
		// dessa forma abaixo o programa não conhece a classe de implementacao
		// SellerDaoJDBC, ele somente declara a interface e a classe DaoFactory 
		// que é a classe de instanciação passando .métodoresponsável pela classe
		// de implementacao
		
		
		
		System.out.println("==== TESTE 1: seller findById ====");
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		// Classe da tabela, varaivel, nome da inteface com 1º letra mminusculo
		// método que queira utilizar
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n==== TESTE 2: seller findByIdDepartment ====");
		Departament department = new Departament(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for (Seller s : list) {
			System.out.println(s);
		}
		System.out.println("\n==== TESTE 3: seller findAll ====");
		list = sellerDao.findAll();
		for (Seller s : list) {
			System.out.println(s);
		}
	}

}
