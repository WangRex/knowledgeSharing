package com.knowledgeSharing.service;

import java.util.List;

import com.knowledgeSharing.pojo.Deliver;

public interface IDeliverService {
	public List<Deliver> getDeliverAll();
	public Deliver selectByPrimaryKey(String id);
	public void add(Deliver pojo);
	public void del(String id);
	public void upd(Deliver pojo);
	public List<Deliver> getDeliverAllForExportToExcel(String sql);
}