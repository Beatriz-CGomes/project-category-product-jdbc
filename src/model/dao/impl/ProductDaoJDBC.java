package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DBCon;
import db.DbException;
import db.DbIntegrityException;
import model.dao.ProductDao;
import model.entities.Category;
import model.entities.Product;

public class ProductDaoJDBC implements ProductDao {

	Connection conn;

	public ProductDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	// INSERINDO PRODUTO
	@Override
	public void insert(Product prod) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO tb_product " + "(name, description, quantity, price, expirationDate, tb_category_id)"
							+ "VALUES " + "(?, ?, ?, ?, ?, ?) ",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, prod.getName());
			st.setString(2, prod.getDescription());
			st.setInt(3, prod.getQuantity());
			st.setDouble(4, prod.getPrice());
			st.setDate(5, new java.sql.Date(prod.getExpirationDate().getTime()));
			st.setInt(6, prod.getCategory().getId());

			int row = st.executeUpdate();
			if (row > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					prod.setId(id);
				}
				DBCon.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected! ");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DBCon.closeStatement(st);
		}

	}

	// UPDATE
	@Override
	public void update(Product prod) {
		PreparedStatement st = null;
	  
	
		  try {
				st = conn.prepareStatement("UPDATE tb_product "
						+ "SET name = ?, description = ?, quantity = ?, price = ?, expirationDate = ?, tb_category_id = ? " 
						+ "WHERE Id = ? ");

				st.setString(1, prod.getName());
				st.setString(2, prod.getDescription());
				st.setInt(3, prod.getQuantity());
				st.setDouble(4, prod.getPrice());
				st.setDate(5, new java.sql.Date(prod.getExpirationDate().getTime()));
				st.setInt(6, prod.getCategory().getId());
				st.setInt(7, prod.getId());
				

				st.executeUpdate();

			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			} finally {
				DBCon.closeStatement(st);
			}
		}


	// DELETANDO POR ID
	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("SELECT FROM tb_product " + "WHERE id = ? ");

			st.setInt(1, id);

			int row = st.executeUpdate();
			if (row == 0) {
				throw new DbException("This ID does not exist");
			}

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DBCon.closeStatement(st);
		}

	}

	// ENCONTRANDO POR ID
	@Override
	public Product findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT tb_product.*,tb_category.name as CatName " + "FROM tb_product INNER JOIN tb_category "
							+ "ON tb_product.tb_category_id = tb_category.id " + "WHERE tb_product.id = ? ");

			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				Category category = instanCategory(rs);

				Product product = instanProduct(rs, category);
				return product;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DBCon.closeStatement(st);
			DBCon.closeResultSet(rs);
		}

	}

	// INSTANCIAÇÃO PARA CRIAR O OBJETO DE PRODUCT E CATEGORY PARA O CODIGO FICAR
	// MAIS LIMPO
	private Category instanCategory(ResultSet rs) throws SQLException {
		Category category = new Category();
		category.setId(rs.getInt("tb_category_id"));
		category.setName(rs.getString("CatName"));

		return category;
	}

	private Product instanProduct(ResultSet rs, Category category) throws SQLException {
		Product product = new Product();
		product.setName(rs.getString("name"));
		product.setDescription(rs.getString("description"));
		product.setQuantity(rs.getInt("quantity"));
		product.setPrice(rs.getDouble("price"));
		product.setExpirationDate(rs.getDate("expirationDate"));
		product.setCategory(category);

		return product;
	}

	// LISTANDO TUDO - ENCONTRANDO TUDO
	@Override
	public List<Product> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT tb_product.*,tb_category.Name as CatName "
					+ "FROM tb_product INNER JOIN tb_category " + "ON tb_product.tb_category_id = tb_category.id "
					+  "ORDER BY name ");

			rs = st.executeQuery();

			List<Product> listProduct = new ArrayList<>();
			Map<Integer, Category> cat = new HashMap<>();
			while (rs.next()) {

				Category categories = cat.get(rs.getInt("tb_category_id"));
				if (categories == null) {
					categories = instanCategory(rs);
					cat.put(rs.getInt("tb_category_id"), categories);
				}
				Product pro = instanProduct(rs, categories);
				listProduct.add(pro);
			}
			return listProduct;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DBCon.closeStatement(st);
			DBCon.closeResultSet(rs);
		}
	}

	// ENCONTRANDO POR CATEGORIA
	@Override
	public List<Product> findByCategory(Category category) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT tb_product.*,tb_category.name as CatName "
					+ "FROM tb_product INNER JOIN tb_category " + "ON tb_product.tb_category_id = tb_category.id "
					+ "WHERE tb_category_id = ? " + "ORDER BY name ");

			st.setInt(1, category.getId());
			rs = st.executeQuery();

			List<Product> listProduct = new ArrayList<>();
			Map<Integer, Category> cat = new HashMap<>();
			while (rs.next()) {

				Category categories = cat.get(rs.getInt("tb_category_id"));
				if (categories == null) {
					categories = instanCategory(rs);
					cat.put(rs.getInt("tb_category_id"), categories);
				}
				Product pro = instanProduct(rs, categories);
				listProduct.add(pro);
			}
			return listProduct;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DBCon.closeStatement(st);
			DBCon.closeResultSet(rs);
		}
	}

}
