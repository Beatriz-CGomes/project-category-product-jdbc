package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DBCon;
import db.DbException;
import model.dao.CategoryDao;
import model.entities.Category;

public class CategoryDaoJDBC implements CategoryDao {

	// PARA OBTER A CONEXÃO COM BANDO DE DADOS
	Connection conn = null;

	public CategoryDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override // INSERÇÃO
	public void insert(Category cat) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO tb_category " + "(name) " + "VALUES " + "(?)",
					+Statement.RETURN_GENERATED_KEYS);

			st.setString(1, cat.getName());

			int row = st.executeUpdate();

			if (row > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					cat.setId(id);
				}
				DBCon.closeResultSet(rs);

			} else {
				throw new DbException("Unexpected error! No rows affected! ");
			}
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		} finally {
			DBCon.closeStatement(st);
		}

	}

	@Override
	public void update(Category cat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	// ENCONTRANDO POR ID
	@Override
	public Category findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM tb_category WHERE Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				Category cat = new Category();
				cat.setId(rs.getInt("id"));
				cat.setName(rs.getString("name"));

				return cat;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DBCon.closeStatement(st);
			DBCon.closeResultSet(rs);
		}

	}

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
