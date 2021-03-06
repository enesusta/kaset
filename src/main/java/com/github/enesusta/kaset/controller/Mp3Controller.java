package com.github.enesusta.kaset.controller;

import com.github.enesusta.kaset.service.FileQueueService;
import com.github.enesusta.kaset.service.DownloadService;
import com.github.enesusta.kaset.service.PlaylistDownloadService;
import com.github.enesusta.kaset.streaming.Mp3ResourceStreamingOutput;
import com.github.enesusta.kaset.util.OsUtil;
import org.jboss.logging.Logger;
import org.jboss.resteasy.spi.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@RestController
@Path("/mp3")
@RequestMapping("/mp3")
public class Mp3Controller {

    private final static Logger logger = Logger.getLogger(Mp3Controller.class);

    @Autowired
    DownloadService downloadService;

    @Autowired
    PlaylistDownloadService playlistDownloadService;

    @Autowired
    FileQueueService fileQueueService;

    @Autowired
    Mp3ResourceStreamingOutput mp3ResourceStreamingOutput;

    @Autowired
    OsUtil osUtil;

    @Context
    HttpResponse httpResponse;

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getStream(@QueryParam("url") String url) {

        if (url == null) {

            logger.info("burada");

            throw new WebApplicationException(
                    Response
                            .status(Response.Status.NO_CONTENT)
                            .entity("Url parameter is mandatory")
                            .build()
            );
        } else {

            logger.info("else'te");

            final String nameOfTheFile = downloadService.downloadTheFileAndGetItsName(url);
            final String currentFileLocation = osUtil.getCurrentFileLocation(nameOfTheFile);
            final String attachment = String.format("attachment; filename=%s", nameOfTheFile);

            fileQueueService.add(currentFileLocation);

            return Response
                    .ok(mp3ResourceStreamingOutput)
                    .header("Access-Control-Allow-Origin", "http://localhost:3000")
                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .header("Access-Control-Max-Age", "1209600")
                    .header("Content-Disposition", attachment)
                    .build();
        }
    }

    @GetMapping("/playlist")
    public Set<String> getPlaylistUrls(@RequestParam String url) {
        return playlistDownloadService.getUrlsFromPlaylist(url);
    }

}
