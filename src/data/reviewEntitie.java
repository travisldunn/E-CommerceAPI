package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@Table(name = "review")
public class reviewEntitie
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id", nullable = false)
	private productsEntitie product_id;
//	@JsonBackReference
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private userEntitie user_id;
	private String review;
	private String rating;
	private String date;
	
	public reviewEntitie()
	{
		super();
	}

	public reviewEntitie(int id, productsEntitie  product_id, userEntitie  user_id, String review, String rating, String date)
	{
		super();
		this.id = id;
		this.product_id = product_id;
		this.user_id = user_id;
		this.review = review;
		this.rating = rating;
		this.date = date;
	}

	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @return the product_id
	 */
	public productsEntitie  getproduct_id()
	{
		return product_id;
	}

	/**
	 * @return the user_id
	 */
	public userEntitie  getuser_id()
	{
		return user_id;
	}

	/**
	 * @return the review
	 */
	public String getReview()
	{
		return review;
	}

	/**
	 * @return the rating
	 */
	public String getRating()
	{
		return rating;
	}

	/**
	 * @return the date
	 */
	public String getDate()
	{
		return date;
	}

	/**
	 * @param product_id the product_id to set
	 */
	public void setproduct_id(productsEntitie product_id)
	{
		this.product_id = product_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setuser_id(userEntitie  user_id)
	{
		this.user_id = user_id;
	}

	/**
	 * @param review the review to set
	 */
	public void setReview(String review)
	{
		this.review = review;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating)
	{
		this.rating = rating;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date)
	{
		this.date = date;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "reviewEntitie [id=" + id + ", product_id=" + product_id + ", user_id=" + user_id + ", review=" + review
				+ ", rating=" + rating + ", date=" + date + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
