package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import model.Product;

/**
 * An in-memory implementation of the Dao
 * @author zhouy
 *
 */
public class ProductDaoMemImpl implements ProductDao {


	private List<Product> products;
	private int lastId = 0;
	
	public ProductDaoMemImpl(){
		products = new ArrayList<Product>();
		addProduct(new Product("Web Application Architecture","textbook","webapplication.jpg",79.95));
		addProduct(new Product("Internet How to Program","textbook","wwwprogramming.jpg",109.95));
	}
	@Override
	public void addProduct(Product p) {
		// TODO Auto-generated method stub
		lastId ++;
		p.setProductId(lastId);
		products.add(p);
	}

	@Override
	public void deleteProduct(int productId) {
		// TODO Auto-generated method stub
		for (int i = 0; i < products.size(); i ++){
			if (products.get(i).getProductId() == productId){
				products.remove(i);
				break;
			}
		}
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return products;
	}

	@Override
	public Product getProductById(int productId) {
		// TODO Auto-generated method stub
		for (Product prod: products){
			if (prod.getProductId() == productId)
				return prod;
		}
		return null;
	}

	@Override
	public void updateProduct(Product p) {
		// TODO Auto-generated method stub
		int prodId = p.getProductId();
		for (int i = 0; i < products.size(); i++){
			if (products.get(i).getProductId() == prodId){
				products.set(i,p );
				return;
			}
		}
	}

}
