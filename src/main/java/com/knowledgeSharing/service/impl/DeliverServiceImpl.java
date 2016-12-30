package com.knowledgeSharing.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.knowledgeSharing.IDao.DeliverMapper;
import com.knowledgeSharing.pojo.Deliver;
import com.knowledgeSharing.service.IDeliverService;

@Service("deliverService")
public class DeliverServiceImpl implements IDeliverService {
	@Resource
	private DeliverMapper deliverMapper;
	public List<Deliver> getDeliverAll() {
		return this.deliverMapper.selectAll();
	}
	public Deliver selectByPrimaryKey(String id) {
		return this.deliverMapper.selectByPrimaryKey(Long.valueOf(id));
	}
	public void add(Deliver pojo) {
		this.deliverMapper.insert(pojo);
	}
	public void del(String id) {
		this.deliverMapper.deleteByPrimaryKey(Long.valueOf(id));
	}
	public void upd(Deliver pojo) {
		this.deliverMapper.updateByPrimaryKey(pojo);
	}
	public List<Deliver> getDeliverAllForExportToExcel(String strParam) {
		String sql = "select * from KS_DELIVER_T where "
				+ "(DEVELOPER like '%" + strParam + "%' "
				+ "or DELIVERY_DATE like '%" + strParam + "%' "
				+ "or DESCRIPTION like '%" + strParam + "%' "
				+ "or APP_CHANGES like '%" + strParam + "%' "
				+ "or WEB_CHANGES like '%" + strParam + "%' "
				+ "or CONFIG_CHANGES like '%" + strParam + "%' "
				+ "or DB_CHANGES like '%" + strParam + "%' "
				+ "or TASK_SUMMARY like '%" + strParam + "%' "
				+ "or STATUS like '%" + strParam + "%' "
				+ "or REGION like '%" + strParam + "%' "
				+ "or UXFDP like '%" + strParam + "%' "
				+ "or UXFVF like '%" + strParam + "%' "
				+ "or GC_BUILD_IQADP like '%" + strParam + "%' "
				+ "or GC_BUILD_IQAVF like '%" + strParam + "%' "
				+ "or GC_UAT_IQADP like '%" + strParam + "%' "
				+ "or GC_UAT_IQAVF like '%" + strParam + "%' "
				+ "or SITDP like '%" + strParam + "%' "
				+ "or SITVF like '%" + strParam + "%' "
				+ "or UATDP like '%" + strParam + "%' "
				+ "or UATVF like '%" + strParam + "%' "
				+ "or REMARK like '%" + strParam + "%') and delsign = 0 order by to_date(DELIVERY_DATE,'MM/DD/YYYY') desc";
		Deliver deliver = new Deliver();
		deliver.setSql(sql);
		return this.deliverMapper.selectAllForExportToExcel(deliver);
	}

}
