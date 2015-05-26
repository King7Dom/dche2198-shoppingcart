package controller;

import java.util.ArrayList;
import java.util.List;

import model.Cart;
import model.CartItem;
import model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import dao.DaoFactory;
import dao.ProductDao;

@Controller
@RequestMapping("/carts")
@SessionAttributes("cart")
public class CartController{

	private final ProductDao pdao = DaoFactory.getInstance().getProductDao();
	
	@ModelAttribute("cart")
	public Cart createCart(){
		return new Cart();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String show( Model model, @ModelAttribute(value="cart") Cart cart){
		List<Product> products = pdao.getAllProducts();
		model.addAttribute("products", products);
		return "catalogue";
	}
	

	
	@RequestMapping("/add/{productId}")
	public String add(@PathVariable int productId, Model model, @ModelAttribute("cart") Cart cart){
		
		List<Product> products = pdao.getAllProducts();
		model.addAttribute("products", products);
		Product p = pdao.getProductById(productId);
		cart.addItem(p); //add to cart
		
		return "catalogue";
	}
	
}
