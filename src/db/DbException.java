package db;

public class DbException extends RuntimeException {
	
	private static final long serialVersionUID = 1L; // basicamente isso é so um numero de versao da minha classe caso eu queira fzr algo semelhante a ela mudando apenas alguns elementos, seria a 2L 
	
	public DbException(String msg) {
		super(msg);
	}
}
