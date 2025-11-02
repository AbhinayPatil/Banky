package com.abhinay.bankapp.bankapp.filter.encryptionFilter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class ResponseWrapper extends HttpServletResponseWrapper {

    private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private final PrintWriter writer = new PrintWriter(buffer);

    public ResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {
                // No-op
            }

            @Override
            public void write(int b) throws IOException {
                buffer.write(b);
            }
        };
    }

    @Override
    public PrintWriter getWriter() {
        return writer;
    }

    /**
     * Returns the captured response body as a string.
     */
    public String getCapturedResponseBody() {
        writer.flush(); // Ensure all data is written to the buffer
        return buffer.toString(StandardCharsets.UTF_8);
    }
}
