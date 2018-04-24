package com.elitecore.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.elitecore.dao.ClientDao;
import com.elitecore.dao.DBMasterDao;
import com.elitecore.model.Attempt;
import com.elitecore.model.Audit;
import com.elitecore.model.Client;
import com.elitecore.model.Report;

@Service("clientservices")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class clientservices {

	@Autowired
	ClientDao dao;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean check_attempts(String contact, int max_attempts) {
		
		int i=dao.check_attempts(contact);
		
		if(i<max_attempts)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void save_client(Client client, int OTP) {
		// TODO Auto-generated method stub
		dao.save_client(client,OTP);
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int max_attempts() {
		// TODO Auto-generated method stub
		int i=dao.max_attempts();
		return i;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void change_limit(int maximum) {
		// TODO Auto-generated method stub
		dao.change_limit(maximum);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int check_client_first(String contact, String username) {
		// TODO Auto-generated method stub
		int blocked=dao.check_client(contact,username);
		return blocked;
		
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void add_otp_Attempt(String contact) {
		// TODO Auto-generated method stub
		dao.check_otp_attempt(contact);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void add_data_attempt(String contact, int oTP) {
		// TODO Auto-generated method stub
		int client_id=dao.add_data_attempt(contact);
		dao.add_attempt(client_id,oTP);
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Client> getbykeyword(String keyword,int pageid, int total) {
		
		return dao.getReportBykeyword(keyword, pageid, total);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int getcount(String key) {

		int result=dao.getcount(key);
		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void block_user(int id) {
		// TODO Auto-generated method stub
		dao.block_user(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void unblock_user(int id) {
		// TODO Auto-generated method stub
		dao.unblock_user(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void grant_user(int id) {
		// TODO Auto-generated method stub
		dao.grant_user(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void revoke_user(int id) {
		// TODO Auto-generated method stub
		dao.revoke_user(id);
	}

	public void dlt_client(int id) {
		// TODO Auto-generated method stub
		dao.dlt_client(id);
	}

	public void multi_dlt_client(int ids) {
		// TODO Auto-generated method stub
		dao.multi_dlt_client(ids);
	}

	public List<Map<String, Object>> showAllAttempts(String key, int pageid, int total) {
		// TODO Auto-generated method stub
		return dao.showAllAttempts(key, pageid, total);
	}

	public int getcountAttempts(String key) {
		// TODO Auto-generated method stub
		int result=dao.getcountAttempts(key);
		return result;
		
	}

	public List<Map<String, Object>> showAllAudits(String key, int pageid, int total) {
		// TODO Auto-generated method stub
		return dao.showAllAudits(key, pageid, total);
	}

	public int getcountAudits(String key) {
		// TODO Auto-generated method stub
		int result=dao.getcountAudits(key);
		return result;
	}
	
}
