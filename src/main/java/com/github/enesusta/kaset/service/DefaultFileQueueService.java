package com.github.enesusta.kaset.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;

@Service
public class DefaultFileQueueService implements FileQueueService {

    private final static Queue<File> resourceQueue = new ArrayDeque<>(2);

    @Override
    public final void add(final String currentFilePosition) {
        resourceQueue.add(new File(currentFilePosition));
    }

    @Override
    public final File getFileInProgress() {
        return resourceQueue.element();
    }

    @Override
    public final void delete() {
        Optional<File> optionalFile = Optional.ofNullable(resourceQueue.poll());
        optionalFile.ifPresent(File::delete);
    }
}


