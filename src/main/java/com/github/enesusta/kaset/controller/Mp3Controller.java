package com.github.enesusta.kaset.controller;

import com.github.enesusta.kaset.streaming.Mp3ResourceStreamingOutput;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;

@RestController
@Path("/mp3")
public class Mp3Controller {

    private final static Logger logger = Logger.getLogger(Mp3Controller.class);

    @Autowired
    Mp3ResourceStreamingOutput mp3ResourceStreamingOutput;

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getStream(@QueryParam("url") String url) {

        logger.info("Url is " + url);
        logger.info("react istek atayor.");

        return Response
                .ok(mp3ResourceStreamingOutput)
                .header("Access-Control-Allow-Origin", "http://localhost:3000")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .header("Content-Disposition", "inline;")
                .build();
    }

    //@GetMapping
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
            while ((str = br.readLine()) != null) stack.push(str);

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
