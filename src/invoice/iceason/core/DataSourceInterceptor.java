package invoice.iceason.core;

import org.aspectj.lang.JoinPoint;

public class DataSourceInterceptor {
	//sessionFactoryincoice
	public void setDataSourceIncoice(JoinPoint jp) {
		DatabaseContextHolder.setCustomerType(DatabaseContextHolder.SESSION_FACTORY_INVOICE);
	}
	
	public void setDataSourceBo(JoinPoint jp) {
		DatabaseContextHolder.setCustomerType(DatabaseContextHolder.SESSION_FACTORY_BO);
	}
}