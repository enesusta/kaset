package com.github.enesusta.kaset.streaming;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Component
public class Mp3ResourceStreamingOutput implements StreamingOutput {

    private final static Logger logger = Logger.getLogger(Mp3ResourceStreamingOutput.class);

    private InputStream inputStream;

    @Override
    public void write(OutputStream outputStream) throws IOException, WebApplicationException {
        logger.info(2 << 13);

        int len;
        byte[] bytes = new byte[2 << 13];

        while ((len = inputStream.read(bytes)) >= 0) outputStream.write(bytes, 0, len);

        outputStream.close();
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
