package net.blairdye.simpledao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class UserDaoImpl implements UserDao {  
	//private DataSource dataSource;  
	private Session session;

	public void createUser(User user) {
		session.save(user);
	}  

	public List<User> getAllUsers() { 
		Query query = session.createQuery("from User");
		return query.list();
	}  


	public User getUserByUserName(String userName){
		User user = null;  
		Query query = session.createQuery("from User where userName = :userName");

		query.setParameter("userName", userName);
		List<User> users = query.list();
		if(users != null && users.size() > 0) {
			user = users.get(0);
		}  
		return user;
	}

	public void setDataSource(DataSource dataSource) throws SQLException {
		//this.dataSource = dataSource;
		// this.jdbcTemplate = new JdbcTemplate(this.dataSource);
		//java.sql.Connection conn = dataSource.getConnection();
		Configuration configuration = new Configuration()
				.setProperty("hibernate.current_session_context_class", "thread").addAnnotatedClass(User.class)
				.configure();

		SessionFactory sf = configuration.buildSessionFactory(
				new StandardServiceRegistryBuilder().applySetting(Environment.DATASOURCE, dataSource).build());
		session = sf.openSession();

	}
	
	public void cleanup(){
		//not needed as the DB disappears after program ends
		//if(session !=null && session.isOpen())
			//session.close();
		
	}
/*
	public DataSource getDataSource() {
		return dataSource;  
	} 
*/	 
}

