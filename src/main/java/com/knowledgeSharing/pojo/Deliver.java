package com.knowledgeSharing.pojo;

import com.knowledgeSharing.common.DataUtils;

public class Deliver {
    private Long id;

    private String developer;

    private String deliveryDate;

    private String description;

    private String appChanges;

    private String webChanges;

    private String configChanges;

    private String dbChanges;

    private String taskSummary;

    private String status;

    private String region;

    private String uxfdp;

    private String uxfvf;

    private String gcBuildIqadp;

    private String gcBuildIqavf;

    private String gcUatIqadp;

    private String gcUatIqavf;

    private String sitdp;

    private String sitvf;

    private String uatdp;

    private String uatvf;

    private String remark;

    private String delsign;

    private String sql;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer == null ? "" : developer.trim();
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate == null ? "" : deliveryDate.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description.trim();
    }

    public String getAppChanges() {
        return appChanges;
    }

    public void setAppChanges(String appChanges) {
        this.appChanges = appChanges == null ? "" : appChanges.trim();
    }

    public String getWebChanges() {
        return webChanges;
    }

    public void setWebChanges(String webChanges) {
        this.webChanges = webChanges == null ? "" : webChanges.trim();
    }

    public String getConfigChanges() {
        return configChanges;
    }

    public void setConfigChanges(String configChanges) {
        this.configChanges = configChanges == null ? "" : configChanges.trim();
    }

    public String getDbChanges() {
        return dbChanges;
    }

    public void setDbChanges(String dbChanges) {
        this.dbChanges = dbChanges == null ? "" : dbChanges.trim();
    }

    public String getTaskSummary() {
        return taskSummary;
    }

    public void setTaskSummary(String taskSummary) {
        this.taskSummary = taskSummary == null ? "" : taskSummary.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? "" : status.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? "" : region.trim();
    }

    public String getUxfdp() {
        return uxfdp;
    }

    public void setUxfdp(String uxfdp) {
        this.uxfdp = uxfdp == null ? "" : uxfdp.trim();
    }

    public String getUxfvf() {
        return uxfvf;
    }

    public void setUxfvf(String uxfvf) {
        this.uxfvf = uxfvf == null ? "" : uxfvf.trim();
    }

    public String getGcBuildIqadp() {
        return gcBuildIqadp;
    }

    public void setGcBuildIqadp(String gcBuildIqadp) {
        this.gcBuildIqadp = gcBuildIqadp == null ? "" : gcBuildIqadp.trim();
    }

    public String getGcBuildIqavf() {
        return gcBuildIqavf;
    }

    public void setGcBuildIqavf(String gcBuildIqavf) {
        this.gcBuildIqavf = gcBuildIqavf == null ? "" : gcBuildIqavf.trim();
    }

    public String getGcUatIqadp() {
        return gcUatIqadp;
    }

    public void setGcUatIqadp(String gcUatIqadp) {
        this.gcUatIqadp = gcUatIqadp == null ? "" : gcUatIqadp.trim();
    }

    public String getGcUatIqavf() {
        return gcUatIqavf;
    }

    public void setGcUatIqavf(String gcUatIqavf) {
        this.gcUatIqavf = gcUatIqavf == null ? "" : gcUatIqavf.trim();
    }

    public String getSitdp() {
        return sitdp;
    }

    public void setSitdp(String sitdp) {
        this.sitdp = sitdp == null ? "" : sitdp.trim();
    }

    public String getSitvf() {
        return sitvf;
    }

    public void setSitvf(String sitvf) {
        this.sitvf = sitvf == null ? "" : sitvf.trim();
    }

    public String getUatdp() {
        return uatdp;
    }

    public void setUatdp(String uatdp) {
        this.uatdp = uatdp == null ? "" : uatdp.trim();
    }

    public String getUatvf() {
        return uatvf;
    }

    public void setUatvf(String uatvf) {
        this.uatvf = uatvf == null ? "" : uatvf.trim();
    }

    public String getRemark() {
        return DataUtils.getNoNull(remark);
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? "" : remark.trim();
    }

	public String getDelsign() {
		return delsign;
	}

	public void setDelsign(String delsign) {
        this.delsign = delsign == null ? "" : delsign.trim();
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
        this.sql = sql == null ? "" : sql.trim();
	}
}