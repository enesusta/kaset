package com.github.enesusta.kaset.service;

import java.io.IOException;

public interface CleanService {
    void add(String currentFilePosition) throws IOException;

    void delete() throws IOException;
}
