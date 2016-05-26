package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class LoginDAO
{
	@PersistenceContext
	EntityManager em;

	public List<userEntitie> getAllUsers()
	{
		System.out.println("inside DAO getAllUsers");
		List<userEntitie> userList = (List<userEntitie>) em.createNamedQuery("getALL").getResultList();
		return userList;
	}

	public userEntitie getUserById(String id)
	{
		System.out.println("inside DAO Id");
		try
		{
			userEntitie user = (userEntitie) em.createNamedQuery("getUserById").setParameter("id", id).getSingleResult();
			return user;
		} catch (Exception e)
		{
			return null;
		}
	}

	public userEntitie getUserByEmail(String email)
	{
		System.out.println("inside DAO to get user by Email for login");
		try
		{
			System.out.println("email is  " + email);
			userEntitie user = (userEntitie) em.createNamedQuery("getUserByEmail").setParameter("email", email)
					.getSingleResult();
			System.out.println(user);
			return user;
			
		} catch (Exception e)
		{
			System.out.println(e + "user not found");
			return null;
		}

	}

	public userEntitie getUserByPassword(String password)
	{
		System.out.println("inside DAO Password");
		userEntitie user = (userEntitie) em.createNamedQuery("getUserByPassword").setParameter("password", password)
				.getSingleResult();
		return user;
	}

	public addressEntitie createUser(userEntitie newUser)
	{
		System.out.println("in create user DAO");
		System.out.println(newUser.getAddress().getType());
		System.out.println(newUser.getAddress().getAddress());
		List<Object> list = new ArrayList<>();
		userEntitie checkUser;
		try
		{
			checkUser = (userEntitie) em.createNamedQuery("getUserByEmail").setParameter("email", newUser.getEmail())
					.getSingleResult();
			if (!checkUser.getEmail().toLowerCase().equals(newUser.getEmail().toLowerCase()))
			{
				System.out.println("in else");
				addressEntitie aE = new addressEntitie();
				aE.setAddress(newUser.getAddress().getAddress());
				aE.setType(newUser.getAddress().getType());
				newUser.setAddress(null);
				System.out.println(newUser.getAddress());
				System.out.println("inside if statemet in createUSer method");				
				em.persist(newUser);
				aE.setuser_id(newUser);
				list.add(newUser);
				
				em.persist(aE);
				System.out.println("persisted User");
				return aE;
			} else
			{
				newUser.setName("Username already exists");
				list.add(newUser);
				return null;
			}
		} catch (Exception e)
		{
			System.out.println("in catch");
			addressEntitie aE = new addressEntitie();
			aE.setAddress(newUser.getAddress().getAddress());
			aE.setType(newUser.getAddress().getType());
			newUser.setAddress(null);
			
			em.persist(newUser);
			aE.setuser_id(newUser);
			em.persist(aE);
			em.merge(newUser);
			//list.add(newUser);
			//list.add(aE);
			System.out.println(newUser.getAddress());
			System.out.println(e);
			System.out.println(list);
			System.out.println(newUser.getId());
			
			return aE;
		}
	}

	public addressEntitie login(userEntitie json)
	{
		// ObjectMapper mapper = new ObjectMapper();
		// userEntitie user;
		try
		{
			// json = mapper.readValue(json, userEntitie.class);
			String email = json.getEmail();
			String password = json.getPassword();

			userEntitie checkUser = getUserByEmail(email);
			if (checkUser == null)
			{
				userEntitie tempUser = new userEntitie();
				tempUser.setName("Username/password is incorrect");
				return tempUser.getAddress();
			}
			if (validateEmail(checkUser, email) && validatePassword(checkUser, password))
			{

				return checkUser.getAddress();
			} else if (validateEmail(checkUser, email))
			{
				userEntitie tempUser = new userEntitie();
				tempUser.setName("Invalid username");
				return tempUser.getAddress();
			} else if (validatePassword(checkUser, password))
			{
				userEntitie tempUser = new userEntitie();
				tempUser.setName("Invalid password");
				return tempUser.getAddress();
			} else
			{
				userEntitie tempUser = new userEntitie();
				tempUser.setName("Username/password is incorrect");
				return tempUser.getAddress();
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			userEntitie tempUser = new userEntitie();
			tempUser.setName("Catch error");
			return tempUser.getAddress();
		}
	}

	public userEntitie editUser(String user)
	{
		ObjectMapper om = new ObjectMapper();
		int userId = Integer.parseInt(user.split(":")[1].split(",")[0]);
		System.out.println(userId);
		userEntitie changes = null;
		userEntitie userDB = em.find(userEntitie.class, userId);
		try
		{
			System.out.println(user);
			changes = om.readValue(user, userEntitie.class);
			if (changes.getEmail() != null)
			{
				userDB.setEmail(changes.getEmail());
			}
			if (changes.getName() != null)
			{
				userDB.setName(changes.getName());
			}
			if (changes.getPassword() != null)
			{
				userDB.setPassword(changes.getPassword());
			}

		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		em.merge(userDB);
		return userDB;
	}

	public String deleteUser(String user)
	{
		String id =  user.split(",")[2].split(":")[1];
		userEntitie ue = (userEntitie) em.createNamedQuery("getUserById").setParameter("id", id).getSingleResult();;
		System.out.println(ue);
		em.remove(ue);
		return "";
		//userEntitie check = em.find(userEntitie.class, user.getId());
//		if (check == null)
//		{
//			return "delete account successful";
//		} else
//		{
//			return "delete account failed";
//		}

	}

	public boolean validateEmail(userEntitie checkUser, String email)
	{

		if (checkUser.getEmail().equals(email))
			return true;
		else
			return false;
	}

	public boolean validatePassword(userEntitie checkUser, String password)
	{
		if (checkUser.getPassword().equals(password))
			return true;
		else
			return false;
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
}