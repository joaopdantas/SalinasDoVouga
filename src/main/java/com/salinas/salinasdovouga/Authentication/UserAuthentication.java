package com.salinas.salinasdovouga.Authentication;

import com.salinas.salinasdovouga.Model.User;
import com.salinas.salinasdovouga.Users.UserType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserAuthentication {

    private static List<User> users = loadUsers();

    private static List<User> loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.ser"))) {
            return (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No user data found. Initializing with default admin user.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.ser"))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        if (users.isEmpty()) {
            users.add(new User("admin", "admin", "Admin", "123456789", "admin@salinas.com", "Admin City", "Admin Locality", "Braga", UserType.ADMIN));
            saveUsers();
        }
    }

    public static boolean registerUser(User user) {
        // Check if the username is already taken
        if (getUserByUsername(user.getUsername()) == null) {
            users.add(user);
            saveUsers();
            return true; // Registration successful
        }
        return false; // Username already exists
    }

    public static User loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user; // Login successful
            }
        }
        return null; // Username or password incorrect
    }

    private static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void deleteUser(String username) {
        User userToDelete = getUserByUsername(username);
        if (userToDelete != null) {
            users.remove(userToDelete);
            saveUsers();
        }
    }
}
