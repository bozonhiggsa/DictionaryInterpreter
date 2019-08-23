package com.english.dictionary.dao;

import com.english.dictionary.handlingSourceFile.ExtractWordsImpl;
import com.english.dictionary.interfaces.SendDictionaryToBd;
import com.english.dictionary.interfaces.SendWordEngToBdService;
import com.english.dictionary.models.WordEng;
import com.english.dictionary.models.WordRus;
import org.springframework.context.ApplicationContext;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Implementation of {@link SendDictionaryToBd} for send the dictionary into BD
 * @author Ihor Savchenko
 * @version 1.0
 */
public class SendDictionaryToBdImpl implements SendDictionaryToBd {

    private static final String ENCODING = "UTF-8";
    private static final String FILENAME = "english_words.txt";
    private static final String lineSeparator = System.lineSeparator();

    private Extractor instance = Extractor.getInstance();

    public void sendToBd(Set<String> setStringWithWords, ApplicationContext context){

        OutputStream outputStream = getOutputStream();
        Writer writer = getWriter(outputStream);

        for (String setStrings: setStringWithWords) {

            LinkedHashSet<String> words = new ExtractWordsImpl().extractWords(setStrings);

            WordEng wordEng = this.instance.extractWordEng(words);

            if(writer != null) {
                try {
                    writer.write(wordEng.getWord() + lineSeparator);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            LinkedHashSet<WordRus> wordsRus = this.instance.extractWordsRus(words, wordEng);
            wordEng.setWordsRus(wordsRus);

            SendWordEngToBdService sendWordEngToBdService = (SendWordEngToBdService) context.getBean("sendEngWordService");
            sendWordEngToBdService.sendWordEngToBd(wordEng);
        }

        if(writer != null){
            try {
                writer.flush();
                writer.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private OutputStream getOutputStream() {

        try {
            return new FileOutputStream(String.format("%s%s", this.getClass()
                    .getResource("/").getPath(), FILENAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Writer getWriter(OutputStream os) {

        if(os != null){
            try {
                return new OutputStreamWriter(os, ENCODING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
