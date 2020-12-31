package model.entities;

public class Login {
	private String name;
	private String senha;
	
	public Login() {}

	public Login(String name, String senha) {
		this.name = name;
		this.senha = senha;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
	
	
}
