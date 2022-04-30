package com.company;

import static com.company.Role.ADMIN;
import java.util.ArrayList;
import java.util.Iterator;

public class UserService {

	UserStorage db;

	public UserService(UserStorage db) {
		this.db = db;
	}

	public User getUser(String username, String password) throws Exception {
		User user = db.getUser(username);
		if(user != null && user.getPassword().equals(password)) {
			return user;
		}
		throw new UserException("Neteisingi prisijungimo duomenys");
	}

	public ArrayList<User> getAllUsers() throws Exception {
		return db.getAllUsers();
	}

	public boolean isUserExists(String username) throws Exception {
		User user = db.getUser(username);
		if(user == null) return false;
		else return true;
	}

	public void addUser(User user) throws Exception {
		db.addUser(user);
	}

	public void deleteUserByUsername(String username) throws Exception {
		db.deleteUser(username);
	}

	public void deleteAdmin(User adminUser) throws Exception {
		validateAdminDeletion();
		deleteUserByUsername(adminUser.getUsername());
	}

	private void validateAdminDeletion() throws Exception {
		int adminCount = 0;
		for (User user : getAllUsers()) {

			if (user.getRole().equals(ADMIN)) {
				adminCount++;
			}
		}
		if (adminCount <= 1) {
			throw new AdminDeletionException("ADMIN vartotojo istrinti negalima nes tai yra vienintelis admin vartotojas sistemoje");
		}
	}


	private User getUserByUsername(String username, ArrayList<User> allUsers) throws UserException {
		Iterator<User> iter = allUsers.iterator();

		while (iter.hasNext()) {

			User userToDelete = iter.next();
			if (userToDelete.getUsername().equalsIgnoreCase(username)) {
				return userToDelete;
			}
		}
		throw new UserException(String.format("Vartotojas su prisijungimo vardu: \'%s\' neegzistuoja", username));
	}
}
