package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Departament;

public class DepartmentProgram {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
	
		System.out.println("\n==== TESTE 1: findById ====");
		
		DepartmentDao depDao = DaoFactory.createDepartmentDao();
		Departament dep = depDao.findById(3);
		System.out.println(dep);
		
		System.out.println("\n==== TESTE 2: findAll ====");
		List<Departament> list = depDao.findall();
		for (Departament l : list) {
			System.out.println(l); 
		}
		
		System.out.println("\n==== TESTE 3: insert ====");
		Departament newDepartament = new Departament(null, "RH");
		depDao.insert(newDepartament);
		System.out.println("Inserted  department: "  + newDepartament.getId());
		
		
		System.out.println("\n=== TEST 4: update =======");
		Departament dep2 = depDao.findById(1);
		dep2.setName("Food");
		depDao.update(dep2);
		System.out.println("Update completed");
		
		System.out.println("\n=== TEST 5: delete =======");
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		depDao.deleteById(id);
		System.out.println("Delete completed");
		sc.close();
		sc.close();
	}	
	

}
