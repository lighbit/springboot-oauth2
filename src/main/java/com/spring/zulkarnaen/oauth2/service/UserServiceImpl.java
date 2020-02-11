package com.spring.zulkarnaen.oauth2.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.spring.zulkarnaen.oauth2.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	private static final AtomicLong counter = new AtomicLong();

	private static List<User> users;

	static {
		users = populateDummyUsers();
	}

	public List<User> findAllUsers() {
		logger.info("FIND ALL USER =============");
		return users;
	}

	public User findById(long id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public User findByName(String name) {
		logger.info("FIND BY NAME =============");
		for (User user : users) {
			if (user.getName().equalsIgnoreCase(name)) {
				return user;
			}
		}
		return null;
	}

	public void saveUser(User user) {
		logger.info("SAVE NEW USER =============");
		user.setId(counter.incrementAndGet());
		users.add(user);
	}

	public void updateUser(User user) {
		logger.info("UPDATE USER =============");
		int index = users.indexOf(user);
		users.set(index, user);
	}

	public void deleteUserById(long id) {
		logger.info("DELETE USER BY ID =============");

		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
			}
		}
	}

	public boolean isUserExist(User user) {
		logger.info("USER ALREADY EXIST =============");
		return findByName(user.getName()) != null;
	}

	public void deleteAllUsers() {
		logger.info("DELETE ALL USER =============");
		users.clear();
	}

	private static List<User> populateDummyUsers() {
		logger.info("CALL POPULATE DUMMY USER =============");
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(), "Sam", 30, 70000));
		users.add(new User(counter.incrementAndGet(), "Tom", 40, 50000));
		users.add(new User(counter.incrementAndGet(), "Jerome", 45, 30000));
		users.add(new User(counter.incrementAndGet(), "Silvia", 50, 40000));
		return users;
	}

}
