package com.github.enesusta.kaset.streaming;

import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

@Component
public class Mp3ResourceStreamingOutput implements StreamingOutput {

    @Override
    public void write(OutputStream outputStream) throws IOException, WebApplicationException {
        try {
            for (int i = 0; i < 10; i++) {
                outputStream.write(String.format("Hello %d\n", i).getBytes());
                outputStream.flush();
                TimeUnit.MILLISECONDS.sleep(500);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
