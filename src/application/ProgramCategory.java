package application;

import java.util.List;
import java.util.Scanner;

import model.dao.CategoryDao;
import model.dao.DaoFactory;
import model.entities.Category;

public class ProgramCategory {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		CategoryDao catDao = DaoFactory.createCategoryDao();

		System.out.println();
		System.out.println("\n*** TEST 1: Category insert *** ");
		Category category = new Category(null, "Headphones");
		catDao.insert(category);
		System.out.println("Inserted! New id = " + category.getId());

		System.out.println();
		System.out.println("\n*** TEST 2: Category FindById *** ");
		category = catDao.findById(5);
		System.out.println(category);
		
		System.out.println();
		System.out.println("\n*** TEST 3: Category FindAll *** ");
		List<Category> listCategory = catDao.findAll();
		for(Category cat : listCategory) {
			System.out.println(cat);
			System.out.println();
		}
		
		System.out.println();
		System.out.println("\n*** TEST 3: Category update *** ");
		category = catDao.findById(8);
		category.setName(" ");
		catDao.update(category);
		System.out.println("Update finished ");
		
		System.out.println();
		System.out.println("\n*** TEST 3: Category delete *** ");
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		catDao.deleteById(id);
		System.out.println("Id deleted");
		
		sc.close();
	}

}
