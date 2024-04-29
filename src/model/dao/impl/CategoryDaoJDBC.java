package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import db.DbException;
import model.dao.CategoryDao;
import model.entities.Category;

public class CategoryDaoJDBC implements CategoryDao {

	// PARA OBTER A CONEXÃO COM BANDO DE DADOS
	Connection conn = null;

	public CategoryDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Category cat) {

	}

	@Override
	public void update(Category cat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Category id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Category findById(Category id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
