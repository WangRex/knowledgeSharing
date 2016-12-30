package com.knowledgeSharing.IDao;

import java.util.List;

import com.knowledgeSharing.pojo.User;

public interface UserMapper {
    
    List<User> selectAll();
}