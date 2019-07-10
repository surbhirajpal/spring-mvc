package com.newshunt.channelmanagement.controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newshunt.daomodel.AddingChannel;
import com.newshunt.daomodel.Signup;
import com.newshunt.daomodel.feedbackDao;
import com.newshunt.dto.ChannelNews;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Controller
public class ChannelManagement
{
	@RequestMapping("/add_channels")
	public  String login() {
		
		return "addingChannels";
	
	}
	
	
	@RequestMapping(value="/channelList" ,method = RequestMethod.POST)
	@ResponseBody
	public List<AddingChannel> addChannels(HttpSession session)
	{
		String email=session.getAttribute("un").toString();
		Configuration cfg = new Configuration();
		   cfg.configure("hibernate.cfg.xml");
		   SessionFactory sf = cfg.buildSessionFactory();
	       Session ss = sf.openSession();
	       Criteria cc = ss.createCriteria(Signup.class);
	       cc.add(Restrictions.eq("email",email));
	       Signup UA=(Signup)cc.uniqueResult();
			String channel = UA.getMychannel();
			if(channel != null)
			{
				String m[]=channel.split(",");
				ArrayList<Integer>al=new ArrayList();
				for(String z:m)
				{
					al.add(Integer.parseInt(z));

				}
				cc=ss.createCriteria(AddingChannel.class);
				cc.add(Restrictions.not(Restrictions.in("id",al)));
				List<AddingChannel>suscribeChannel= cc.list();
				if(suscribeChannel.isEmpty()!=true)
				{
					return suscribeChannel;
				}
				else
				{
					return null;
				}

				
			}
			else
			{
				cc=ss.createCriteria(AddingChannel.class);
				List<AddingChannel>suscribeChannel=cc.list();
				if(suscribeChannel.isEmpty()!=true)
				{
					return suscribeChannel;
					
				}
				else
				{
					return null;
				}
				
			}  
	 }
	
