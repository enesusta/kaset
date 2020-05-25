package com.github.enesusta.kaset.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;

@Service
public class DefaultCleanService implements CleanService {

    private final static Queue<File> resourceQueue = new ArrayDeque<>(5);

    @Override
    public final void add(final String currentFilePosition) throws IOException {
        resourceQueue.add(new File(currentFilePosition));
    }

    @Override
    public final void delete() throws IOException {
        Optional<File> optionalFile = Optional.ofNullable(resourceQueue.poll());
        optionalFile.ifPresent(File::delete);
    }
}


