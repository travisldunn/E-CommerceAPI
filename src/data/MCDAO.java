package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class MCDAO
{
	@PersistenceContext
	EntityManager em;

	public productsEntitie getSingleProduct(int id)
	{
		productsEntitie product = (productsEntitie) em.createNamedQuery("getProductsById").setParameter("id", id)
				.getSingleResult();
		return product;
	}

	public List<productsEntitie> getAllProducts()
	{
		List<productsEntitie> products = (List<productsEntitie>) em.createNamedQuery("getAllProducts").getResultList();
		return products;
	}

	public List<productsEntitie> getCategories(String cat)
	{
		System.out.println("in dao get categories " + cat);
		List<productsEntitie> products = (List<productsEntitie>) em.createNamedQuery("getProductsBycatagory")
				.setParameter("cat", cat).getResultList();
		return products;
	}
	
	public addressEntitie getAddress(String id){
		try{
		addressEntitie aE = (addressEntitie) em.createNamedQuery("getAddressbyUser")
		.setParameter("id", id).getSingleResult();
		return aE;
		}
		catch(Exception e){
			return null;
		}
	}
	
	public void editAddress(String address){
        System.out.println(address);
        int userid = Integer.parseInt(address.replace("{", "").replace("}", "").split(",")[0].split(":")[1]);
        String addr = address.replace("{", "").replace("}", "").split(",")[1].split(":")[1].replaceAll("\"", "");
        String type = address.replace("{", "").replace("}", "").split(",")[2].split(":")[1].replaceAll("\"", "");
        userEntitie uE = em.find(userEntitie.class, userid);
        if(addr != ""){
        uE.getAddress().setAddress(addr);
        }
        if(type != ""){
        uE.getAddress().setType(type);
        }
        em.merge(uE);
        System.out.println(uE);
        
    }
	public List<productsEntitie> searchProducts(String id)
	{
		System.out.println("in dao get products " + id);
		List<productsEntitie> products = em.createQuery("select u from productsEntitie u where u.name like :searchID or u.brand like :searchID or u.catagory like :searchID", productsEntitie.class).setParameter("searchID", "%"+id+"%").getResultList();
		System.out.println("results: "+products);
		return products;
	}



public  List<ShoppingCartItemsEntitie> getShoppingCartItems (String id) 
{
	System.out.println("in dao for get shoppingcartItems and id is " + id);
	userEntitie userID = (userEntitie)em.createNamedQuery("getUserById").setParameter("id", Integer.parseInt(id)).getSingleResult();
//	em.merge(userID);
	int ShoppingCartID = userID.getCart().getId();
//	System.out.println(userID.getCart().getItems());
return userID.getCart().getItems();
}

public void addToCart(String json){
	int productID = Integer.parseInt(json.split(":")[1].split(",")[0].replaceAll("\"", ""));
	int userID = Integer.parseInt(json.split(":")[2].replace("}", ""));
	System.out.println(productID);
	userEntitie user = em.find(userEntitie.class, userID);
	productsEntitie product = em.find(productsEntitie.class, productID);
	System.out.println(product);
	ShoppingCartItemsEntitie scie = null;
	System.out.println(user.getCart() + " user.getCart()");
		if (user.getCart() != null){
			System.out.println("inside cart is already there add to cart");
			user.getCart().addToProductsList(product);
			try{
				System.out.println("in try block of add to cart dao method");
				scie = (ShoppingCartItemsEntitie) em.createNamedQuery("getShoppingCartItem").setParameter("sid", user.getCart()).setParameter("pid", product).getSingleResult();
				System.out.println("1");
				scie.setQuantity(scie.getQuantity() + 1);
				System.out.println("2");
				System.out.println(scie);
				System.out.println("3");
			}
			catch(Exception e){
				System.out.println("in catch block of add to cart dao method");
				scie = new ShoppingCartItemsEntitie();
				scie.setShoppingCart_id(user.getCart());
				scie.setProducts_id(product);
				scie.setQuantity(1);
			}
		}
		else {
			System.out.println("inside else statement for add to cart");
			shoppingCartEntitie mycart = new shoppingCartEntitie();
			user.setCart(mycart);
			System.out.println("inside else statement after user.setCart(mycart)");
			scie = new ShoppingCartItemsEntitie();
			System.out.println("inside else statement after scie = new ShoppingCartItemsEntitie()");
			scie.setProducts_id(product);
			scie.setShoppingCart_id(user.getCart());
			scie.setQuantity(1);
			
			user.getCart().setUsers_id(user);
			System.out.println("inside else statement after user.getCart().setUsers_id(user)");
			user.getCart().setType("shopping cart");
			user.getCart().addToProductsList(product);
			
			//em.merge(mycart);
			System.out.println("inside else statement after em.merge(mycart)");
		}
	
//	em.merge(user);
	//em.merge(product);
	em.persist(scie);
//	System.out.println(user.getCart().getProductsList());
//	System.out.println(user.getCart().getProductsList().size());
	}
	public void deleteCartItem(String id)
	{
		System.out.println("in delete Cart dao method");
		System.out.println("id: "+id);
		em.createNamedQuery("deleteSCitemsbyID").setParameter("id", Integer.parseInt(id)).executeUpdate();
		System.out.println("deleted item" );
		
//	userEntitie user =em.find(userEntitie.class, 3);
//	user.
////		em.merge(user);
		
	}


}