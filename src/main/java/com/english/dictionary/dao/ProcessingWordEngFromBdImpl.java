package com.english.dictionary.dao;

import com.english.dictionary.interfaces.ProcessingWordEngFromBd;
import com.english.dictionary.models.WordEng;
import com.english.dictionary.models.WordRus;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import javax.persistence.NoResultException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

/**
 * Implementation of {@link ProcessingWordEngFromBd} interface to process one English Word or English phrase and
 * associated Russian Words from BD. Implementation both MySQL and PostgreSQL
 * @author Ihor Savchenko
 * @version 1.0
 */
@Repository
public class ProcessingWordEngFromBdImpl implements ProcessingWordEngFromBd {

    private static final Logger logger = LoggerFactory.getLogger(ProcessingWordEngFromBdImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public WordEng selectWordEngFromBd() {
        Session session = this.sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM english_words AS EW WHERE EW.mark = 0 ORDER BY RAND() LIMIT 1");
        return processingTemplate(sqlQuery);
    }

    public WordEng selectWordEngForRepeat() {
        Session session = this.sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM english_words AS EW WHERE EW.mark = 1 AND EW.done = 0 ORDER BY RAND() LIMIT 1");
        return processingTemplate(sqlQuery);
    }

    public LinkedList<WordEng> selectListWordEngFromBd(WordEng wordEng) {
        if(wordEng == null){
            return new LinkedList<WordEng>();
        }
        Session session = this.sessionFactory.getCurrentSession();
        LinkedList<WordEng> wordEngList = new LinkedList<WordEng>();
        Set<WordRus> set = wordEng.getWordsRus();
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (WordRus wordRus: set) {
            int id = wordRus.getId();
            SQLQuery sqlQuery = session.createSQLQuery(String.format("%s%s","SELECT id_eng FROM third_table WHERE id_rus = ", id));
            ArrayList<Integer> tempList = (ArrayList<Integer>) sqlQuery.list();
            logger.info("Indexes of english words for one russian word is: " + tempList.toString());
            list = removeUniqueElements(tempList, list);
        }
        logger.info("Final indexes of english words is: " + list.toString());
        logger.info("Start loading the list of english words");

        for (Integer id: list) {
            SQLQuery sqlQuery = session.createSQLQuery(String.format("%s%s","SELECT * FROM english_words WHERE id = ", id));
            sqlQuery.addEntity(WordEng.class);
            WordEng wordEngFromList = (WordEng) sqlQuery.getSingleResult();
            logger.info("One english word successfully loaded: " + wordEngFromList.getWord());
            wordEngList.add(wordEngFromList);
        }
        logger.info("List of english words successfully loaded");
        return wordEngList;
    }

    public void updateWordEngFromBd(WordEng wordEngFromBd) {
        Session session = this.sessionFactory.getCurrentSession();
        if(!wordEngFromBd.isMark()){
        wordEngFromBd.setMark(true);
        } else {
            wordEngFromBd.setDone(true);
        }
        session.update(wordEngFromBd);
        logger.info("One english word successfully done and count updated: " + wordEngFromBd.getWord());
    }

    public int selectCountAll() {
        Session session = this.sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT COUNT(id) FROM english_words");
        BigInteger countAll = (BigInteger) sqlQuery.getSingleResult();
        logger.info("Counter ALL records was getting: " + countAll);
        return countAll.intValue();
    }

    public int selectCountRemain() {
        Session session = this.sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT COUNT(id) FROM english_words AS EW WHERE EW.mark = 0");
        BigInteger countRemain = (BigInteger) sqlQuery.getSingleResult();
        logger.info("Counter remaining records was getting: " + countRemain);
        return countRemain.intValue();
    }

    public int selectCountForDone() {
        Session session = this.sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT COUNT(id) FROM english_words AS EW WHERE EW.mark = 1 AND EW.done = 0");
        BigInteger countForDone = (BigInteger) sqlQuery.getSingleResult();
        logger.info("Counter of records for done was getting: " + countForDone);
        return countForDone.intValue();
    }

    public void resetMark() {
        Session session = this.sessionFactory.getCurrentSession();
        session.createSQLQuery("UPDATE english_words AS EW SET EW.mark = 0, EW.done = 0").executeUpdate();
        logger.info("Counter was reset");
    }

    private LinkedList<Integer> removeUniqueElements(ArrayList<Integer> tempList, LinkedList<Integer> list){

        LinkedList<Integer> listOut = new LinkedList<Integer>();
        if(list.isEmpty()){
            for(int i = 0; i < tempList.size(); i++) {
                listOut.add(tempList.get(i));
            }
        }
        else {
            for (Integer ind : tempList) {
                if(list.contains(ind)){
                    listOut.add(ind);
                }
            }
        }
        return listOut;
    }

    private WordEng processingTemplate(SQLQuery sqlQuery){

        sqlQuery.addEntity(WordEng.class);
        WordEng wordEng = null;
        try {
            wordEng = (WordEng) sqlQuery.getSingleResult();
        } catch (NoResultException e){
            logger.info("All words from dictionary translated: " + e);

        }
        if(wordEng != null){
            logger.info("One english word successfully loaded: " + wordEng.getWord());
        }
        return wordEng;
    }
}
