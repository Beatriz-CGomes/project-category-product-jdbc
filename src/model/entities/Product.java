package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String description;
	private Integer quantity;
	private Double price;
	private Date expirationDate;

	private Category category;

	public Product() {

	}

	public Product(Integer id, String name, String description, Integer quantity, Double price, Date expirationDate,
			Category category) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.expirationDate = expirationDate;
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id: " + id + "\n");
		sb.append("Name: " + name + "\n");
		sb.append("Description: " + description + "\n");
		sb.append("Quantity: " + quantity + "\n");
		sb.append("Price: " + String.format("%.2f", price) + "\n");
		sb.append("Expiration Date" + expirationDate);
		sb.append("Category: " + category);
		return sb.toString();
	}

}
