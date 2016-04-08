package net.blairdye.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.blairdye.simpledao.User;
import net.blairdye.simpledao.UserDao;

public class HibernateMain {
	
	
	private HibernateMain(){}
	
	private void findUser(UserDao userDao){
		//User user = userDao.getUserByUserName("blaird");
		User user = userDao.getUserByUserName("blaird");
		System.out.println("user="+user);
	}

	private void findAllUsers(UserDao userDao){
		List<User> users = userDao.getAllUsers();
		for(User user: users){
			System.out.println("found user="+user);
		}
	}
	
	final static Logger logger = Logger.getLogger(HibernateMain.class);
	public static void main(String[] args) {
		
	    ApplicationContext context = 
	             new ClassPathXmlApplicationContext("applicationContext.xml");
	    UserDao userDao = (UserDao)context.getBean("userDao");
	    HibernateMain hm = new HibernateMain();
	    hm.findUser(userDao);
	    hm.findAllUsers(userDao);
	    userDao.cleanup();
	}

}
