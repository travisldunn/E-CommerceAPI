package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import data.EmployeesDAO;
import data.employeesEntitie;

@Controller
public class EmployeesController {

	@Autowired
	private EmployeesDAO employeesdao;

	@ResponseBody
	@RequestMapping(path = "employee", method = RequestMethod.GET)
	public List<employeesEntitie> getAllemployees() {
		System.out.println("in all employees route");
		return employeesdao.getAllEmployees();
	}
	
}
