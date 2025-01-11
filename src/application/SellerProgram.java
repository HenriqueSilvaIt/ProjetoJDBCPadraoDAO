package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Departament;
import model.entities.Seller;

public class SellerProgram {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
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
		
		// retorna os vendedores pelo ID do departamento
		System.out.println("\n==== TESTE 2: seller findByIdDepartment ====");
		Departament department = new Departament(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for (Seller s : list) {
			System.out.println(s);
		}
		
		// Retorna os vendedores de todos os departamentos
		System.out.println("\n==== TESTE 3: seller findAll ====");
		list = sellerDao.findAll();
		for (Seller s : list) {
			System.out.println(s);
		}
		
		// Inseri um novo vendedor na tabela
		System.out.println("==== TESTE 4: seller Insert ====");
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0,
				department); // aqui importamos o java util.date
		sellerDao.insert(newSeller);
		System.out.println("Inserted, New ID = " + newSeller.getId());

		System.out.println("\n==== TESTE 5: seller Update ====");
		seller = sellerDao.findById(1); // pegando o vendedor de ID 1
		seller.setName("Martha Wain"); // colocando o nome dele de marta
		sellerDao.update(seller); // fazendo o update  e passando objeto vendedor como
		// argumento
		System.out.println(seller.getName());
		
		System.out.println("\n==== TESTE 6: seller Update ====");
		System.out.print("Enter Id for delete test: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete completed sucessfully!");
		sc.close();
	}	
	

}
