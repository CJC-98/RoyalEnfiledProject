package com.app.extremity.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.extremity.model.Advisors;
import com.app.extremity.model.User;
import com.app.extremity.serviceimpl.HomeServiceImpl;
import com.google.gson.Gson;

@RestController
public class Controller3 {

	@Autowired
	HomeServiceImpl service;
	 
	//@RequestMapping("/assign")
	//public ResponseEntity<Advisors> assignAdv(@RequestParam(name="userPin") String userpin,@RequestParam(name="advisorAddPin")String advisorAddPin){
	//@RequestMapping("/assign")	
	public void assignAdv(@RequestParam(name="advisorAddPin") String adpin,HttpServletResponse response) throws IOException{
	
		int pincode=Integer.parseInt(adpin);
		System.out.println("assign controller"+adpin);
		List<Advisors> advs=service.getAdvisors(pincode);

		 
		String json = new Gson().toJson(advs);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}
 
	@RequestMapping("/assign")	
	public ResponseEntity<List<Advisors>> assignAdv(@RequestParam(name="advisorAddPin") int adpin){
	
		//int pincode=Integer.parseInt(adpin);
		System.out.println("assign controller"+adpin);
		List<Advisors> advs=service.getAdvisors(adpin);

		//return new ResponseEntity<Advisors>(advs,HttpStatus.OK); 
		return new ResponseEntity<List<Advisors>>((List<Advisors>) advs,HttpStatus.OK);
	}
	@RequestMapping("/adud")	
	public String assignAdvisortoUser(@RequestParam(name="advisorID") String adid,@RequestParam(name="userID") String uid){
	
		System.out.println("in adud");
		System.out.println(adid); 
		System.out.println(uid); 
		service.assignAdvisor(adid, uid);
		return "assignAdvisortoUser";
	} 
	
}
