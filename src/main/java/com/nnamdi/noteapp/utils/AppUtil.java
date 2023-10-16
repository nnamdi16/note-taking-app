package com.nnamdi.noteapp.utils;

import com.nnamdi.noteapp.exceptions.BadRequestException;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
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

    public static void validatePageRequest(int page, int size) {
        log.info("about to validate  page request ");
        if(page < 1 || size <  1) {
            throw new BadRequestException("page and size must be positive and not less than 1");
        }
    }
}
