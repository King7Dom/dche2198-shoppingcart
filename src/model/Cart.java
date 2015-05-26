package model;
import java.util.*;
public class Cart {
	private Map<Integer,CartItem> carts;


	public Cart(){
		carts = new HashMap<Integer,CartItem>();
	}

	public void addItem(Product p){
		int productId = p.getProductId();
		CartItem ci = carts.get(productId);
		if (ci == null){
			carts.put(productId, new CartItem(p,1)); // add the new product			
		}else{ // increase the quantity
			ci.increaseQuantity();
		}
	}

	public void removeItem(Product product) {
		int productId = product.getProductId();
		CartItem cartItem = this.carts.get(productId);
		if (cartItem != null) {
			if (cartItem.getQuantity() > 0) {
				cartItem.decreaseQuantity();
				if (cartItem.getQuantity() == 0) {
					this.carts.remove(productId);
				}
			}
		}
	}
	
	public Collection<CartItem> getItems() {
		return carts.values();
	}
	
	public double getTotal(){
		double total = 0.0;
		for (CartItem ci: carts.values()){
			total += ci.getQuantity() * ci.getProduct().getPrice();
		}
		return total;
	}

}
