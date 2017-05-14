package com.english.dictionary.main;

import com.english.dictionary.dao.SendDictionaryToBdImpl;
import com.english.dictionary.handlingSourceFile.ConvertToSetImpl;
import com.english.dictionary.handlingSourceFile.ReadingSourceFileImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.util.Set;

/**
 * Input point to project for downloading your dictionary into BD
 * @author Ihor Savchenko
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {

        BufferedReader reader = new ReadingSourceFileImpl().readFile();
        Set<String> set = new ConvertToSetImpl().createSet(reader);

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        new SendDictionaryToBdImpl().sendToBd(set, context);
    }
}
