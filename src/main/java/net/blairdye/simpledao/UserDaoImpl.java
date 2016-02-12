package net.blairdye.simpledao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.StringUtils;

public class UserDaoImpl {  
		 private DataSource dataSource;  
		 private JdbcTemplate jdbcTemplate;  
		  
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
		  
		 public User getUserByUserName(String userName) {  
		  User user = null;  
		  
		  if(!StringUtils.isEmpty(userName)) {  
		   List<User> users = jdbcTemplate.query("SELECT * from users where username = ?", new UserMapper(), new Object[] {userName});  
		  
		   if(users != null && users.size() > 0) {  
		    user = users.get(0);  
		   }  
		  }  
		  
		  return user;  
		 }  
		  
		 private static final class UserMapper implements RowMapper<User> {  
		  public User mapRow(ResultSet rs, int rowNum) throws SQLException {  
		   User user = new User();  
		   user.setUserId(rs.getInt("user_id"));  
		   user.setGroupId(rs.getInt("group_id"));  
		   user.setUsername(rs.getString("username"));  
		   user.setPassword(rs.getString("password"));  
		   user.setFirstName(rs.getString("first_name"));  
		   user.setMiddleName(rs.getString("middle_name"));  
		   user.setLastName(rs.getString("last_name"));  
		   user.setPhoneNumber(rs.getInt("phone_number"));  
		   user.setVerificationCode(rs.getString("verification_code"));  
		   user.setResetPaswordCode(rs.getString("reset_pasword_code"));  
		   user.setPasswordQuestion(rs.getString("password_question"));  
		   user.setPasswordAnswer(rs.getString("password_answer"));  
		   return user;  
		  }  
		 }  
		  
		 public void setDataSource(DataSource dataSource) {  
		  this.dataSource = dataSource;  
		  this.jdbcTemplate = new JdbcTemplate(this.dataSource);  
		 }  
		  
		 public DataSource getDataSource() {  
		  return dataSource;  
		 }  
		}

