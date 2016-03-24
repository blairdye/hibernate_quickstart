package net.blairdye.simpledao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;

public class UserDaoImpl implements UserDao {  
		 private DataSource dataSource;  
		 private JdbcTemplate jdbcTemplate;  
		 private Session session;
		 
		 public void createUser(User user) {  

			 
		  jdbcTemplate.update("insert into users (group_id,username,password,first_name," +  
		    "middle_name,last_name,phone_number) " +  
		    "values (?,?,?,?,?,?,?)", new Object[] {new Integer(1), user.getUsername(), user.getPassword(),   
		    user.getFirstName(), user.getMiddleName(), user.getLastName(),   
		    new Integer(user.getPhoneNumber())});  
		 }  
		  
		 public List<User> getAllUsers() {  
		  return jdbcTemplate.query("SELECT * from users", new UserMapper());  
		 }  
		  
		 public User getUserByUserNameOld(String userName) {  
		  User user = null;  
		  
		  if(!StringUtils.isEmpty(userName)) {  
		   List<User> users = jdbcTemplate.query("SELECT * from users where username = ?", new UserMapper(), new Object[] {userName});  
		  
		   if(users != null && users.size() > 0) {  
		    user = users.get(0);  
		   }  
		  }  
		  
		  return user;  
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
		 
		 private static final class UserMapper implements RowMapper<User> {  
		  public User mapRow(ResultSet rs, int rowNum) throws SQLException {  
		   User user = new User();  
		   user.setUserId(rs.getInt("userid"));  
		   user.setGroupId(rs.getInt("groupid"));  
		   user.setUsername(rs.getString("username"));  
		   user.setPassword(rs.getString("password"));  
		   user.setFirstName(rs.getString("firstname"));  
		   user.setMiddleName(rs.getString("middlename"));  
		   user.setLastName(rs.getString("lastname"));  
		   user.setPhoneNumber(rs.getInt("phonenumber"));  
		   user.setVerificationCode(rs.getString("verificationcode"));  
		   user.setResetPasswordCode(rs.getString("resetpasswordcode"));  
		   user.setPasswordQuestion(rs.getString("passwordquestion"));  
		   user.setPasswordAnswer(rs.getString("passwordanswer"));  
		   return user;  
		  }  
		 }  

		 /*
		    public LocalSessionFactoryBean sessionFactory() {
		        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		        sessionFactory.setDataSource(dataSource);
		        sessionFactory.setPackagesToScan(new String[] { "com.websystique.springmvc.model" });
		        sessionFactory.setHibernateProperties(hibernateProperties());
		        return sessionFactory;
		     }		 
		 */
		 public void setDataSource(DataSource dataSource) throws SQLException {  
			 this.dataSource = dataSource;
		  	 this.jdbcTemplate = new JdbcTemplate(this.dataSource);
			 java.sql.Connection conn = dataSource.getConnection();
			 Configuration configuration = new Configuration()
					 .setProperty("hibernate.current_session_context_class", "thread")
					 .addAnnotatedClass(User.class)
					 .configure();
			 
			 SessionFactory sf = configuration
					    .buildSessionFactory(
					        new StandardServiceRegistryBuilder()
					            .applySetting(Environment.DATASOURCE, dataSource)
					            .build());
			 session = sf.openSession();
			 //session.close();
			 
			 //"org.apache.derby.jdbc.EmbeddedDriver"
			 /*
			 Configuration cfg = new Configuration()
			    .addClass(org.hibernate.auction.Item.class)
			    .addClass(org.hibernate.auction.Bid.class)
			    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect")
			    .setProperty("hibernate.connection.datasource", "java:comp/env/jdbc/test")
			    .setProperty("hibernate.order_updates", "true");
			 cfg.setProperty(propertyName, dataSource.getConnection().get)
			 */

			 //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");			 
		  	 /*
			 java.sql.Connection conn = dataSource.getConnection();
			 Session session = sessionFactory.openSession(conn);
			 */
		 }
		  
		 public DataSource getDataSource() {  
		  return dataSource;  
		 }  
		}

