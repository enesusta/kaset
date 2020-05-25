package com.github.enesusta.kaset.service;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;

@Service
public class DefaultDownloadService implements DownloadService {

    private final static Logger logger = Logger.getLogger(DefaultDownloadService.class);

    @Override
    public String downloadTheFileAndGetItsName(final String url) {

        final Stack<String> stack = new Stack<>();
        final String command =
                String.format("%s %s %s %s",
                        "youtube-dl --yes-playlist",
                        "--extract-audio --audio-format mp3",
                        "--audio-quality 0",
                        url);

        String nameOfTheFile = null;

        try {

            final Process process = Runtime.getRuntime().exec(command);
            final InputStream is = process.getInputStream();
            final InputStreamReader isr = new InputStreamReader(is);
            final BufferedReader bufferedReader = new BufferedReader(isr);

            String string;
            while ((string = bufferedReader.readLine()) != null) {
                System.out.println(string);
                stack.push(string);
            }

            stack.pop();

            final String destinationOfTheFile = stack.pop().substring(22);
            final String[] patterns = destinationOfTheFile.split("/");
            nameOfTheFile = patterns[4];


        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return nameOfTheFile;
    }
}
