package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.ProductDao;
import model.entities.Category;
import model.entities.Product;

public class ProgramProduct {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		ProductDao productDao = DaoFactory.createProductDao();

		System.out.println();
		System.out.println("\n*** TEST 1: Product finById *** ");
		Product product = productDao.findById(5);
		System.out.println(product);

		System.out.println();
		System.out.println("\n*** TEST 2: Product findyByCategory *** ");
		Category category = new Category(2, null);
		List<Product> list = productDao.findByCategory(category);
		for (Product p : list) {
			System.out.println();
			System.out.println(p);
		}

		System.out.println();
		System.out.println("\n*** TEST 3: Product insert *** ");
		Product newProduct = new Product(null, "Notebook Asus", "Processador i7", 12, 3000.0, new Date(), category);
		productDao.insert(newProduct);
		System.out.println("New Product: " + newProduct.getId());

		System.out.println();
		System.out.println("\n*** TEST 4: Product findAll *** ");
		list = productDao.findAll();
		for (Product p : list) {
			System.out.println();
			System.out.println(p);
		}

		System.out.println();
		System.out.println("\n*** TEST 5: Product update *** ");
		product = productDao.findById(8);
		product.setName("Domestic");
		productDao.update(product);
		System.out.println("Update completed ");

		System.out.println();
		System.out.println("\n*** TEST 6: Product delete *** ");
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		productDao.deleteById(id);
		System.out.println("Deleted");

		sc.close();
	}

}
