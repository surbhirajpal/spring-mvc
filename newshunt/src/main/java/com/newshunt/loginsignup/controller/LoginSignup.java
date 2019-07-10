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

import javax.servlet.http.HttpSession;
@Controller
public class LoginSignup {

	@RequestMapping("/")
	public  String login() {
		
		return "login";
	
	}
	

@RequestMapping("/login")
@ResponseBody
public int signup(@RequestBody Signup r,HttpSession session)
{
		try
		{
			int flag=0;
			String un ="";
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf= cfg.buildSessionFactory();
	        Session ss = sf.openSession();
	        Criteria cr= ss.createCriteria(Signup.class);
	        List<Signup>z=cr.list();
	        for(Signup m:z)
	        {
	        	if(r.getEmail().equals(m.getEmail())&& r.getPassword().equals(m.getPassword()))
	        	{
	        		un = r.getEmail();
	        		flag=1;
	        	}
	        	else if(r.getEmail().equals("admin@gmail.com")&& r.getPassword().equals("123"))
	        	{
	        		un = r.getEmail();
	        		flag=2;
	        	}
	        }
	        session .setAttribute("un", un);		
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
