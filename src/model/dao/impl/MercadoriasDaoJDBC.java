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
import model.dao.MercadoriasDao;
import model.entities.Mercadorias;

	// esse crude vai servi para certas coias como, colocar os nomes no meu combobox , atualizar mercadorias,casdastra mercadorias, deletar mercadorias

public class MercadoriasDaoJDBC implements MercadoriasDao{
	
	private Connection conn = null;
	
	public MercadoriasDaoJDBC(Connection conn) {
		this.conn = conn;
	}	
	
	@Override
	public void insert(Mercadorias obj) { // veja se vai ser possivel pegar os paramentros de um objeto dessa forma
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(" INSERT INTO produtos "  
										+"(codProduct,descricao,preco)" 
										+ " VALUES " 
										+ " (null,?,?) ",  
										Statement.RETURN_GENERATED_KEYS 
										);
		//	st.setInt(1, (Integer) null);
			st.setString(1, obj.getNomeProduto());
			st.setDouble(2, obj.getPrecoProduto());
			int linhasModificadas = st.executeUpdate();
			if(linhasModificadas > 0) {
				System.out.println("Elemento inserido com sucesso");
			}else {
				System.out.println("Nenhuma linha foi modificada");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(String newName,double preco,String nomeAtual) {
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"UPDATE produtos "
					+ "SET descricao = ?, preco = ?"
					+ "WHERE "
					+ "(descricao = ?)"); // codigo para atualizar os dados no sql
			
			st.setString(1, newName);
			st.setDouble(2, preco);
			st.setString(3, nomeAtual);
			
			int linhasModificadas = st.executeUpdate();
			System.out.println("Feito ,linhas modificadas: " + linhasModificadas);
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
			st = conn.prepareStatement(" DELETE FROM produtos " + 
										" WHERE descricao = ? ");
			st.setString(1, nome);
			
			st.executeUpdate();
			System.out.println("Deletado");
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Mercadorias findByName(String nome) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(" SELECT * FROM produtos WHERE descricao = ? ");
			st.setString(1,nome); 
			rs = st.executeQuery(); 
			if(rs.next()) { 
				Mercadorias obj = new Mercadorias();
				obj.setCodProduct(rs.getInt("codProduct"));
				obj.setNomeProduto(rs.getString("descricao")); 
				obj.setPrecoProduto(rs.getDouble("preco"));
				return obj;
				}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			}
	}
	public int count(){
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery("SELECT COUNT(*) FROM produtos");
			if(rs.getRow() >= 0) {
				return rs.getRow();
			}else {
				return 0;
			}
			
		}
		catch(SQLException e) { // pode gerar esse tipo de exceção 
			e.printStackTrace(); 
		}
		finally {
			DB.closeStatement(st);//st.close(); // para evitar q isso fique dando problemas de exception criei uma classe para trata isso no pacote db em DB
			DB.closeResultSet(rs);	//rs.close();
		}
		return 0;
		
	}
	@Override
	public List<Mercadorias> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(" SELECT * FROM produtos ORDER BY descricao");
			rs = st.executeQuery();
			
			List<Mercadorias> list = new ArrayList<>();
			while(rs.next()) {
				Mercadorias merca = new Mercadorias();
				merca.setCodProduct(rs.getInt("codProduct"));
				merca.setNomeProduto(rs.getString("descricao"));
				merca.setPrecoProduto(rs.getDouble("preco"));
				list.add(merca);
				}
			return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage()); 
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeResultSet(rs);
		}
	}
	
}
