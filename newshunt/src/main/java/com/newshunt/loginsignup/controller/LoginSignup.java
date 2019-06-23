package com.newshunt.loginsignup.controller;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newshunt.daomodel.Signup;
import java.util.List;
@Controller
public class LoginSignup {

	@RequestMapping("/")
	public  String login() {
		
		return "login";
	
	}
	

@RequestMapping("/login")
@ResponseBody
public  byte signup(@RequestBody Signup rec)
{
		try
		{
			byte flag=0;
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory session= cfg.buildSessionFactory();
	        Session ss = session.openSession();
	        Criteria cr= ss.createCriteria(Signup.class);
	        List<Signup>p=cr.list();
	        for(Signup m:p)
	        {
	        	if(m.getEmail().equals(rec.getEmail())&& m.getPassword().equals(rec.getPassword()))
	        	{
	        		flag=1;
	        	}
	        }
	        		
			return flag;
		}
	 catch(Exception e) 
	{
		  
		  e.printStackTrace();
		  
		  
	}
		return 0;
}
	
	
	@RequestMapping("/signup")
	@ResponseBody
	public  byte saveUserInfo(@RequestBody  Signup rec) {
		
		  try
		  {
			     Configuration cfg = new Configuration();
				 cfg.configure("hibernate.cfg.xml");
				 SessionFactory session = cfg.buildSessionFactory();
	             Session s = session.openSession();
	             s.save(rec);
			     s.beginTransaction().commit();
			    return 1; 
		  }
		  catch(Exception e) {
			  
		  e.printStackTrace();
		  
		  }
		  return 0;
		
	
	}
	
	@RequestMapping("/home")
	public  String home() {
		
		return "home";
	
	}
	
	
}
