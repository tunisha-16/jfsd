package com.klef.jfsd.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.klef.jfsd.springboot.model.Employee;
import com.klef.jfsd.springboot.service.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController {
	  @Autowired
	  private EmployeeService employeeService;
	  
	  
	  @GetMapping("/")
      public ModelAndView home() {
   	   ModelAndView mv=new ModelAndView();
   	   mv.setViewName("home");
   	   return mv;
      }
	  
       @GetMapping("/emphome")
       public ModelAndView emphome() {
    	   ModelAndView mv=new ModelAndView();
    	   mv.setViewName("emphome");
    	   return mv;
       }
       
       
       @GetMapping("/empreg")
       public ModelAndView empreg() {
    	   ModelAndView mv=new ModelAndView();
    	   mv.setViewName("empreg");
    	   return mv;
       }
       
       
       @PostMapping("insertemp")
       public ModelAndView insertemp(HttpServletRequest request)
       {
          String name = request.getParameter("ename");
        String gender = request.getParameter("egender");
        String dob = request.getParameter("edob");
        String dept = request.getParameter("edept");
        double salary = Double.parseDouble(request.getParameter("esalary"));
        String location = request.getParameter("elocation");
        String email = request.getParameter("eemail");
        String password = request.getParameter("epwd");
        String contact = request.getParameter("econtact");
        String status = "Registered";
        
          Employee emp = new Employee();
          emp.setName(name);
          emp.setGender(gender);
          emp.setDepartment(dept);
          emp.setDateofbirth(dob);
          emp.setSalary(salary);
          emp.setLocation(location);
          emp.setEmail(email);
          emp.setPassword(password);
          emp.setContact(contact);
          emp.setStatus(status);
          
          String msg = employeeService.EmployeeRegistration(emp);
          
          ModelAndView mv = new ModelAndView("regsucess");
          mv.addObject("message", msg);
        
          return mv;

       }
       @GetMapping("/emplogin")
       public ModelAndView emplogin() {
    	   ModelAndView mv=new ModelAndView();
    	   mv.setViewName("emplogin");
    	   return mv;
       }
       @GetMapping("/emplogout")
       public ModelAndView emplogout(HttpServletRequest request) {
    	   HttpSession session=request.getSession();
    	   session.removeAttribute("employee");
    	   ModelAndView mv=new ModelAndView();
    	   mv.setViewName("emplogin");
    	   return mv;
       }
       
       @PostMapping("/checkemplogin")
       public ModelAndView checkemplogin(HttpServletRequest request) {
    	   ModelAndView mv=new ModelAndView();
    	   
    	   String eemail=request.getParameter("eemail");
    	   String epwd=request.getParameter("epwd");
    	   
    	   Employee e=employeeService.checkemployeelogin(eemail,epwd);
    	   if(e!=null) {
    		   HttpSession session=request.getSession();
    		   session.setAttribute("employee", e);
    		   
    		   
    		   mv.setViewName("emphome");
    	   }
    	   else {
    		   mv.setViewName("emploginfail");
    		   mv.addObject("message","login failed");
    	   }
    	   return mv;
    	   
       }
       
       
       @GetMapping("/empsessionfail")
       public ModelAndView empsessionfial() {
    	   ModelAndView mv=new ModelAndView();
    	   mv.setViewName("empsessionfail");
    	   
    	   return mv;
       }
       
       @GetMapping("/updateemp")
       public ModelAndView updatemep() {
    	   ModelAndView mv=new ModelAndView();
    	   mv.setViewName("updateemp");
    	   return mv;
       }
       
       @GetMapping("/empprofile")
       public ModelAndView empprofile() {
    	   ModelAndView mv=new ModelAndView();
    	   mv.setViewName("empprofile");
    	   return mv;
       }
       @PostMapping("update")
       public ModelAndView update(HttpServletRequest request)
       {
    	   String id = request.getParameter("eid");
    	   String name = request.getParameter("ename");
           String gender = request.getParameter("egender");
           String dob = request.getParameter("edob");
           String dept = request.getParameter("edept");
           double salary = Double.parseDouble(request.getParameter("esalary"));
           String location = request.getParameter("elocation");
           String email = request.getParameter("eemail");
           String password = request.getParameter("epwd");
           String contact = request.getParameter("econtact");
           String status = "Registered";
           
             Employee emp = new Employee();
             emp.setName(name);
             emp.setGender(gender);
             emp.setDepartment(dept);
             emp.setDateofbirth(dob);
             emp.setSalary(salary);
             emp.setLocation(location);
             emp.setEmail(email);
             emp.setPassword(password);
             emp.setContact(contact);
             emp.setStatus(status);
             String msg = employeeService.EmployeeRegistration(emp);
             
             ModelAndView mv = new ModelAndView("regsucess");
             mv.addObject("message", msg);
           
             
             return mv;
    	   
       }
       
    		   
       
       
       
       public ModelAndView viewempbydept(HttpServletRequest request)
       {
    	   ModelAndView mv = new ModelAndView("viewempbydept");
    	   
    	   HttpSession session = request.getSession();
    	   
    	   Employee emp = (Employee)session.getAttribute("employee");
    	   
    	   List<Employee> emplist= employeeService.displayEmpsByDepartment(emp.getDepartment());
    	   mv.addObject("emplist",emplist);
    	   return mv;
       }
       
       
}
