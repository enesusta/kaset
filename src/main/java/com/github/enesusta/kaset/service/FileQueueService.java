package com.github.enesusta.kaset.service;

import java.io.File;

public interface FileQueueService {
    void add(String currentFilePosition);

    File getFileInProgress();

    void delete();
}
