package com.salinas.salinasdovouga.Util;

import com.salinas.salinasdovouga.Authentication.UserAuthentication;
import com.salinas.salinasdovouga.Model.User;
import com.salinas.salinasdovouga.Users.UserType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedSimulation {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5); // Create a pool with 5 threads

        // Simulate user actions
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                simulateUserActions();
            });
        }

        // Shutdown the executor when done
        executorService.shutdown();
    }

    private static void simulateUserActions() {
        // Simulate user actions (e.g., login, registration, etc.)
        User newUser = new User("user", "password", "User",
                "123456789", "123456789", "City", "Rua X", "Region", UserType.CUSTOMER);

        // Simulate registration
        boolean registrationResult = UserAuthentication.registerUser(newUser);
        System.out.println(Thread.currentThread().getName() + " registration result: " + registrationResult);

        // Simulate login
        User loginUser = UserAuthentication.loginUser(newUser.getUsername(), newUser.getPassword());
        System.out.println(Thread.currentThread().getName() + " login result: " + (loginUser != null));
    }
}

