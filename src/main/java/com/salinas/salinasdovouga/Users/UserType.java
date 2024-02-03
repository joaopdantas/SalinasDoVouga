package com.salinas.salinasdovouga.Users;

import java.util.Arrays;
import java.util.List;

public enum UserType {
    CUSTOMER,
    PRODUCTION_MANAGER,
    SALES_MANAGER,
    ADMIN;

    public static List<UserType> getAllTypes() {
        return Arrays.asList(values());
    }
}

