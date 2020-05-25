package com.github.enesusta.kaset.mock;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Component
public class MockResource {

    private static final String BASE = "/home/phield/mp3";

    public InputStream inputStream() {

        File file = new File(String.format(String.format("%s/Leonard Cohen - Dance Me To The End Of Love.mp3", BASE)));
        InputStream is = null;

        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return is;
    }

}
