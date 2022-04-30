package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface UserStorage {
    User getUser(String username) throws Exception;
    ArrayList<User> getAllUsers() throws FileNotFoundException, Exception;
    void addUser(User user) throws IOException, Exception;
    void deleteUser(String username) throws Exception;
}
