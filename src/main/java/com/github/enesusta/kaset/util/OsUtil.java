package com.github.enesusta.kaset.util;

import org.springframework.stereotype.Component;

@Component
public class OsUtil {

    private final static String BASE = "/home/phield/mp3";

    public String getCurrentFileLocation(final String nameOfTheFile) {
        return String.format("%s/%s", BASE, nameOfTheFile);
    }
}
