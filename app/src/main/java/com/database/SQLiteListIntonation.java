package com.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.entity.Rule;
import com.entity.Sentence;

import java.util.ArrayList;

/**
 * Created by Luong Vien on 22.01.2018.
 */

public class SQLiteListIntonation extends SQLiteDataController {
    public SQLiteListIntonation(Context con) {
        super(con);
    }
    public ArrayList<Sentence> getListSentence(int num){
        String tmp = String.valueOf(num);
        ArrayList<Sentence> listSentence = new ArrayList<>();
        try{
            openDataBase();
            Cursor cs = database.rawQuery("select id, sentence from INTONATION"+tmp, null);
            Sentence sentence;
            while (cs.moveToNext()){
                sentence = new Sentence(cs.getInt(0), cs.getString(1));
                listSentence.add(sentence);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return listSentence;
    }

    public ArrayList<Rule> getListEsdSentence(){
        ArrayList<Rule> listSentence = new ArrayList<>();
        try{
            openDataBase();
            Cursor cs = database.rawQuery("select id, content1, content2 from ENDINGRULE", null);
            Rule rule;
            while (cs.moveToNext()){
                rule = new Rule(cs.getInt(0), cs.getString(1), cs.getString(2));
                listSentence.add(rule);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return listSentence;
    }

    public ArrayList<Sentence> getListAskSentence(String tmp){
        ArrayList<Sentence> listSentence = new ArrayList<>();
        try{
            openDataBase();
            Cursor cs = database.rawQuery("select id, content from "+tmp, null);
            Sentence sentence;
            while (cs.moveToNext()){
                sentence = new Sentence(cs.getInt(0), cs.getString(1));
                listSentence.add(sentence);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return listSentence;
    }

    public ArrayList<Rule> getListLinkingSentence(){
        ArrayList<Rule> listSentence = new ArrayList<>();
        try{
            openDataBase();
            Cursor cs = database.rawQuery("select id, content, spelling from LINKING", null);
            Rule rule;
            while (cs.moveToNext()){
                rule = new Rule(cs.getInt(0), cs.getString(1), cs.getString(2));
                listSentence.add(rule);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return listSentence;
    }

    public ArrayList<Sentence> getListStress(){
        ArrayList<Sentence> listSentence = new ArrayList<>();
        try{
            openDataBase();
            Cursor cs = database.rawQuery("select id, content from STRESS", null);
            Sentence sentence;
            while (cs.moveToNext()){
                sentence = new Sentence(cs.getInt(0), cs.getString(1));
                listSentence.add(sentence);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return listSentence;
    }

    public ArrayList<Sentence> getGuideIntonation(){
        ArrayList<Sentence> listSentence = new ArrayList<>();
        try{
            openDataBase();
            Cursor cs = database.rawQuery("select id, content from GUIDE_INTONATION", null);
            Sentence sentence;
            while (cs.moveToNext()){
                sentence = new Sentence(cs.getInt(0), cs.getString(1));
                listSentence.add(sentence);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return listSentence;
    }

    public ArrayList<Sentence> getListSentenceStress(){
        ArrayList<Sentence> listSentence = new ArrayList<>();
        try{
            openDataBase();
            Cursor cs = database.rawQuery("select id, content from STRESS_SENTENCE", null);
            Sentence sentence;
            while (cs.moveToNext()){
                sentence = new Sentence(cs.getInt(0), cs.getString(1));
                listSentence.add(sentence);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return listSentence;
    }
}
