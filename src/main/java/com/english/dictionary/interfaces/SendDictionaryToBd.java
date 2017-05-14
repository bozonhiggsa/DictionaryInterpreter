package com.english.dictionary.interfaces;

import org.springframework.context.ApplicationContext;
import java.util.Set;

/**
 * Interface for send the dictionary into BD
 * @author Ihor Savchenko
 * @version 1.0
 */
public interface SendDictionaryToBd {

    void sendToBd(Set<String> setStringWithWords, ApplicationContext context);
}
