package com.knowledgeSharing.service;

import java.util.List;

import com.knowledgeSharing.pojo.Issues;

public interface IIssuesService {
	public List<Issues> getIssuesAll();
	public Issues getIssuesById(String id);
	public void addIssues(Issues issue);
	public void delIssues(String id);
	public void updIssues(Issues issue);
}