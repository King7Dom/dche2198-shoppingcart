package dao;

import java.util.List;
import model.Product;

public interface ProductDao {
	public List<Product> getAllProducts();
	public void addProduct(Product p);
	public Product getProductById(int productId);
	public void updateProduct(Product p);
	public void deleteProduct(int productId);	
}
