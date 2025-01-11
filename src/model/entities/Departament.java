package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Departament implements Serializable {
	
	// Se quisermos que nosso objeto seja gravado em arquivo tem que implementar
		// o Serializable
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	
	// Construtores
	
	public Departament() {
		
	}
	public Departament(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	// Ge
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() { 
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//HashCode e Equals
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departament other = (Departament) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
	//String
	@Override
	public String toString() {
		return "id:" + id + " name: " + name;
	}

}
