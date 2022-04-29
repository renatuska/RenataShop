package com.company;

import java.util.ArrayList;
import java.util.Iterator;

public class UserService {
	UserStorage db;

	public UserService(UserStorage db) {
		this.db = db;
	}

	public User getUser(String username, String password) throws Exception {
		User user = this.db.getUser(username);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		} else {
			throw new UserException("Neteisingi prisijungimo duomenys");
		}
	}

	public ArrayList<User> getAllUsers() throws Exception {
		return this.db.getAllUsers();
	}

	public boolean isUserExists(String username) throws Exception {
		User user = this.db.getUser(username);
		return user != null;
	}

	public void addUser(User user) throws Exception {
		this.db.addUser(user);
	}

	public void deleteUserByUsername(String username) throws Exception {
		this.db.deleteUser(username);
	}

	public void deleteAdmin(User adminUser) throws Exception {
		this.validateAdminDeletion();
		this.deleteUserByUsername(adminUser.getUsername());
	}

	private void validateAdminDeletion() throws Exception {
		int adminCount = 0;
		Iterator var2 = this.getAllUsers().iterator();

		while(var2.hasNext()) {
			User user = (User)var2.next();
			if (user.getRole().equals(Role.ADMIN)) {
				++adminCount;
			}
		}

		if (adminCount <= 1) {
			throw new AdminDeletionException("ADMIN vartotojo istrinti negalima nes tai yra vienintelis admin vartotojas sistemoje");
		}
	}

	private User getUserByUsername(String username, ArrayList<User> allUsers) throws UserException {
		Iterator iter = allUsers.iterator();

		User userToDelete;
		do {
			if (!iter.hasNext()) {
				throw new UserException(String.format("Vartotojas su prisijungimo vardu: '%s' neegzistuoja", username));
			}

			userToDelete = (User)iter.next();
		} while(!userToDelete.getUsername().equalsIgnoreCase(username));

		return userToDelete;
	}
}
