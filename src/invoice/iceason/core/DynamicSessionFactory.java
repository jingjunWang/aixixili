package invoice.iceason.core;

import org.hibernate.SessionFactory;

public interface DynamicSessionFactory extends SessionFactory {
    
    public SessionFactory getHibernateSessionFactory();
}