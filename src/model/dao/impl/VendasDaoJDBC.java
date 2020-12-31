package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.VendasDao;
import model.entities.Vendas;

public class VendasDaoJDBC implements VendasDao{ // relacionado a interface vendasDao 
	
	private Connection conn = null;
	
	public VendasDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override 
	public void insert(Vendas obj, int qtd, double valor) {
		PreparedStatement st = null;
		try{
			st = conn.prepareStatement(" INSERT INTO vendas "  
										+"(codProduct,descricao,preco,qtd,valor)" 
										+ " VALUES " 
										+ " (?,?,?,?,?) ",  
										Statement.RETURN_GENERATED_KEYS 
										);
			st.setInt(1, obj.getCodProduto());
			st.setString(2, obj.getName()); // valor do combobox
			st.setDouble(3, obj.getPreco());
			st.setInt(4,qtd); // caixa de txt 
			st.setDouble(5, valor); // valor total
			int linhasModificadas = st.executeUpdate();
		/*	if(linhasModificadas > 0) {
				System.out.println("Elemento inserido com sucesso");
			}else {
				System.out.println("Nenhuma linha foi modificada");
			}*/
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteByNome(String nome) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(" DELETE FROM vendas " + 
										" WHERE descricao = ? ");
			st.setString(1, nome);
			
			st.executeUpdate();
			//System.out.println("Deletado!");
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public List<Vendas> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(" SELECT * FROM vendas ORDER BY descricao");
			rs = st.executeQuery();
			
			List<Vendas> list = new ArrayList<>();
			
			while(rs.next()) {
				Vendas vendas = new Vendas();
				vendas.setCodProduto(rs.getInt("codProduct"));
				vendas.setName(rs.getString("descricao"));
				vendas.setPreco(rs.getDouble("preco"));
				vendas.setQuantidade(rs.getInt("qtd"));
				vendas.setValor(rs.getDouble("valor"));
				list.add(vendas);
				}
			return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage()); 
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	public Vendas findByNome(String nome) { // esse metodo serve para mostra em nosso table view nossas vendas
											// smp q for inserida uma nova
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(" SELECT * FROM vendas WHERE descricao = ? ");
			st.setString(1,nome); // pegando a venda pelo nome
			rs = st.executeQuery(); 
			if(rs.next()) { // se caso deu td certo e retornou algo a primeira posi��o via ser 0, e os elmentos vao ta na posi��o 1
				Vendas obj = new Vendas();
				obj.setCodProduto(rs.getInt("codProduct"));
				obj.setName(rs.getString("descricao"));
				obj.setPreco(rs.getDouble("preco"));
				obj.setQuantidade(rs.getInt("qtd"));
				obj.setValor(rs.getDouble("valor"));
				return obj;
				}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			// n precisa fechar a conec��o com o banco de dados pq essa foi apenas uma opera��o no caso vc fecha na tela principal depois q chamar td
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			}
	}
	/*
	public void zeraVendas() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("DELETE * FROM vendas);	
			st.executeUpdate();	
			//System.out.println("Lista de vendas apagadas!");
		}
		catch(SQLException e) { // se der erro no mysql retorne uma msg cm esse erro
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
	
	@Override
	public List<String> mercadoriasMaisVendidas() {
		PreparedStatement st = null;	
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(" SELECT count(3) as NrVezes, descricao FROM vendas GROUP BY CLI ORDER BY NrVezes DESC");
			rs = st.executeQuery();
			List<String> list = new ArrayList<>();
			while(rs.next()){
				list.add(rs.getString("descricao"));
			}
			return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage()); 
		}
		finally {
			DB.closeStatement(st);
		}
	}*/
}

