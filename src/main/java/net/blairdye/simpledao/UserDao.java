package net.blairdye.simpledao;

import java.util.List;

public interface UserDao {
	 public List<User> getAllUsers();  
	 public User getUserByUserName(String userName);  
	 public void createUser(User user);  
}
