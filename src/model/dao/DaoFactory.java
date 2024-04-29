package model.dao;

import db.DBCon;
import model.dao.impl.CategoryDaoJDBC;

public class DaoFactory {

	// ABSTRAÇÃO DE ACESSO AO RESTANTE DA APLICAÇÃO

	public static CategoryDao createCategoryDao() {
		return new CategoryDaoJDBC(DBCon.getConnection());
	}
}
