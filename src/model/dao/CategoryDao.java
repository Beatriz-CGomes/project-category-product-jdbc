package model.dao;

import java.util.List;

import model.entities.Category;

public interface CategoryDao {

	void insert(Category cat);

	void update(Category cat);

	void deleteById(Category id);

	Category findById(Category id);

	List<Category> findAll();

}
