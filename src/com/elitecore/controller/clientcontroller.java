package com.elitecore.controller;

import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elitecore.dto.Clientdto;
import com.elitecore.dto.Schedulerdto;
import com.elitecore.dto.querydto;
import com.elitecore.dto.userdto;
import com.elitecore.model.Attempt;
import com.elitecore.model.Audit;
import com.elitecore.model.Client;
import com.elitecore.model.Report;
import com.elitecore.model.User;
import com.elitecore.services.Client_utilities;
import com.elitecore.services.clientservices;
import com.elitecore.services.queryservices;
import com.elitecore.services.transfer;

@Controller
public class clientcontroller {
	
	@Autowired
	clientservices services;
	
	String base_path = "C:/Vatsal/EliteCoreGITPRoject/";
	
	@RequestMapping("/client_login.html")
	public ModelAndView client_login()
	{
		return new ModelAndView("client_form","Clientdto",new Client());
	}
	
	@RequestMapping("/client_otp.html")
	public ModelAndView client_otp(@ModelAttribute Clientdto clientdto, HttpSession session)
	{
		
		Boolean rows = null;
		String contact=clientdto.getContact();
		String username=clientdto.getUsername();
		int max_attempts=services.max_attempts();
		
		//check already Client or signing up?
		System.out.println("client ne check krto avu pehla....");
		int status_response=services.check_client_first(contact,username);
		System.out.println("aai gyo check krine with status response etle account htu k nai..."+status_response+"-----");
		if(status_response==-5)
		{
			//this user is registered for the first time so will not be blocked obviously
			rows=true;	
			System.out.println("Ahiya AYO???????????????????????????????");
			//Audit ma entry padvani OTP attempt ma...
			services.add_otp_Attempt(contact);
			System.out.println("AUDIT MA NAVI ENTRY PADI AAYO....hehehehe.....");
			
		}
		else
		{
			//this user is already registered so may be blocked or Unblocked so check it..
			if(status_response==0)
			{
				//Not blocked
				System.out.println("Pakku 100% ahiya j avto hois....------------");
				//Rows=true jo limit krta o6a attempt hoyy ajna to(check audit mathi 'data_attempt'
				rows=services.check_attempts(contact,max_attempts);
				System.out.println("Hve OTP ATTEMPT MAchedva JAISU....");
				//Audit ma entry padvani OTP attempt ma...
				services.add_otp_Attempt(contact);
				System.out.println("AAI GYA MACHEDI NE OTP ATTEMPTS NE....");
			}
			else{
				//Blocked... 
				rows=false;
			}
		}		
		
		ModelAndView model=new ModelAndView();
		if(rows)
		{
			System.out.println("HAVE OTP BANAISU....");
			//generate Otp
			int OTP = Client_utilities.generate_otp();
						
			//Sending OTP text message code is below but it is commented to stop wastage of credits
		//-------------------
			//send Sms
			try
			{
				String text="Dear User, Thank you for choosing our Network. To Access the Internet Session, your OTP is";
				//String text="your Otp is";
				text=text.replaceAll(" ", "%20");
				String requestUrl= "https://www.smsgatewayhub.com/api/mt/SendSMS?APIKey=pWNGSxPqPkC7G2T6UXofvA&senderid=TESTIN&channel=2&DCS=0&flashsms=0&number="+contact+"&text="+text+"%20"+OTP+"&route=1";
				URL url = new URL(requestUrl);
				System.out.println(requestUrl);
				HttpURLConnection uc = (HttpURLConnection)url.openConnection();				  
				System.out.println(uc.getResponseMessage());
				uc.disconnect();				  
			} 
			catch(Exception ex) 
			{
					System.out.println(ex.getMessage());
					
			}
		//----------------------			
			
			//session Set attributes...
			session.setAttribute("contact", contact);
			session.setAttribute("OTP", OTP);
			
			Client client=transfer.client_trans(clientdto);
			session.setAttribute("client", client);
			
			System.out.println("FINALLY BIJU PAGE DEKHASE HVE....");
			//forwarding it to OTP page
			model.setViewName("otp_page");
			model.addObject("Clientdto", new Clientdto());
		}
		else
		{
			model.setViewName("access_denied");
		}
		
		return model;		
	}
	
	@RequestMapping("/client_verify_otp.html")
	public ModelAndView client_verify_login(@RequestParam("otp") int OTP, HttpSession session)
	{
		ModelAndView model=new ModelAndView();
		Boolean success_otp=false;
		
		int saved_OTP=((int)session.getAttribute("OTP"));
		System.out.println("My Entered OTP is "+OTP+" And Saved OTP in Session is "+saved_OTP);
		
		//verify entered OTP
		if(OTP==saved_OTP)
		{				
			success_otp=true;
		}
		//if Otp is verified then
		if(success_otp)
		{
			System.out.println("OTPs are verified...Hurrayy...Make increment of the attempt...");
			//increment data_attempt in audit and Add attempt entry in attempt
			Client client=(Client)session.getAttribute("client");
			String contact=client.getContact();
			services.add_data_attempt(contact,OTP);
			
			
			//redirect to timer page
			model.setViewName("start_timer_session");
		}
		else
		{
			model.setViewName("client_index");
		}
		return model;
		
	}
	
