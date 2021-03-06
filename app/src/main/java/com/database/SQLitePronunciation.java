package com.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.entity.Pronunciation;

import java.util.ArrayList;

/**
 * Created by Luong Vien on 02.09.2017.
 */

public class SQLitePronunciation extends SQLiteDataController {
    public SQLitePronunciation(Context con) {
        super(con);
    }

    public ArrayList<Pronunciation> getListPronunciation(){
        ArrayList<Pronunciation> listPronunciation = new ArrayList<>();
        try{
            openDataBase();
            Cursor cs = database.rawQuery("select pronunciationID, name, description from PRONUNCIATION", null);
            Pronunciation pronunciation;
            while (cs.moveToNext()){
                pronunciation = new Pronunciation(cs.getInt(0),cs.getString(1), cs.getString(2));
                listPronunciation.add(pronunciation);
            }
            cs.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }

        return listPronunciation;
    }
}
