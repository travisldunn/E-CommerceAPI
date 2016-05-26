package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import data.LoginDAO;
import data.addressEntitie;
import data.userEntitie;



@Controller
public class LoginController {
	@Autowired 
	private LoginDAO loginDao;
		
	@ResponseBody
	@RequestMapping("user")
	public List<userEntitie> getUser() {
		System.out.println("inside get user rout");
		return loginDao.getAllUsers();
	}
	
	@ResponseBody
	@RequestMapping(path = "createUser", method = RequestMethod.POST)
	public addressEntitie createUser(@RequestBody userEntitie newUser)
	{
		System.out.println("in create user Controller");
		return loginDao.createUser(newUser);
	}
	
	@ResponseBody
	@RequestMapping(path = "login", method = RequestMethod.POST)
	public addressEntitie login(@RequestBody userEntitie json)
	{
		return loginDao.login(json);
	}
	@ResponseBody
	@RequestMapping(path = "editUser", method = RequestMethod.POST)
	public userEntitie editUser(@RequestBody String json)
	{
		return loginDao.editUser(json);
	}
	@ResponseBody
	@RequestMapping(path = "deleteUser", method = RequestMethod.POST)
	public void deleteUser(@RequestBody String json)
	{
		System.out.println("user id to delete" + json);
		
	}
	
}
