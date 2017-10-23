package invoice.iceason.core;

public class DatabaseContextHolder {
	
	public final static String SESSION_FACTORY_INVOICE = "sessionFactoryinvoice";
	public final static String SESSION_FACTORY_BO = "sessionFactorybo";

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setCustomerType(String customerType) {
		contextHolder.set(customerType);
	}

	public static String getCustomerType() {
		return contextHolder.get();
	}

	public static void clearCustomerType() {
		contextHolder.remove();
	}
	
}