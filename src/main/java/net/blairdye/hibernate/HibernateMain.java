package net.blairdye.hibernate;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.blairdye.simpledao.User;

public class HibernateMain {
	final static Logger logger = Logger.getLogger(HibernateMain.class);
	public static void main(String[] args) {
	    ApplicationContext context = 
	             new ClassPathXmlApplicationContext("applicationContext.xml");
	    User user = userDao.getUserByUserName("blair");  
	    /*
		Webmail webmail = new Webmail();
		webmail.setOrganizationId("100");
		webmail.setDomainname("test");
		logger.info("Creating webmail");
		// Get Session
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		// start transaction
		session.beginTransaction();
		// Save the Model object
		session.save(webmail);
		// Commit transaction
		session.getTransaction().commit();
		System.out.println("Employee ID=" + webmail.getOrganizationId());

		// terminate session factory, otherwise program won't end
		HibernateUtil.getSessionFactory().close();
		*/
	}

}
