package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name="shoppingcart")
@NamedQueries({ @NamedQuery(name = "getALLShoppingCart", query = "select u from shoppingCartEntitie u"),
	@NamedQuery(name = "getSCbyID", query = "select u from shoppingCartEntitie u where u.id = :id"),
	@NamedQuery(name = "deleteSCbyID", query = "delete  from shoppingCartEntitie  where id = :id") })
public class shoppingCartEntitie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@JsonManagedReference(value ="UserShoppingCart")
	@OneToOne(optional = false, cascade =  CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false)
	private userEntitie user_id;
	
	private String type; 
	
	
	@ManyToMany(mappedBy = "cartList", fetch = FetchType.EAGER, cascade =  CascadeType.ALL)
	@JsonBackReference(value ="ShoppingCartToProducts")
	private List<productsEntitie> productsList = new ArrayList<>();
	
	@OneToMany(mappedBy = "shoppingCart_id", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JsonManagedReference(value ="shopingCartItemsToShoppingCart")
	private List<ShoppingCartItemsEntitie> items;
	 
	public shoppingCartEntitie(){}
	
	public shoppingCartEntitie(int id, userEntitie users_id, String type) {
		super();
		this.id = id;
		this.user_id = users_id;
		this.type = type;
	}
	
	
	
	public userEntitie getUsers_id() {
		return user_id;
	}
	
	public void setUsers_id(userEntitie users_id) {
		this.user_id = users_id;
	}
	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<productsEntitie> getProductsList() {
		return productsList;
	}
	


	public void addToProductsList(productsEntitie product){
		this.productsList.add(product);
	}
	
	public void setProductsList(List<productsEntitie> productsList) {
		this.productsList = productsList;
	}

	/**
	 * @return the items
	 */
	public List<ShoppingCartItemsEntitie> getItems()
	{
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<ShoppingCartItemsEntitie> items)
	{
		this.items = items;
	}
	
	public void addShoppingCartItemsEntitie(ShoppingCartItemsEntitie item) {
	if (!getItems().contains(item)) {
		getItems().add(item);
	}
}

public void removeShoppingCartItemsEntitie(ShoppingCartItemsEntitie item) {
	if (getItems().contains(item)) {
		getItems().remove(item);

	}
}
	

//	@Override
//	public String toString() {
//		return "shoppingCartEntitie [id=" + id + ", user_id=" + user_id + ", type=" + type + ", productsList="
//				+ productsList + "]";
//	}
//	
	
	

}
