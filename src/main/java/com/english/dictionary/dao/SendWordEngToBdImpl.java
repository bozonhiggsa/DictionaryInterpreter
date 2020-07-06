package com.english.dictionary.dao;

import com.english.dictionary.interfaces.SendWordEngToBd;
import com.english.dictionary.models.WordEng;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Implementation of {@link SendWordEngToBd} interface for send one English Word or English phrase
 * and associated Russian Words from the dictionary into BD
 * @author Ihor Savchenko
 * @version 1.0
 */
@Repository
public class SendWordEngToBdImpl implements SendWordEngToBd {

    private static final Logger logger2 = LoggerFactory.getLogger(SendWordEngToBdImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void sendWordEngToBd(WordEng wordEng) {

        Session session = this.sessionFactory.getCurrentSession();
        session.save(wordEng);
        logger2.info("Set of words successfully saved: " + wordEng);

    }
}
