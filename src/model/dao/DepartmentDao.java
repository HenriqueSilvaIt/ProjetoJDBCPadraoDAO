package model.dao;

import java.util.List;

import model.entities.Departament;

public interface DepartmentDao {
	
	void insert(Departament obj); // responsavel por inserir no banco
	void update(Departament obj); // update no banco
	void deleteById(Integer id); // delete no banco pelo ID
	Departament findById (Integer id); // Procura o departamento pelo ID
	List<Departament> findall(); // retorna todos os departamento por isso tem que ser
	// lista
	
	
}
