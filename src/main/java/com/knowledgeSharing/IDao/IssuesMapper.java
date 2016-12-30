package com.knowledgeSharing.IDao;

import java.util.List;

import com.knowledgeSharing.pojo.Issues;

public interface IssuesMapper {
    int deleteByPrimaryKey(String id);

    int insert(Issues record);

    int insertSelective(Issues record);

    Issues selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Issues record);

    int updateByPrimaryKey(Issues record);
    
    void updateDelByPrimaryKey(String id);
    
    List<Issues> selectAll();
}