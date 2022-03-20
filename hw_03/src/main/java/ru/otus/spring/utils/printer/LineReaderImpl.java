package ru.otus.spring.utils.printer;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;

@Service
public class LineReaderImpl implements LineReader {

    private final BufferedReader in;

    public LineReaderImpl(BufferedReader in) {
        this.in = in;
    }

    @Override
    public String readLine() throws IOException {
        String line = null;
        StringBuilder rslt = new StringBuilder();
        if ((line = in.readLine()) != null) {
            rslt.append(line);
        }
        return rslt.toString();
    }
}
