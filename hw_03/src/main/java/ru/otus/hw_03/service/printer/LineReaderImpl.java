package ru.otus.hw_03.service.printer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class LineReaderImpl implements LineReader {

    private final BufferedReader in;

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
