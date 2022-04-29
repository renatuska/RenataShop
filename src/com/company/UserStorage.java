package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface UserStorage {
    User getUser(String var1) throws Exception;

    ArrayList<User> getAllUsers() throws FileNotFoundException, Exception;

    void addUser(User var1) throws IOException, Exception;

    void deleteUser(String var1) throws Exception;
}
