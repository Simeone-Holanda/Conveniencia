package model.dao;

import model.entities.Login;

public interface LoginDao { // esse tipo de classe � do tipo interface
		// as interfaces nada mais sao do que modelos de metodos 
		// o acesso ao meu BD � feito apenas para verificar a senha
		// logo so tem uma fun��o
	Login findByNameSenha(String name,String Senha); // com so vou ter um elemento eu n precisava criar essa classe porem eu fiz por organizacao
}
