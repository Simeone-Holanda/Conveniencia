package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import db.DbException;
import model.dao.LoginDao;
import model.entities.Login;

public class LoginDaoJDBC implements LoginDao {

	private Connection conn;
	
	public LoginDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Login findByNameSenha(String name, String senha) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(" SELECT  nome,senha  FROM login.usuarios WHERE nome = ? and senha = ? ;");
			st.setString(1, name);
			st.setString(2, senha);
			rs = st.executeQuery();
			if(rs.next()) {
				Login obj = new Login();
				obj.setName(rs.getString("nome"));
				obj.setSenha(rs.getString("senha"));
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

}
