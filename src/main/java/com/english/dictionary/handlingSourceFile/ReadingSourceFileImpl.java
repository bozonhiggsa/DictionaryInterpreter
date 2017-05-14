package com.english.dictionary.handlingSourceFile;

import com.english.dictionary.interfaces.ReadingSourceFile;
import java.io.*;
import java.nio.charset.Charset;

/**
 * Implementation of {@link ReadingSourceFile} interface for reading source txt file
 */
public class ReadingSourceFileImpl implements ReadingSourceFile {

    public BufferedReader readFile() {

        File file = new File("src/main/resources/dictionary2.txt");

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("cp1251")));
        return reader;
    }
}
