package db;

public class DbException extends RuntimeException {

	// ELE obriga a colocar um número de versão, ai pode colocar um padrão
	private static final long serialVersionUID = 1L;
	
	public DbException(String msg) {
		super(msg);
	}
	
}
