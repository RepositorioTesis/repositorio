package utils;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vaadin.demo.domain.Usuario;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static <T> Boolean saveEntity(T object) throws Exception{
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	try {
    		session.beginTransaction();
    		session.saveOrUpdate(object);
    		session.getTransaction().commit();
    		
    		return true;
    		
		} catch (Exception e) {
			session.close();
		 throw e;
		} finally {
			//HibernateUtil.getSessionFactory().close();
		} 
    }
    
    public static  List<?> getEntity(String consulta){
    		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    		session.beginTransaction();
    		try{
            List<?> result = session.createQuery(consulta).list();
            return result;
    		} catch (Exception e) {
    			System.out.println(e.toString());
    		}
            session.getTransaction().commit();
//            if(result.isEmpty())
//            	return null;
//            return result;
            return null;
    }
    

}