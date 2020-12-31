package model.dao;

import java.sql.SQLException;
import java.util.List;

import model.entities.Mercadorias;

public interface MercadoriasDao {
	// Esses sao os famoso crude em mysql
	
	void insert(Mercadorias obj);
	void update(String newName, double preco, String nomeAtual);
	void deleteByNome(String nome);
	public int count();
	Mercadorias findByName(String nome);
	List<Mercadorias> findAll();
	
}
