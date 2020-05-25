package com.github.enesusta.kaset.streaming;

import com.github.enesusta.kaset.service.FileQueueService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Component
public class Mp3ResourceStreamingOutput implements StreamingOutput {

    private final static Logger logger = Logger.getLogger(Mp3ResourceStreamingOutput.class);

    @Autowired
    FileQueueService fileQueueService;

    @Override
    public void write(final OutputStream outputStream) throws IOException, WebApplicationException {
        final InputStream inputStream = new FileInputStream(fileQueueService.getFileInProgress());

        int len;
        byte[] bytes = new byte[2 << 13];

        while ((len = inputStream.read(bytes)) >= 0) outputStream.write(bytes, 0, len);

        outputStream.close();
        fileQueueService.delete();
    }

}
