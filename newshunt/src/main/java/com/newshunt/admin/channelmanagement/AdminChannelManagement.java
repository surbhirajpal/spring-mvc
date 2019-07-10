package com.newshunt.admin.channelmanagement;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newshunt.daomodel.AddingChannel;
import com.newshunt.daomodel.MenuDao;
import com.newshunt.daomodel.Signup;
import com.newshunt.daomodel.feedbackDao;

@Controller
public class AdminChannelManagement 
{
	@RequestMapping(value="/addChannel")
	public String addChannel()
	{
		return "AdminAddChannel";
	}
	
	@RequestMapping(value="/channelList")
	public String channelList()
	{
		return "AdminChannelList";
	}
	
	@RequestMapping(value="/addMenu")
	public String addMenu()
	{
		return "AdminAddMenu";
	}
	
	@RequestMapping(value="/menuList")
	public String menuListDisplay()
	{
		return "DisplayAdminMenuList";
	}
	
	@RequestMapping(value="/userList")
	public String userList()
	{
		return "AdminUserList";
	}
	
	@RequestMapping(value="/adminFeedback")
	public String feedback()
	{
		return "DisplayAdminFeedback";
	}
	
	@RequestMapping(value="/AdminAddMenuList",method=RequestMethod.POST)
	@ResponseBody
	public List<MenuDao>ins(@RequestBody MenuDao r)
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss= sf.openSession();
		ss.save(r);
		ss.beginTransaction().commit();
		Criteria cr=ss.createCriteria(MenuDao.class);
		List<MenuDao>q=cr.list();
		return q;
		
		
	}
	
	@RequestMapping(value="/AdminAddChannelList",method=RequestMethod.POST)
	@ResponseBody
	public List<AddingChannel>insChannel(@RequestBody AddingChannel r)
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session ss= sf.openSession();
		ss.save(r);
		ss.beginTransaction().commit();
		Criteria cr=ss.createCriteria(AddingChannel.class);
		List<AddingChannel>q=cr.list();
		return q;
		
		
	}
	
	@RequestMapping(value="/FetchMenuList",method=RequestMethod.POST)
	@ResponseBody
	public List<MenuDao>displayMenuList()
	{
		Configuration cfg= new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(MenuDao.class);
		List<MenuDao>p=cr.list();
		return p;
		
	}
	
	@RequestMapping(value="/deleteMenu",method=RequestMethod.POST)
	@ResponseBody
	public void delete(@RequestBody MenuDao r)
	{
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf= cfg.buildSessionFactory();
		Session ss=sf.openSession();
		ss.delete(r);
		ss.beginTransaction().commit();
		
	}
	
	@RequestMapping(value="/updateMenu",method=RequestMethod.POST)
	@ResponseBody
	public void updateMenu(@RequestBody MenuDao r)
	{
		
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf= cfg.buildSessionFactory();
		Session ss=sf.openSession();
		ss.update(r);
		ss.beginTransaction().commit(); 
		
		
		
	}
	
	@RequestMapping(value="/FetchChannelList",method=RequestMethod.POST)
	@ResponseBody
	public List<AddingChannel>displayChannelList()
	{
		Configuration cfg= new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(AddingChannel.class);
		List<AddingChannel>p=cr.list();
		return p;
		
	}
	
	@RequestMapping(value="/updateChannel",method=RequestMethod.POST)
	@ResponseBody
	public void updateChannel(@RequestBody AddingChannel r)
	{
		
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf= cfg.buildSessionFactory();
		Session ss=sf.openSession();
		ss.update(r);
		ss.beginTransaction().commit(); 
		
		
		
	}
	
	@RequestMapping(value="/deleteChannel",method=RequestMethod.POST)
	@ResponseBody
	public void deleteChannel(@RequestBody AddingChannel rec)
	{
 		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf= cfg.buildSessionFactory();
		Session ss=sf.openSession();
		Criteria criteria=ss.createCriteria(Signup.class);
		List<Signup>info=criteria.list();
		for(Signup w : info)
		{
			String updateChannel="";
			if(w.getMychannel()!=null) {
				if(w.getMychannel().contains(rec.getId()+""))
				{
					String m[]=w.getMychannel().split(",");
					for(String v:m)
					{
						if(v.equals(rec.getId()+""))
						{
							continue;
						}
						else
						{
							updateChannel=updateChannel+v+",";
						}
					}
					w.setMychannel(updateChannel);
					ss.update(w);
		   		}
			}
			}
		ss.delete(rec);
		ss.beginTransaction().commit();
				
	}
	
	@RequestMapping(value="/FetchFeedback",method=RequestMethod.POST)
	@ResponseBody
	public List<feedbackDao>displayFeedback()
	{
		Configuration cfg= new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(feedbackDao.class);
		List<feedbackDao>p=cr.list();
		return p;
		
	}
	
	@RequestMapping(value="/deleteFeedback",method=RequestMethod.POST)
	@ResponseBody
	public void deleteFeedback(@RequestBody feedbackDao r)
	{
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf= cfg.buildSessionFactory();
		Session ss=sf.openSession();
		ss.delete(r);
		ss.beginTransaction().commit();
		
	}
	
	@RequestMapping(value="/FetchUserList",method=RequestMethod.POST)
	@ResponseBody
	public List<Signup>displayUserList()
	{
		Configuration cfg= new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(Signup.class);
		List<Signup>p=cr.list();
		return p;
		
	}
	
	@RequestMapping(value="/updateUserInfo",method=RequestMethod.POST)
	@ResponseBody
	public void updateUserInfo(@RequestBody Signup r)
	{
		
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf= cfg.buildSessionFactory();
		Session ss=sf.openSession();
		ss.update(r);
		ss.beginTransaction().commit(); 
		
		
		
	}
	
	@RequestMapping(value="/deleteUser",method=RequestMethod.POST)
	@ResponseBody
	public void deleteUser(@RequestBody Signup r)
	{
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf= cfg.buildSessionFactory();
		Session ss=sf.openSession();
		ss.delete(r);
		ss.beginTransaction().commit();
		
	}
	
	@RequestMapping(value="/userListCount",method=RequestMethod.POST)
	@ResponseBody
	public int userListcount()
	{
		int count=0;
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf= cfg.buildSessionFactory();
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(Signup.class);
		List<Signup> p=cr.list();
		for(Signup x:p)
		{
			count++;
		}
		return count;
	}
		
	
	@RequestMapping(value="/channelListCount",method=RequestMethod.POST)
	@ResponseBody
	public int channelListcount()
	{
		int count=0;
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf= cfg.buildSessionFactory();
		Session ss=sf.openSession();
		Criteria cr=ss.createCriteria(AddingChannel.class);
		List<AddingChannel> p=cr.list();
		for(AddingChannel x:p)
		{
			count++;
		}
		return count;
	}
	
	
	@RequestMapping(value="/subscribedListCount",method=RequestMethod.POST)
	@ResponseBody
	public int subscribedListcount(HttpSession ss)
	{
		int count=0;
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf= cfg.buildSessionFactory();
		Session s=sf.openSession();
		Criteria cr=s.createCriteria(Signup.class);
		List<Signup> rec=cr.list();
		HashSet<String> al= new HashSet();
		for(Signup x:rec)
		{
			if(x.getMychannel()!=null)
			{
				String r=x.getMychannel();
				String m[]=r.split(",");
				
				for(String z : m)
				{
					al.add(z);
				}
			
			}
			
		}
		int n= al.size();
		return n;
	}
	



	

	

	
	

}
