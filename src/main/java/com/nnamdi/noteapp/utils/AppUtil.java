package com.nnamdi.noteapp.utils;

import java.util.UUID;

public enum AppUtil {
    INSTANCE;
    private AppUtil() {}

    public static String generateUUIDString() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "").toUpperCase();
    }

    public  static  boolean stringIsNullOrEmpty(String arg) {
        if ((arg == null)) return true;
        else
            return (arg.isEmpty()) || (arg.trim().isEmpty());
    }
}
