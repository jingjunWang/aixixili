package invoice.iceason.weixin.service;

import invoice.iceason.weixin.dao.StoreListDao;
import invoice.iceason.weixin.entity.StoreList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreListService extends BaseService<StoreList>{

	private StoreListDao storeListDao;
	
	@Autowired
	public StoreListService(StoreListDao storeListDao){
		super(storeListDao);
		this.storeListDao = storeListDao;
	}


}
