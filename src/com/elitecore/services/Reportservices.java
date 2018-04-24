package com.elitecore.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.elitecore.model.User;
import com.elitecore.model.scheduler;
import com.elitecore.dao.ReportDao;
import com.elitecore.model.DBMaster;
import com.elitecore.model.Query;
import com.elitecore.model.Report;
@Service("Reportservices")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)

public class Reportservices {
@Autowired
ReportDao dao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Report> getreportbypage(int page, int total) 
	{
		return dao.getReportByPage(page, total);
	}
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)	
	public void addrep(Report rp) 
	{  
		System.out.println("in service,before invoking add report function");
		dao.saveReport(rp);
		System.out.println("in service, after invoking add report function");
	}
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void multidelete(String ids) 
	{		
		dao.multideleterep(ids);
	}
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void Dltrep(int id) 
	{
		dao.dltReport(id);
	}
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Map<String,Object>> caller(int sql,String disp_name,int db_id)
	{
		return dao.runner(sql,disp_name,db_id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Map<String,Object>> caller1(String qu,String disp_name,int db_id)
	{
		return dao.runner1(qu,disp_name,db_id);
	}
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Query> getbyid(int id)
	{
		return dao.getbyid(id);
	}
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int getparam(int x)
	{
		return dao.getparams(x);
	}
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Map<String,Object>> getbykeyword(String keyword,int pageid, int total) {
		
		return dao.getReportBykeyword(keyword, pageid, total);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int getcount(String key) {

		int result=dao.getcount(key);
		return result;
	}
	public List<User> sample() {
		// TODO Auto-generated method stub
		return dao.sample();
	}
	public Map<String, Object> call_for_reportid(int i, int j) {
		// TODO Auto-generated method stub
		System.out.println("In the Services "+i+" "+j);
		Map<String,Object> a=dao.getScheduled_reportid(i, j);
		System.out.println("Back from servies..."+a);
		return a;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addschedule(scheduler sch) {
		dao.addscheduler(sch);
		
	}

}
