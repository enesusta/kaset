package com.github.enesusta.kaset.controller;

import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;

@RestController
@RequestMapping("/mp3")
public class Mp3Controller {

    private final static Logger logger = Logger.getLogger(Mp3Controller.class);

    @GetMapping
    public byte[] retrieveMp3File(@RequestParam String url) {

        Stack<String> stack = new Stack<>();

        logger.info("url is " + url);

        final String command =
                String.format("%s %s %s %s",
                        "youtube-dl --yes-playlist",
                        "--extract-audio --audio-format mp3",
                        "--audio-quality 0",
                        url);


        try {

            logger.info(command);

            Process process = Runtime.getRuntime().exec(command);

            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String str;
            while ((str = br.readLine()) != null) {
                stack.push(str);
                System.out.println(str);
            }

            System.out.println("---");

            stack.pop();
            final String destinationOfFile = stack.pop().substring(22);
            final String[] urls = destinationOfFile.split("/");
            final String nameOfFile = urls[4];
            System.out.println("nameOfFile = " + nameOfFile);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}
