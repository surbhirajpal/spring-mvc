package com.newshunt.livenews.contoller;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newshunt.daomodel.AddingChannel;
import com.newshunt.daomodel.Signup;
import com.newshunt.dto.ChannelNews;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;


@Controller
public class News
{
	@RequestMapping(value = "/news" , method = RequestMethod.POST)
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
		String channel=rec.getMychannel();
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
}
