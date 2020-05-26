package com.github.enesusta.kaset.service;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

@Service
public class DefaultPlaylistDownloadService implements PlaylistDownloadService {

    private final static Logger logger = Logger.getLogger(DefaultPlaylistDownloadService.class);

    @Override
    public Set<String> getUrlsFromPlaylist(final String url) {

        final Set<String> treeSet = new TreeSet<>();
        final String userDir = System.getProperty("user.dir");
        System.out.println("userDir = " + userDir);
        final String command = String.format("/usr/bin/python3 %s/main.py %s", userDir, url.trim());

        logger.info("girdi");
        logger.info("url is " + url);
        logger.info("command is :" + command);

        try {

            final Process process = Runtime.getRuntime().exec(command);
            final InputStream is = process.getInputStream();
            final InputStreamReader isr = new InputStreamReader(is);
            final BufferedReader bufferedReader = new BufferedReader(isr);
            final BufferedReader bre = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String string;
            while ((string = bufferedReader.readLine()) != null) {
                System.out.println(string);
                treeSet.add(string);
            }
            bufferedReader.close();

            while ((string = bre.readLine()) != null) {
                System.out.println(string);
            }

            bre.close();
            process.waitFor();
            process.destroy();

        } catch (IOException e) {
            logger.error(e.getMessage());
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }

        return treeSet;
    }
}
