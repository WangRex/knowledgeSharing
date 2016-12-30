package com.knowledgeSharing.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.knowledgeSharing.IDao.IssuesMapper;
import com.knowledgeSharing.pojo.Issues;
import com.knowledgeSharing.service.IIssuesService;

@Service("issuesService")
public class IssuesServiceImpl implements IIssuesService {
	@Resource
	private IssuesMapper issuesMapper;
	public List<Issues> getIssuesAll() {
		return this.issuesMapper.selectAll();
	}
	public Issues getIssuesById(String id) {
		return this.issuesMapper.selectByPrimaryKey(id);
	}
	public void addIssues(Issues issue) {
		this.issuesMapper.insert(issue);
	}
	public void delIssues(String id) {
		this.issuesMapper.updateDelByPrimaryKey(id);
	}
	public void updIssues(Issues issue) {
		this.issuesMapper.updateByPrimaryKey(issue);
	}

}
