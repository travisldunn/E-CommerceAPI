package data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "ShoppingcartItems")
@NamedQueries({ @NamedQuery(name = "getALLShoppingCartItems", query = "select u from userEntitie u"),
	@NamedQuery(name = "getSCitemsbyID", query = "select u from ShoppingCartItemsEntitie u where u.id = :id"),
	@NamedQuery(name = "deleteSCitemsbyID", query = "delete  from ShoppingCartItemsEntitie  where id = :id"),
	@NamedQuery(name = "getShoppingCartItem", query = "select u from ShoppingCartItemsEntitie u where u.shoppingCart_id = :sid and u.products_id = :pid") })
public class ShoppingCartItemsEntitie {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 
	
	@JsonBackReference(value ="shopingCartItemsToShoppingCart")
	@ManyToOne(cascade =  CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "shoppingCart_id")
	private shoppingCartEntitie shoppingCart_id;
	
	@JsonBackReference(value ="shopingCartItemsToProduct")
	@ManyToOne(cascade =  CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "products_id")
	private productsEntitie products_id;
	private int quantity;
	
	public ShoppingCartItemsEntitie(){}

	public ShoppingCartItemsEntitie(shoppingCartEntitie shoppingCart_id, productsEntitie products_id, int quantity) {
		super();
		this.shoppingCart_id = shoppingCart_id;
		this.products_id = products_id;
		this.quantity = quantity;
	}

	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @return the shoppingCart_id
	 */
	public shoppingCartEntitie getShoppingCart_id()
	{
		return shoppingCart_id;
	}

	/**
	 * @return the products_id
	 */
	public productsEntitie getProducts_id()
	{
		return products_id;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity()
	{
		return quantity;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * @param shoppingCart_id the shoppingCart_id to set
	 */
	public void setShoppingCart_id(shoppingCartEntitie shoppingCart_id)
	{
		this.shoppingCart_id = shoppingCart_id;
	}

	/**
	 * @param products_id the products_id to set
	 */
	public void setProducts_id(productsEntitie products_id)
	{
		this.products_id = products_id;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "ShoppingCartItemsEntitie [id=" + id + ", shoppingCart_id=" + shoppingCart_id + ", products_id="
				+ products_id + ", quantity=" + quantity + "]";
	}



	
}
