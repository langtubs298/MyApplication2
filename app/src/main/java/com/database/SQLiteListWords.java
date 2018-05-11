package com.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.entity.Word;

import java.util.ArrayList;

/**
 * Created by Luong Vien on 13.01.2018.
 */

public class SQLiteListWords extends SQLiteDataController {
    public SQLiteListWords(Context con) {
        super(con);
    }

    public ArrayList<Word> getListWord(int num){
        ArrayList<Word> listWord = new ArrayList<>();
        String tmp = String.valueOf(num);
        try{
            openDataBase();
            Cursor cs = database.rawQuery("select id, word, pronoun, mean, ok from PRONOUN"+tmp, null);
            Word word;
            while (cs.moveToNext()){
                word = new Word(cs.getInt(0),cs.getString(1), cs.getString(2), cs.getString(3), cs.getString(4));
                listWord.add(word);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return listWord;
    }
    public void updateTick(int pronunc, int id, String text){
        String tmp = String.valueOf(pronunc);
        String tmp1 = String.valueOf(id);
        try{
            openDataBase();
            ContentValues values = new ContentValues();
            values.put("ok", "ok");
            database.update("PRONOUN"+tmp,values,"id=?",new String[]{tmp1});
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
    }

    public ArrayList<Word> getListEsdWord(int id){
        String tmp = String.valueOf(id);
        ArrayList<Word> listWord = new ArrayList<>();
        try{
            openDataBase();
            Cursor cs = database.rawQuery("select id, word, pronoun, mean, ok from RULE"+tmp, null);
            Word word;
            while (cs.moveToNext()){
                word = new Word(cs.getInt(0),cs.getString(1), cs.getString(2), cs.getString(3), cs.getString(4));
                listWord.add(word);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return listWord;
    }

    public void updateTickEsd(int pronunc, int id, String text){
        String tmp = String.valueOf(pronunc);
        String tmp1 = String.valueOf(id);
        try{
            openDataBase();
            ContentValues values = new ContentValues();
            values.put("ok", "ok");
            database.update("RULE"+tmp,values,"id=?",new String[]{tmp1});
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
    }

    public void updateTickStressWord(int id, String text){
        String tmp1 = String.valueOf(id);
        try{
            openDataBase();
            ContentValues values = new ContentValues();
            values.put("ok", "ok");
            database.update("STRESS_WORD",values,"id=?",new String[]{tmp1});
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
    }

    public ArrayList<Word> getListStressWord(){
        ArrayList<Word> listWord = new ArrayList<>();
        try{
            openDataBase();
            Cursor cs = database.rawQuery("select id, word, pronoun, mean, ok from STRESS_WORD", null);
            Word word;
            while (cs.moveToNext()){
                word = new Word(cs.getInt(0),cs.getString(1), cs.getString(2), cs.getString(3), cs.getString(4));
                listWord.add(word);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return listWord;
    }

}
