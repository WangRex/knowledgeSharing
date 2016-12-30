package com.knowledgeSharing.pojo;


public class Issues {
    private String id;

    private String issueType;

    private String summary;

    private String descriptionLogs;

    private String solution;

    private String fixedBy;

    private String inputDate;

    private String inputName;

    private String visitorName;

    private String delsign;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? "" : id.trim();
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType == null ? "" : issueType.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? "" : summary.trim();
    }

    public String getDescriptionLogs() {
        return descriptionLogs;
    }

    public void setDescriptionLogs(String descriptionLogs) {
        this.descriptionLogs = descriptionLogs == null ? "" : descriptionLogs;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution == null ? "" : solution.trim();
    }

    public String getFixedBy() {
        return fixedBy;
    }

    public void setFixedBy(String fixedBy) {
        this.fixedBy = fixedBy == null ? "" : fixedBy.trim();
    }
    
    public String getInputDate() {
        return inputDate;
    }

    public void setInputDate(String inputDate) {
        this.inputDate = inputDate == null ? "" : inputDate.trim();
    }

	public String getInputName() {
		return inputName;
	}

	public void setInputName(String inputName) {
        this.inputName = inputName == null ? "" : inputName.trim();
	}

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName == null ? "" : visitorName.trim();;
	}

	public String getDelsign() {
		return delsign;
	}

	public void setDelsign(String delsign) {
        this.delsign = delsign == null ? "" : delsign.trim();
	}
    
}