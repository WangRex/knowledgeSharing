package com.knowledgeSharing.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.knowledgeSharing.common.JXLUtil;
import com.knowledgeSharing.pojo.Deliver;
import com.knowledgeSharing.pojo.User;
import com.knowledgeSharing.service.IDeliverService;
import com.knowledgeSharing.service.IUserService;

@Controller
@RequestMapping("/deliver")
public class DeliverController {
	@Autowired
	private IDeliverService deliverService;
	@Autowired
	private IUserService userService;
	@InitBinder("deliver")    
    public void initBinder2(WebDataBinder binder) {    
            binder.setFieldDefaultPrefix("deliver.");    
    } 
	@ResponseBody
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public List<Deliver> getList(ModelMap model) {
		
		try {
			List<Deliver> list = deliverService.getDeliverAll();
			return list;
		} catch (BeansException e) {
			e.printStackTrace();
			return null;
		}
	}
	@ResponseBody
	@RequestMapping(value = "/getDeliver", method = RequestMethod.GET)
	public Deliver selectByPrimaryKey(
			@RequestParam(value = "id", required = true, defaultValue = "1") String id,
			ModelMap model) {
		
		try {
			Deliver deliver = deliverService.selectByPrimaryKey(id);
			return deliver;
		} catch (BeansException e) {
			e.printStackTrace();
			return null;
		}
	}
	@ResponseBody
	@RequestMapping(value = "/updInitDeliver", method = RequestMethod.GET)
	public Deliver selectByPrimaryKeyUpdInit(
			@RequestParam(value = "id", required = true, defaultValue = "1") String id,
			ModelMap model) {
		
		try {
			Deliver deliver = deliverService.selectByPrimaryKey(id);
			return deliver;
		} catch (BeansException e) {
			e.printStackTrace();
			return null;
		}
	}
	@ResponseBody
	@RequestMapping(value = "/updDeliver", method = RequestMethod.GET)
	public Deliver updateByPrimaryKey(@ModelAttribute Deliver deliver) {
		
		try {
			deliverService.upd(deliver);
			return deliver;
		} catch (BeansException e) {
			e.printStackTrace();
			return null;
		}
	}
	@ResponseBody
	@RequestMapping(value = "/addDeliverInitUser", method = RequestMethod.GET)
	public String getUserDefault(HttpServletRequest request) {
		
		try {

			List<User> userList = userService.getUserAll();
			Map<String, User> map = new HashMap<String, User>();
			for(User u : userList) {
				map.put(u.getComputerName().toString(), u);
			}
			
			String strName = "";
			String strComputerName = "";
			strComputerName = DataUtils.getComputerName(request.getRemoteHost());
			strName = map.get(strComputerName) == null ? "LeiFeng" : map.get(strComputerName).getRealName();
			return strName;
			
		} catch (BeansException e) {
			e.printStackTrace();
			return null;
		}
	}
	@ResponseBody
	@RequestMapping(value = "/addDeliverInitGetUserList", method = RequestMethod.GET)
	public List<User> getUserList(
			@ModelAttribute Deliver deliver,
			HttpServletRequest request,
			ModelMap model) {
		
		try {

			List<User> userList = userService.getUserAll();
			return userList;
			
		} catch (BeansException e) {
			e.printStackTrace();
			return null;
		}
	}
	@ResponseBody
	@RequestMapping(value = "/addDeliver", method = RequestMethod.GET)
	public void addIssues( @ModelAttribute Deliver deliver) {
		
		try {

			deliver.setId(Long.valueOf(DataUtils.generate9()));
			deliverService.add(deliver);
			
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
	@ResponseBody
	@RequestMapping(value = "/delDeliver", method = RequestMethod.GET)
	public void delIssues(
			@RequestParam(value = "id", required = true, defaultValue = "") String id,
			ModelMap model) {
		
		try {
			deliverService.del(id);
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
	@ResponseBody
	@RequestMapping(value = "/getListForExportToExcel", method = RequestMethod.GET)
	public String getListForExportToExcel(ModelMap model, 
			@RequestParam(value = "strParam", required = true, defaultValue = "") String strParam, 
			HttpServletResponse response) {
		
		try {
			List<Deliver> list = deliverService.getDeliverAllForExportToExcel(strParam);
			String filename = JXLUtil.writeExcel(list);
			return filename;
		} catch (BeansException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
