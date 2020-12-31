package model.dao;

import db.DB;
import model.dao.impl.LoginDaoJDBC;
import model.dao.impl.MercadoriasDaoJDBC;
import model.dao.impl.VendasDaoJDBC;

public class DaoFactory {
	
	public static LoginDao createLoginDao() {
		return new LoginDaoJDBC(DB.getConnection()); // instanciando nossa implementação ja com a conecção do BD
	}
	public static MercadoriasDao createMercadoriasDao() {
		return new MercadoriasDaoJDBC(DB.getConnection());
	}
	public static VendasDao createVendasDao() {
		return new VendasDaoJDBC(DB.getConnection());
	}
}
