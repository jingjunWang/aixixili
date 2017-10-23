package invoice.iceason.weixin.service;

import invoice.iceason.weixin.dao.InEnterpriseDao;
import invoice.iceason.weixin.entity.InEnterprise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InEnterpriseService extends BaseService<InEnterprise>{

	private InEnterpriseDao inEnterpriseDao;
	
	@Autowired
	public InEnterpriseService(InEnterpriseDao inEnterpriseDao){
		super(inEnterpriseDao);
		this.inEnterpriseDao = inEnterpriseDao;
	}


}
