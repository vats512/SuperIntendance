package com.elitecore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.elitecore.dao.querydao;
import com.elitecore.dao.schedulerdao;
import com.elitecore.dto.*;
import com.elitecore.model.Query;
import com.elitecore.model.SearchKeyWord;
import com.elitecore.model.User;
import com.elitecore.model.queryin;
import com.elitecore.services.queryservices;
import com.elitecore.services.transfer;
import com.elitecore.services.userservices;
import com.elitecore.services.validator;
@Controller
@SessionAttributes("test")

public class operationcontroller {

private userservices userService;
//private queryservices queryservices;
@Autowired
queryservices services;
@Autowired
schedulerdao sch;
	public operationcontroller(userservices userService) { 
		this.userService = userService;
	}
	public operationcontroller() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value="/hello.html")
	public ModelAndView hello()
	{
			return new ModelAndView("form");
	}
	
	@RequestMapping(value="/set_url.html", method = RequestMethod.GET)
	public ModelAndView setsession(@RequestParam("url") String url, HttpSession session)
	{
		session.setAttribute("url",url);
		return new ModelAndView("form","userdto",new userdto());
	}
	
	@RequestMapping(value="/save.html", method = RequestMethod.POST)
	public String save(@RequestParam("name1") String username,@RequestParam("pword1") String password,@RequestParam("contact1") String contact) throws Exception 
	{
		userdto userdto=new userdto();
		userdto.setUsername(username);
		userdto.setPassword(password);
		userdto.setPhone_no(contact);
		System.out.println("Hakuna Matata Null Pointer Exception");
		System.out.println(userdto.getUsername()+"---"+userdto.getPassword());
		validator valid=new validator();
		if(valid.validate_uname(userdto.getUsername()) && valid.validate_pw(userdto.getPassword()))
		{
				User user=transfer.trans(userdto);
				System.out.println("Hakuna Matata"+user.getUserName()+" "+user.getPassword());
				userService.addUser(user);
				return "redirect:success_reg.html";
		}
		else
		{
			return "redirect:errorpage.html";
		}

}
	@RequestMapping(value="/get_page_2.html")
	public ModelAndView hello5(@RequestParam("query") String temp, @RequestParam("name") String name,@RequestParam("db_id") String db_id1)
	{	
		int a=temp.indexOf("^");
		int query_id=Integer.parseInt(temp.substring(0,a));
		
		String disp_name=temp.substring(a+1);
		Reportdto r1=new Reportdto();
		r1.setReport_name(name);
		r1.setQuery_id(query_id);
		
		int db_id= Integer.parseInt(db_id1);
		r1.setDb_id(db_id);
		
		int b=disp_name.indexOf("from");
		if(b==-1)
		{
			b=disp_name.indexOf("FROM");
		}
		if(b==-1)
		{
			b=disp_name.indexOf("From");
		}

		String df=disp_name.substring(7,b-1);

		ModelAndView model=new ModelAndView();
		model.addObject("Reportdto",r1);
		model.addObject("list",df);
		model.setViewName("report_conf2");
		return model;
	}
	
	@RequestMapping(value="/success_reg.html", method = RequestMethod.GET)
	public ModelAndView save1(@ModelAttribute userdto  userdto)
	{
		
				return new ModelAndView("profile","test","hello");
		
	}
	@RequestMapping(value="/errorpage.html",method=RequestMethod.GET)
	public ModelAndView err()
	{
		return new ModelAndView("errorpage");
	}
	@RequestMapping(value="/login.html")
	public String login(@RequestParam("username") String username,@RequestParam("password") String password, HttpSession session) throws Exception
	{
		userdto userdto= new userdto();
		userdto.setUsername(username);
		userdto.setPassword(password);
		User user=transfer.trans(userdto);
		
		System.out.println("Hakuna Matata"+user.getUserName()+" --- "+user.getPassword());
		String pass=user.getPassword();
		username=user.getUserName();
		System.out.println(userService.checklogin(username,pass));
		String m=userService.checklogin(username,pass);
		System.out.println(m);
		if(m.equalsIgnoreCase("success"))
		{
			session.setAttribute("user", user);
			session.setAttribute("url", "viewquery.html?page=1");
			return "redirect:profile.html";
		}
		else
		{
			return "redirect:errorpage.html;";
		}
	}
	
	@RequestMapping(value="/profile.html")
	public ModelAndView profile(HttpSession session) throws Exception
	{
		try {
			sch.scheduler1();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		User user = (User) session.getAttribute("user");
		if(user == null || user.equals(""))
		{
			return new ModelAndView("form","userdto",new userdto());
		}
		else
		{
			return new ModelAndView("gentella","key",user.getUserName());
		}
		
	}
	
	@RequestMapping(value="/logout.html")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "redirect:hello.html";
	}
	
	@RequestMapping(value="/dummy.html")
	public ModelAndView dummy()
	{
		return new ModelAndView("client_index");
	}
}
