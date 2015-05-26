package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import model.Product;

public class ProductDaoDBImpl implements ProductDao {
	private DataSource ds;
	private String getProductByIdSQL = "SELECT * from products where id = ?",
				getAllProductsSQL = "SELECT * from products",
				deleteProductSQL = "DELETE from products where id = ?" ,
				insertProductSQL = "INSERT into products(title,description,price) values (?,?,?)" ,
				updateProductSQL = "UPDATE products set title = ?, description=?, price=? where id = ?";
	Log log = LogFactory.getLog(ProductDaoDBImpl.class);
	
	public ProductDaoDBImpl() throws Exception{
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			// Look up our data source
			ds = (DataSource) envCtx.lookup("jdbc/ShoppingCart");			
		}catch (NamingException e){
			throw new Exception("cannot find database");
		}catch (Exception e){
			throw new Exception(e.getMessage());
		}
	}
	@Override
	public void addProduct(Product p) {
		try{
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(insertProductSQL);
			ps.setString(1, p.getTitle());
			ps.setString(2, p.getDescription());
			ps.setDouble(3, p.getPrice());
			ps.executeUpdate();
			ps.close();
			conn.close();
		}
		catch(SQLException e){
			log.error("can not insert product " + p);
		}

	}

	@Override
	public void deleteProduct(int productId) {
		try{
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(deleteProductSQL);
			ps.setInt(1, productId);
			ps.executeUpdate();
			ps.close();
			conn.close();
		}
		catch(SQLException e){
			log.error("can not delete product " +productId);
		}

	}

	@Override
	public List<Product> getAllProducts() {
		
		List<Product> products = new ArrayList<Product>();
		try{
			Connection conn =ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(getAllProductsSQL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				products.add(new Product(rs.getInt("id"),
									rs.getString("title"),
									rs.getString("description"),
									rs.getString("img"),
									rs.getDouble("price")));
			}
			rs.close();
			ps.close();
			conn.close();
			return products;
		}catch (Exception e){
			e.printStackTrace();
			log.error("does not get products!");
			return null;
		}
	}

	@Override
	public Product getProductById(int productId) {
		try{
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(getProductByIdSQL);
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();
			Product p = null;
			if (rs.next()){
				p = new Product(rs.getInt("id"),
										rs.getString("title"),
										rs.getString("description"),
										rs.getDouble("price"));
			}
			rs.close();
			ps.close();
			conn.close();
			return p;
		}catch (SQLException e){
			return null;
		}
	}

	@Override
	public void updateProduct(Product p) {
		try{
			Connection conn = ds.getConnection();
			PreparedStatement ps = conn.prepareStatement(updateProductSQL);
			ps.setString(1, p.getTitle());
			ps.setString(2, p.getDescription());
			ps.setDouble(3, p.getPrice());
			ps.setInt(4, p.getProductId());
			ps.executeUpdate();
			ps.close();
			conn.close();
		}
		catch(SQLException e){
			log.error("can not update product " + p);
		}


	}

}
