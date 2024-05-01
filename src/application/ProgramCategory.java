package application;

import java.util.Scanner;

import model.dao.CategoryDao;
import model.dao.DaoFactory;
import model.entities.Category;

public class ProgramCategory {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		CategoryDao catDao = DaoFactory.createCategoryDao();

		System.out.println("\n*** TEST 1: Category insert *** ");
		Category category = new Category(null, "Headphones");
		catDao.insert(category);
		System.out.println("Inserted! New id = " + category.getId());

		System.out.println("\n*** TEST 2: Category FindById *** ");
		category = catDao.findById(5);
		System.out.println(category);
		sc.close();
	}

}
