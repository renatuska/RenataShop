import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUserStorage implements UserStorage {
    private final String filePath;

    public FileUserStorage(String filePath) {
        this.filePath = filePath;
    }

    public User getUser(String username) throws Exception {
        ArrayList<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<User> getAllUsers() throws Exception {
        File file = new File(this.filePath);
        Scanner sc = new Scanner(file);
        ArrayList<User> users = new ArrayList<>();

        while(sc.hasNextLine()) {
            String username = sc.nextLine();
            String password = sc.nextLine();
            String role = sc.nextLine();
            String name = sc.nextLine();
            String surname = sc.nextLine();
            String address = sc.nextLine();
            int age = sc.nextInt();
            sc.nextLine();
            sc.nextLine();
            users.add(new User(username, password, Role.valueOf(role), name, surname, address, age));
        }

        return users;
    }

    public void addUser(User user) throws Exception {
        FileWriter fw = new FileWriter(this.filePath, true);
        PrintWriter writer = new PrintWriter(fw);
        this.writeUser(writer, user);
        writer.close();
    }

    public void deleteUser(String username) throws Exception {
        ArrayList<User> users = getAllUsers();

        boolean found = false;
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                users.remove(user);
                found = true;
                break;
            }
        }

        //lets rewrite
        if(found) {
            FileWriter fw = new FileWriter(filePath);
            PrintWriter writer = new PrintWriter(fw);
            for (User u : users) {
                writeUser(writer, u);
            }
            writer.close();
        }

    }

    private void writeUser(PrintWriter writer, User user) {
        writer.println(user.getUsername());
        writer.println(user.getPassword());
        writer.println(user.getRole());
        writer.println(user.getName());
        writer.println(user.getSurname());
        writer.println(user.getAddress());
        writer.println(user.getAge());
        writer.println();
    }
}