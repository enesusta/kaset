package com.github.enesusta.kaset.service;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class DefaultStreamService implements StreamService {

    private final static Logger logger = Logger.getLogger(DefaultStreamService.class);

    @Override
    public InputStream stream(final String currentFileLocation) {

        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(new File(currentFileLocation));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return inputStream;
    }
}