	@RequestMapping(value="/suscribe", method = RequestMethod.POST)
	@ResponseBody
	public void suscribe(@RequestBody Signup r , HttpSession session)
	{
    	try
    	{
    	   String un = session.getAttribute("un").toString();
    	   Configuration cfg= new Configuration();
	       cfg.configure("hibernate.cfg.xml");
	       SessionFactory sf = cfg.buildSessionFactory();
	       Session ss = sf.openSession();
	       Criteria criteria = ss.createCriteria(Signup.class);
	       criteria.add(Restrictions.eq("email", un));
           Signup rec = (Signup)criteria.uniqueResult();	       
	       String z=rec.getMychannel();
	       if(z!=null)
	       {
	    	   String x=r.getMychannel();
	    	   String n=z.concat(x);
	    	   rec.setMychannel(n);
	       }
	       else
	       {
	    	   rec.setMychannel(r.getMychannel());
	       }
	       ss.update(rec);
	       ss.beginTransaction().commit();
	      
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
   }
	
	@RequestMapping(value="/unsuscribe", method = RequestMethod.POST)
	@ResponseBody
	public void unsuscribe(@RequestBody Signup r , HttpSession session)
	{
    	try
    	{
    	String un = session.getAttribute("un").toString();
    		Signup p = new Signup();
		   p.setMychannel(r.getMychannel());
		   Configuration cfg= new Configuration();
	       cfg.configure("hibernate.cfg.xml");
	       SessionFactory sf = cfg.buildSessionFactory();
	       Session ss = sf.openSession();
	       Criteria criteria = ss.createCriteria(Signup.class);
	       criteria.add(Restrictions.eq("email", un));
           Signup rec = (Signup)criteria.uniqueResult();	       
	       rec.setMychannel(r.getMychannel());
	       ss.update(rec);
	       ss.beginTransaction().commit();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
   }
	
	
	
	
	@RequestMapping("/my_channels")
	public  String myChannels() 
	{
		
		return "myChannels";
	
	}
	
	@RequestMapping(value="/myChannelList",method = RequestMethod.POST)
	@ResponseBody
	public List<Signup>MyChannel(HttpSession session)
	{
		String email=(String) session.getAttribute("un");
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Criteria cc= ss.createCriteria(Signup.class);
		cc.add(Restrictions.eq("email",email));
		Signup UA=(Signup)cc.uniqueResult();
		String channel = UA.getMychannel();
		String m[]=channel.split(",");
		ArrayList<Integer>al=new ArrayList();
		for(String z:m)
		{
			al.add(Integer.parseInt(z));

		}
		cc=ss.createCriteria(AddingChannel.class);
		cc.add(Restrictions.in("id",al));
		List<Signup>suscribeChannel= cc.list();
		return suscribeChannel;

	}
		/*
		if(channel != null)
		{
			String m[]=channel.split(",");
			ArrayList<Integer>al=new ArrayList();
			for(String z:m)
			{
				al.add(Integer.parseInt(z));

			}
			cc=ss.createCriteria(AddingChannel.class);
			cc.add(Restrictions.not(Restrictions.in("id",al)));
			List<Signup>suscribeChannel= cc.list();
			if(suscribeChannel.isEmpty()!=true)
			{
				return suscribeChannel;
			}
			else
			{
				return null;
			}

			
		}
		else
		{
			cc=ss.createCriteria(AddingChannel.class);
			List<Signup>suscribeChannel=cc.list();
			if(suscribeChannel.isEmpty()!=true)
			{
				return suscribeChannel;
				
			}
			else
			{
				return null;
			}
			
		}
	}
		*/
		
		/*
		String m[]=channel.split(",");
		ArrayList<Integer>al=new ArrayList();
		for(String z:m)
		{
			al.add(Integer.parseInt(z));

		}
		cc=ss.createCriteria(AddingChannel.class);
		cc.add(Restrictions.in("id",al));
		List<Signup>suscribeChannel= cc.list();
		return suscribeChannel;

*/		
		
				
	
	@RequestMapping(value="/addFavourities",method=RequestMethod.POST)
	@ResponseBody
	public void addFavourities(@RequestBody Signup r , HttpSession session)
	{
    	try
    	{
    	   String email=(String) session.getAttribute("un");
    	   Signup p = new Signup();
		   p.setFavourities(r.getFavourities());
		   Configuration cfg= new Configuration();
	       cfg.configure("hibernate.cfg.xml");
	       SessionFactory sf = cfg.buildSessionFactory();
	       Session ss = sf.openSession();
	       Criteria criteria = ss.createCriteria(Signup.class);
	       criteria.add(Restrictions.eq("email", email));
	       Signup rec = (Signup)criteria.uniqueResult();	       
	       String z=rec.getFavourities();
	       if(z!=null)
	       {
	    	   String x=r.getFavourities();
	    	   String n=z.concat(x);
	    	   rec.setFavourities(n);
	       }
	       else
	       {
	    	   rec.setFavourities(r.getFavourities());
	       }
	       ss.update(rec);
	       ss.beginTransaction().commit();
	      
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
	       
	       
	       
	       
	       
	       /*
	       
           Signup rec = (Signup)criteria.uniqueResult();	       
	       rec.setFavourities(r.getFavourities());
	       ss.update(rec);
	       ss.beginTransaction().commit();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
   }
   */
	
	@RequestMapping("/favourities")
	public  String favourities() {
		
		return "MyFavourities";
	
	}
	
	@RequestMapping(value = "/displayFavourities" , method = RequestMethod.POST)
	@ResponseBody
	public List<ChannelNews> news(HttpSession session) 
	{
		String uname = session.getAttribute("un").toString();
		Configuration cfg= new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss = sf.openSession();
		Criteria criteria = ss.createCriteria(Signup.class);
		criteria.add(Restrictions.eq("email", uname));
		Signup rec = (Signup)criteria.uniqueResult();	       
		String channel=rec.getFavourities();
		String m[]=channel.split(",");
		ArrayList<Integer>al=new ArrayList();
		for(String z:m)
		{
			al.add(Integer.parseInt(z));
		}
		Criteria cc = ss.createCriteria(AddingChannel.class);
		cc.add(Restrictions.in("id", al));
		List<AddingChannel>sy=cc.list();		
		try
		{
			LinkedList<ChannelNews> cn = new LinkedList<ChannelNews>(); 
			
			for(AddingChannel c:sy)
			{
				URL url = new URL(c.getUrl());
				SyndFeedInput sz = new SyndFeedInput();
				SyndFeed ff = sz.build(new XmlReader(url));
				List list = ff.getEntries();
				for(int j=0;j<3;j++)
				{
					ChannelNews sc = new ChannelNews(); 
					String title = ((SyndEntry) list.get(j)).getTitle();
					sc.setTitle(title);
					String link = ((SyndEntry) list.get(j)).getLink();
					sc.setLink(link);
					Date D = ((SyndEntry) list.get(j)).getPublishedDate();
					Date date = Calendar.getInstance().getTime();  
					DateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");  
					String strDate = dateFormat.format(date);  
					sc.setDate(strDate);
					sc.setChannelName(c.getTitle());
					cn.add(sc);
				}
				
			}
			return cn;
		}
		catch(Exception e)
		{
			
		}		
		return null;
	}		
	
	
	@RequestMapping("/my_info")
	public  String myInfo() 
	{
		
		return "myInfo";
	
	}
	
	
	@RequestMapping(value="/fetchMyInfo",method=RequestMethod.POST)
	@ResponseBody
	public Signup accInfo(HttpSession hs) 
	{
		String email=hs.getAttribute("un").toString();
		Configuration cfg= new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory session=cfg.buildSessionFactory();
		Session s= session.openSession();
		Criteria cr=s.createCriteria(Signup.class);
		cr.add(Restrictions.eq("email",email));
		Signup rec=(Signup)cr.uniqueResult();
		return rec;
	}
	
	@RequestMapping("/UpdateMyInfo")
	@ResponseBody
	public  byte UpdateMyInfo(@RequestBody  Signup rec,HttpSession session) {
		byte flag = 0;
		  try
		  {
			String email=(String) session.getAttribute("un");
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf = cfg.buildSessionFactory();
			Session ss = sf.openSession();
			Criteria criteria = ss.createCriteria(Signup.class);
			criteria.add(Restrictions.eq("email", email));
			Signup r=(Signup)criteria.uniqueResult();
			r.setName(rec.getName());
			r.setPassword(rec.getPassword());
			r.setPhone(rec.getPhone());
			ss.beginTransaction().commit();
			flag=1;
		  }
		  catch(Exception e) {
			  
		  e.printStackTrace();
		  
		  }
		  return flag;
		
	
	}
	
	@RequestMapping("/feedback")
	public  String feedback() 
	{
		
		return "FeedbackForm";
	
	}
	
	@RequestMapping(value="/addFeedback",method=RequestMethod.POST)
	@ResponseBody
	public List<feedbackDao>ins(@RequestBody feedbackDao r)
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss= sf.openSession();
		ss.save(r);
		ss.beginTransaction().commit();
		Criteria cr=ss.createCriteria(feedbackDao.class);
		List<feedbackDao>q=cr.list();
		return q;
		
		
	}
	

	
	
	
}
	


	


