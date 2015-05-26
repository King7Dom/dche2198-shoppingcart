package model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@XmlRootElement
public class Product {
	@NotEmpty(message="You must supply a title")
	private String title;
	
	private String description,imageUrl;
	@NotNull(message="You must specify the price")
	@Min(value=0, message="Price needs to be a positive number")
	private double price;
	private int productId = -1; //default id will be modified by the storage
	
	public Product(){		
	}
	
	public Product(String title, String description, double price){
		this.title = title;
		this.description = description;
		this.price = price;
	}
	
	public Product(int productId, String title, String description, double price){
		this.productId = productId;
		this.title = title;
		this.description = description;
		this.price = price;
	}
	public Product(int productId, String title, String description, String imgUrl, double price){
		this.productId = productId;
		this.title = title;
		this.description = description;
		this.imageUrl = imgUrl;
		this.price = price;
	}
	public Product(String title, String description, String imgUrl, double price){
		this.title = title;
		this.description = description;
		this.imageUrl = imgUrl;
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public String toString(){
		return "Product " + productId + ": " + title + "@" + price;
	}

}
