package model.dao;

import java.util.List;

import model.entities.Vendas;

public interface VendasDao {
	void insert(Vendas obj,int qtd, double valor);
	void deleteByNome(String nome);
	Vendas findByNome(String nome);
	List<Vendas> findAll();
//	void zeraVendas(List<Vendas> listVendas); 
	//List<String> mercadoriasMaisVendidas();
	
}
