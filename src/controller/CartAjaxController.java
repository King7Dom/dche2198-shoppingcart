package controller;

import java.util.ArrayList;
import java.util.List;

import model.Cart;
import model.CartItem;
import model.Product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import dao.DaoFactory;
import dao.ProductDao;

@Controller
@RequestMapping("/cartsAjax")
@SessionAttributes("cart")
public class CartAjaxController {

	private final ProductDao pdao = DaoFactory.getInstance().getProductDao();
	
	@ModelAttribute("cart")
	public Cart createCart(){
		return new Cart();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String show( Model model, @ModelAttribute(value="cart") Cart cart){
		List<Product> products = pdao.getAllProducts();
		model.addAttribute("products", products);
		return "catalogueAjax";
	}
	
	@RequestMapping("/add/{productId}")
	public String add(@PathVariable int productId, Model model, @ModelAttribute("cart") Cart cart){

		Product p = pdao.getProductById(productId);
		cart.addItem(p); //add the item in cart
		return "cart_partial";
	}
	
	@RequestMapping("/remove/{productId}")
	public String remove(@PathVariable int productId, Model model, @ModelAttribute("cart") Cart cart) {
		Product product = pdao.getProductById(productId);
		if (cart != null) {
			cart.removeItem(product);
		}
		
		return "cart_partial";
	}
	
}
