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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;




@Entity
@Table(name = "products")

@NamedQueries({ @NamedQuery(name = "getAllProducts", query = "select u from productsEntitie u"),
	@NamedQuery(name = "getProductsById", query = "select u from productsEntitie u where u.id = :id"),
	@NamedQuery(name = "getProductsBycatagory", query = "select u from productsEntitie u where u.catagory = :cat"),
	@NamedQuery(name = "getProductsByDesription", query = "select u from productsEntitie u where u.description = :description"),
	@NamedQuery(name = "searchProduct", query = "select u from productsEntitie u where u.name like :searchID or u.brand like :searchID or u.catagory like :searchID")})

public class productsEntitie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String catagory;
	private String price;
	private String description;
	private String brand;
	private String name;
	
	@OneToMany(mappedBy = "product_id", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<reviewEntitie> review = new ArrayList<>();
	
	@OneToMany(mappedBy = "products_id", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	 @JsonManagedReference(value ="shopingCartItemsToProduct")
	private List<ShoppingCartItemsEntitie> items;

	@ManyToMany
	@JoinTable(name = "shoppingCartItems", joinColumns = @JoinColumn(name = "products_id") , inverseJoinColumns = @JoinColumn(name = "shoppingCart_id") )
	@JsonBackReference(value ="ShoppingCartToProducts")
	private List<shoppingCartEntitie> cartList = new ArrayList<>();


	public productsEntitie() {
		super();
	}

	public productsEntitie(int id, String catagory, String price, String description, String brand, String name) {
		super();
		this.id = id;
		this.catagory = catagory;
		this.price = price;
		this.description = description;
		this.brand = brand;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the catagory
	 */
	public String getcatagory() {
		return catagory;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param catagory
	 *            the catagory to set
	 */
	public void setcatagory(String catagory) {
		this.catagory = catagory;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param brand
	 *            the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public List<reviewEntitie> getReview() {
		return review;
	}

	public void setReview(List<reviewEntitie> review) {
		this.review = review;
	}

	public void addReview(reviewEntitie review) {
		if (!getReview().contains(review)) {
			getReview().add(review);
		}
	}

	public void removeAddress(reviewEntitie review) {
		if (getReview().contains(review)) {
			getReview().remove(review);

		}
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "productsEntitie [id=" + id + ", catagory=" + catagory + ", price=" + price + ", description="
				+ description + ", brand=" + brand + ", name=" + name + "]";
	}

	/**
	 * @return the cartList
	 */
	public List<shoppingCartEntitie> getCartList()
	{
		return cartList;
	}

	/**
	 * @param cartList the cartList to set
	 */
	public void setCartList(List<shoppingCartEntitie> cartList)
	{
		this.cartList = cartList;
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
	
	

}
