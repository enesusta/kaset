package com.github.enesusta.kaset.streaming;

import com.github.enesusta.kaset.domain.Kaset;
import com.github.enesusta.kaset.mock.MockResource;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Component
public class Mp3ResourceStreamingOutput implements StreamingOutput {

    private final static Logger logger = Logger.getLogger(Mp3ResourceStreamingOutput.class);

    private Kaset kaset;

    @Autowired
    MockResource mockResource;

    @Override
    public void write(OutputStream outputStream) throws IOException, WebApplicationException {

        logger.info(2 << 13);
        int len;

        try (InputStream inputStream = mockResource.inputStream()) {

            byte[] bytes = new byte[2 << 13];
            while ((len = inputStream.read(bytes)) >= 0) outputStream.write(bytes, 0, len);

            outputStream.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void setKaset(Kaset kaset) {
        this.kaset = kaset;
    }
}
