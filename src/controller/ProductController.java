package controller;

import java.util.List;

import javax.validation.Valid;

import model.Product;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.DaoFactory;
import dao.ProductDao;

@Controller
@RequestMapping("/products")
public class ProductController {

	private final ProductDao pdao = DaoFactory.getInstance().getProductDao();
	
	@RequestMapping(method = RequestMethod.GET)
	public String listAll(Model model){
		
		List<Product> products = pdao.getAllProducts();
		model.addAttribute("products", products);
		return "productlist";
	}
	
	@RequestMapping(value="/new", method = RequestMethod.GET)
	public String add(Model model){
		Product product = new Product(); // create an empty product
		model.addAttribute("product", product);
		return "product";
	}
	
	// this is the method for inserting a new product or updating an existing prouct
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("product") Product product, BindingResult result){
		
		if (result.hasErrors()) {
	        return "product";
		}
		int productID = product.getProductId();
		if (pdao.getProductById(productID) != null) {
			pdao.updateProduct(product);
		} else {
			pdao.addProduct(product);
		}
		return "redirect:/products";
	}
	
	@RequestMapping("/edit/{productId}")
	public String edit(@PathVariable int productId, Model model){
		//add your code here to find a product based on its id
		//and put it in the model
		Product product = pdao.getProductById(productId); 
		if (product != null) {
			model.addAttribute("product", product);
		}
		return "product";
	}
	
	@RequestMapping("/delete/{productId}")
	public String delete(@PathVariable int productId, Model model){
		pdao.deleteProduct(productId);
		return "redirect:/products";
	}
}
