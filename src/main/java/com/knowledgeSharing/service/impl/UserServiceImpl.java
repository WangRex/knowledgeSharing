package com.knowledgeSharing.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.knowledgeSharing.IDao.UserMapper;
import com.knowledgeSharing.pojo.User;
import com.knowledgeSharing.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private UserMapper userMapper;
	public List<User> getUserAll() {
		return this.userMapper.selectAll();
	}
}
