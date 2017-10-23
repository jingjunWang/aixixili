package invoice.iceason.weixin.service;

import invoice.iceason.weixin.dao.InPersonalDao;
import invoice.iceason.weixin.entity.InPersonal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InPersonalService extends BaseService<InPersonal>{

	private InPersonalDao inPersonalDao;
	
	@Autowired
	public InPersonalService(InPersonalDao inPersonalDao){
		super(inPersonalDao);
		this.inPersonalDao = inPersonalDao;
	}


}
