package model.dao;

import db.DBCon;
import model.dao.impl.CategoryDaoJDBC;
import model.dao.impl.ProductDaoJDBC;

public class DaoFactory {

	// ABSTRAÇÃO DE ACESSO AO RESTANTE DA APLICAÇÃO

	public static CategoryDao createCategoryDao() {
		return new CategoryDaoJDBC(DBCon.getConnection());
	}

	public static ProductDao createProductDao() {
		return new ProductDaoJDBC(DBCon.getConnection());
	}
}
