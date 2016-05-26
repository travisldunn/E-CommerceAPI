package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="employees")

@NamedQueries({ @NamedQuery(name = "getAllEmployees", query = "select e from employeesEntitie e")})
public class employeesEntitie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String title;
	private String email;
	private String bio;
	private String pictures;
	
	
	public employeesEntitie(){}
	
	
	public employeesEntitie(int id, String name, String title, String email, String bio) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.email = email;
		this.bio = bio;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public int getId() {
		return id;
	}


	public String getImage()
	{
		return pictures;
	}


	public void setImage(String image)
	{
		this.pictures = image;
	}
	
}
