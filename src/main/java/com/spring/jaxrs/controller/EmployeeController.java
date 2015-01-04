package com.spring.jaxrs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

import com.spring.jaxrs.EmpRestURIConstants;
import com.spring.jaxrs.entity.Employee;
import com.spring.jaxrs.service.EmployeeService;


@Controller
public class EmployeeController {

	@Autowired
	public EmployeeService empService;
	
    

	@RequestMapping(value = EmpRestURIConstants.DUMMY_EMP, method = RequestMethod.GET)
	public @ResponseBody Employee getDummyEmployee() {
		
		Employee emp = new Employee();
		emp.setName("Tarun-Sharma");
		emp.setEmail("cool@mail.com");
		emp.setPassword("admin");
		emp.setEnabled(true);
		empService.save(emp);
		return emp;
	}

	@RequestMapping(value = EmpRestURIConstants.GET_EMP, method = RequestMethod.GET)
	public @ResponseBody Employee getEmployee(@PathVariable("id") int empId) {

		return empService.getEmpDate(empId);
	}


	@RequestMapping(value = EmpRestURIConstants.CREATE_EMP, method = RequestMethod.GET)
	public @ResponseBody Employee createEmployee(@RequestBody Employee emp) {

		emp.setEmail("tarun.softengg@gmail.com");
		emp.setEnabled(true);
		emp.setMobNumber("9716144930");
		emp.setPassword("admin_password");
		emp.setName("Myemployee");
		empService.save(emp);
		return emp;
	}

	@RequestMapping(value = EmpRestURIConstants.DELETE_EMP, method = RequestMethod.PUT)
	public @ResponseBody Employee deleteEmployee(@PathVariable("id") int empId) {
		Employee emp = empService.getEmpDate(empId);
		empService.Delete(empId);
		return emp;
	}
	
	@RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
    public String getEmployee(@PathVariable("id") int id, Model model) throws Exception{
        //deliberately throwing different types of exception
        if(id==1){
            throw new EmployeeNotFoundException(id);
        }else if(id==2){
            throw new SQLException("SQLException, id="+id);
        }else if(id==3){
            throw new IOException("IOException, id="+id);
        }else if(id==10){
            Employee emp = new Employee();
            emp.setName("Pankaj");
            emp.setId(id);
            model.addAttribute("employee", emp);
            return "home";
        }else {
            throw new Exception("Generic Exception, id="+id);
        }
         
    }
     
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ModelAndView handleEmployeeNotFoundException(HttpServletRequest request, Exception ex){
        
         
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("url", request.getRequestURL());
         
        modelAndView.setViewName("error");
        return modelAndView;
    } 

}
