package com.newshunt.admin.menu.controller;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newshunt.daomodel.AdminMenuDao;


@Controller
public class AdminMenu 

{
	@RequestMapping(value="/adminHome")
	public String home()
	{
		return "AdminHome";
	}

	@RequestMapping(value = "/AdminMenuList" , method = RequestMethod.POST)
    @ResponseBody
	public List<AdminMenuDao>menuList()
	{
	   Configuration cfg = new Configuration();
	   cfg.configure("hibernate.cfg.xml");
	   SessionFactory sf = cfg.buildSessionFactory();
       Session ss = sf.openSession();
       Criteria cr = ss.createCriteria(AdminMenuDao.class);
       List<AdminMenuDao>p=cr.list();
	   if(p.isEmpty()!=true) 
	   {
		    return p;  
	   }
	   
	 return null;  
   }



}
