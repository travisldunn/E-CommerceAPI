package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.JoinFetch;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name = "users")
@NamedQueries({ @NamedQuery(name = "getALL", query = "select u from userEntitie u"),
		@NamedQuery(name = "getUserById", query = "select u from userEntitie u where u.id = :id"),
		@NamedQuery(name = "getUserByEmail", query = "select u from userEntitie u where u.email = :email"),
		@NamedQuery(name = "getUserByPassword", query = "select u from userEntitie u where u.password = :password") })

public class userEntitie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
//	@JsonManagedReference
	@OneToOne(mappedBy = "user_id", cascade = CascadeType.ALL)
	@JsonBackReference(value="usertoaddress")
	private addressEntitie address;
	@JsonBackReference(value ="UserShoppingCart")
	@OneToOne(mappedBy = "user_id", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private shoppingCartEntitie cart;
//	@JsonManagedReference
	@OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL)
	private List<reviewEntitie> review = new ArrayList<>();

	public userEntitie() {
	}

	public userEntitie(int id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public addressEntitie getAddress() {
		return address;
	}

	public void setAddress(addressEntitie address) {
		this.address = address;
	}

	public shoppingCartEntitie getCart()
	{
		return cart;
	}

	public void setCart(shoppingCartEntitie cart)
	{
		this.cart = cart;
	}

	public List<reviewEntitie> getReview() {
		return review;
	}

	public void setReview(List<reviewEntitie> review) {
		this.review = review;
	}
//
//
//	public void addAddress(addressEntitie address) {
//		if (!getAddress().contains(address)) {
//			getAddress().add(address);
//		}
//	}
//
//	public void removeAddress(addressEntitie address) {
//		if (getAddress().contains(address)) {
//			getAddress().remove(address);
//
//		}
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "userEntitie [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

}
