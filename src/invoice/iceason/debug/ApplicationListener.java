package invoice.iceason.debug;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

public class ApplicationListener extends ContextLoaderListener{
	public static String rootPath;
	 public void contextInitialized(ServletContextEvent sce) {  
		          // TODO Auto-generated method stub  
			rootPath=sce.getServletContext().getInitParameter("Iceason_Invoice.rootPath");
	 } 

}
