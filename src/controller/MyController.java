package controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import data.LoginDAO;
import data.MCDAO;
import data.ShoppingCartItemsEntitie;
import data.addressEntitie;
import data.productsEntitie;
import data.testHolder;
import data.userEntitie;


@Controller
public class MyController {
	@Autowired 
	private MCDAO mcdao;
	
	@ResponseBody
	@RequestMapping("ping")
	public String ping(){
		return "pong";
	}
	

	@ResponseBody
	@RequestMapping(path = "addToCart", method = RequestMethod.POST)
	public void addToCart(@RequestBody String json)
	{
		System.out.println("add to cart");
		System.out.println(json);
		mcdao.addToCart(json);
	}
	

	

	@ResponseBody
	@RequestMapping(path = "product/{id}", method = RequestMethod.GET)
	public productsEntitie getProductsById(@PathVariable int id)
	{

		System.out.println("int product id");
		return mcdao.getSingleProduct(id);
	}
	
	@ResponseBody
	@RequestMapping(path = "allProducts", method = RequestMethod.GET)
	public List<productsEntitie> getAllProducts()
	{
		System.out.println("in all products route");
		return mcdao.getAllProducts();
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(path = "category/{cat}", method = RequestMethod.GET)
	public List<productsEntitie> getCategory(@PathVariable String cat)
	{
		System.out.println("in get categories "+cat);
		return mcdao.getCategories(cat);
	}
	
	@ResponseBody
	@RequestMapping(path = "cart/{id}", method = RequestMethod.GET)
	public List<testHolder> getShoppingCartItems(@PathVariable String id)
	{
		System.out.println("Inside get shopping cart items controller method for ID : " + id);
		List<testHolder> cart = new ArrayList<>();
		
		List<ShoppingCartItemsEntitie> test= mcdao.getShoppingCartItems(id);
		for(int i =0; i <test.size(); i ++)
		{
			
			cart.add(new testHolder(i, test.get(i).getProducts_id(), test.get(i).getQuantity(), test.get(i).getShoppingCart_id()));
		}
		
		
		System.out.println("items found while in controller are:  "+cart);
		return cart;
	}
	
	@ResponseBody
	@RequestMapping(path = "search/{id}", method = RequestMethod.GET)
	public List<productsEntitie> searchProduct(@PathVariable String id)
	{
		System.out.println("in get products "+id);
		return mcdao.searchProducts(id);
	}
	@ResponseBody
	@RequestMapping(path = "address/{id}", method = RequestMethod.GET)
	public addressEntitie getAddresses(@PathVariable String id)
	{

		return mcdao.getAddress(id);
	}
	@ResponseBody
	@RequestMapping(path = "editAddress", method = RequestMethod.POST)
	public void editAddress(@RequestBody String address)
	{
		mcdao.editAddress(address);
	}
	
	
	
	@ResponseBody
	@RequestMapping(path = "deleteCartItem/{id}", method = RequestMethod.DELETE)
	public void deleteFromShoppingCart(@PathVariable String id)
	{
		System.out.println("in controller to delete from shopping cart");
		System.out.println("id: "+id);
		 mcdao.deleteCartItem(id);
	}
	
}

