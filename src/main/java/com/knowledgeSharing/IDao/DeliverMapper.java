package com.knowledgeSharing.IDao;

import java.util.List;

import com.knowledgeSharing.pojo.Deliver;

public interface DeliverMapper {
	
	List<Deliver> selectAll();
	
    int deleteByPrimaryKey(Long id);

    int insert(Deliver record);

    int insertSelective(Deliver record);

    Deliver selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Deliver record);

    int updateByPrimaryKey(Deliver record);
    
	List<Deliver> selectAllForExportToExcel(Deliver record);
    
}