	@RequestMapping("/change_attempt.html")
	public ModelAndView change_attempt()
	{
		return new ModelAndView("attempt_form");
	}

	@RequestMapping("/attempts_changed.html")
	public String changed_attempt(@RequestParam("Max_attempt") int maximum)
	{
		services.change_limit(maximum);
		return "redirect:profile.html";
	}
	
	@RequestMapping(value="/viewclients.html*")
	public ModelAndView get_clients(
	@RequestParam(value="page", required=false) int pageid, @RequestParam(value="key", required=false, defaultValue="") String key, HttpSession session)
	{
		session.setAttribute("page_id",pageid);
		
		int total=5;
		if(pageid==1){}
		else
		{
			pageid=(pageid-1)*total+1;
		}
		
		List<Client> list=services.getbykeyword(key, pageid, total);
		
		int result = services.getcount(key);
		int no;
		if(result%5 == 0)
			no = (result/5);
		else
			no = (result/5)+1;
		
		ModelAndView model=new ModelAndView();
		model.addObject("clientdto",new Clientdto());
		model.addObject("list",list);
		model.addObject("count", no);
		model.addObject("key",key);
		model.setViewName("clientview");
		return model;	
	}
	
	@RequestMapping("/block_user.html")
	public void block_user(@RequestParam("id") int id)
	{
		services.block_user(id);
		
	}
	
	@RequestMapping("/unblock_user.html")
	public void unblock_user(@RequestParam("id") int id)
	{
		services.unblock_user(id);
		
	}
	
	@RequestMapping("/grant_user.html")
	public void grant_user(@RequestParam("id") int id)
	{
		services.grant_user(id);
		
	}
	
	@RequestMapping("/revoke_user.html")
	public void revoke_user(@RequestParam("id") int id)
	{
		services.revoke_user(id);
		
	}
	
	@RequestMapping("/SingleDeleteClient.html")
	public void single_dlt_client(@RequestParam("id") int id)
	{
		services.dlt_client(id);
		
	}
	
	@RequestMapping("/MultiDeleteClient.html")
	public void multi_dlt_client(@RequestParam("ids") int ids)
	{
		services.multi_dlt_client(ids);
		
	}
	
	@RequestMapping(value="/viewattempts.html*")
	public ModelAndView get_attempts(
	@RequestParam(value="page", required=false) int pageid, @RequestParam(value="key", required=false, defaultValue="") String key, HttpSession session)
	{
		session.setAttribute("page_id",pageid);
		
		int total=5;
		if(pageid==1){}
		else
		{
			pageid=(pageid-1)*total+1;
		}
		
		List<Map<String, Object>> list=services.showAllAttempts(key, pageid, total);
		
		int result = services.getcountAttempts(key);
		int no;
		if(result%5 == 0)
			no = (result/5);
		else
			no = (result/5)+1;
		
		ModelAndView model=new ModelAndView();
		//model.addObject("clientdto",new Clientdto());
		model.addObject("list",list);
		model.addObject("count", no);
		model.addObject("key",key);
		model.setViewName("client_view_attempts");
		return model;	
	}
	
	@RequestMapping(value="/viewaudits.html*")
	public ModelAndView get_audits(
	@RequestParam(value="page", required=false) int pageid, @RequestParam(value="key", required=false, defaultValue="") String key, HttpSession session)
	{
		session.setAttribute("page_id",pageid);
		
		int total=5;
		if(pageid==1){}
		else
		{
			pageid=(pageid-1)*total+1;
		}
		
		List<Map<String, Object>> list=services.showAllAudits(key, pageid, total);
		
		int result = services.getcountAudits(key);
		int no;
		if(result%5 == 0)
			no = (result/5);
		else
			no = (result/5)+1;
		
		ModelAndView model=new ModelAndView();
		//model.addObject("clientdto",new Clientdto());
		model.addObject("list",list);
		model.addObject("count", no);
		model.addObject("key",key);
		model.setViewName("client_view_audit");
		return model;	
	}
	
	@RequestMapping("/upload_file.html")
	public ModelAndView upload_file()
	{
		return new ModelAndView("upload_file");
	}
	
	@RequestMapping("/fetch_view_uploaded.html")
	public ModelAndView fetch_view_uploaded()
	{
		return new ModelAndView("fetch_view_uploaded");
	}
	
	@RequestMapping(value="/upload_this.html", method=RequestMethod.POST)
    public String singleFileUpload(@RequestParam("report") MultipartFile file,
    		 RedirectAttributes redirectAttributes,HttpSession session)
    			{
    				
    		        if (file.isEmpty()) {
    		            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
    		            return "redirect:uploadStatus";
    		        }

    		        try {
    		        	byte[] bytes = file.getBytes();
    		        	
    		        	
    		            Path path = Paths.get(base_path+"\\WebContent\\"+"\\file\\" + file.getOriginalFilename());
    		            System.out.println(base_path+"\\WebContent\\"+"\\file\\" + file.getOriginalFilename());
    		            System.out.println(path);
    		            Files.write(path, bytes);
    		            
    		            
    		        } catch (IOException e) {
    		            e.printStackTrace();
    		        }

    		        return "upload";
    		    }
}
