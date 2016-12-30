package com.knowledgeSharing.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knowledgeSharing.common.DataUtils;
import com.knowledgeSharing.pojo.Issues;
import com.knowledgeSharing.pojo.User;
import com.knowledgeSharing.service.IIssuesService;
import com.knowledgeSharing.service.IUserService;

@Controller
@RequestMapping("/issues")
public class IssuesController {
	@Autowired
	private IIssuesService issuesService;
	@Autowired
	private IUserService userService;
	@InitBinder("issues")
    public void initBinder(WebDataBinder binder) {    
            binder.setFieldDefaultPrefix("issues.");    
    } 
	@ResponseBody
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public List<Issues> getList(
			HttpServletRequest request,
			ModelMap model) {
		
		try {
			List<Issues> list = issuesService.getIssuesAll();
			return list;
		} catch (BeansException e) {
			e.printStackTrace();
			return null;
		}
	} 
	@ResponseBody
	@RequestMapping(value = "/getVisitor", method = RequestMethod.GET)
	public User getVisitor(
			HttpServletRequest request,
			ModelMap model) {
		
		try {
			List<User> userList = userService.getUserAll();
			Map<String, User> map = new HashMap<String, User>();
			User user = null;
			String strComputerName = "";
			strComputerName = DataUtils.getComputerName(request.getRemoteHost());
			for(User u : userList) {
				map.put(u.getComputerName().toString(), u);
				if(strComputerName.equals(u.getComputerName().toString())) {
					user = u;
				}
			}
			if(user == null) {
				user = new User();
				user.setRealName("LeiFeng");
				user.setRole("3");
			}
			return user;
		} catch (BeansException e) {
			e.printStackTrace();
			return null;
		}
	}
	@ResponseBody
	@RequestMapping(value = "/getIssue", method = RequestMethod.GET)
	public Issues getIssues(
			@RequestParam(value = "id", required = true, defaultValue = "1") String id,
			ModelMap model) {
		
		try {
			Issues issues = issuesService.getIssuesById(id);
			return issues;
		} catch (BeansException e) {
			e.printStackTrace();
			return null;
		}
	}
	@ResponseBody
	@RequestMapping(value = "/updInitIssue", method = RequestMethod.GET)
	public Issues getUpdInitIssues(
			@RequestParam(value = "id", required = true, defaultValue = "1") String id,
			ModelMap model) {
		
		try {
			Issues issues = issuesService.getIssuesById(id);
			return issues;
		} catch (BeansException e) {
			e.printStackTrace();
			return null;
		}
	}
	@ResponseBody
	@RequestMapping(value = "/addIssue", method = RequestMethod.GET)
	public void addIssues(
			@ModelAttribute Issues issues,
			HttpServletRequest request,
			ModelMap model) {
		
		try {

			List<User> userList = userService.getUserAll();
			Map<String, User> map = new HashMap<String, User>();
			for(User u : userList) {
				map.put(u.getComputerName().toString(), u);
			}
			issues.setId(DataUtils.generate9());
			String strName = "";
			String strComputerName = "";
			strComputerName = DataUtils.getComputerName(request.getRemoteHost());
			strName = map.get(strComputerName) == null ? "LeiFeng" : map.get(strComputerName).getRealName();
			issues.setInputName(strName);
			issuesService.addIssues(issues);
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
	@ResponseBody
	@RequestMapping(value = "/delIssue", method = RequestMethod.GET)
	public void delIssues(
			@RequestParam(value = "id", required = true, defaultValue = "") String id,
			ModelMap model) {
		
		try {
			issuesService.delIssues(id);
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
	@ResponseBody
	@RequestMapping(value = "/updIssue", method = RequestMethod.GET)
	public void updIssues(@ModelAttribute Issues issues,
			ModelMap model) {
		
		try {
			issuesService.updIssues(issues);
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
	
}
