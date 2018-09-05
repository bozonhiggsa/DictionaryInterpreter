package com.english.dictionary.main;

import com.english.dictionary.dao.SendDictionaryToBdImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.TreeSet;

/**
 * Input point to project for downloading your dictionary into BD
 * @author Ihor Savchenko
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        TreeSet<String> set = new TreeSet<String>();
        Path path = Paths.get("src/main/resources/dictionary.txt");
        try {
            List<String> strings = Files.readAllLines(path, Charset.forName("UTF-8"));
            for (String s: strings) {
                set.add(s.toLowerCase());
            }
            new SendDictionaryToBdImpl().sendToBd(set, context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
