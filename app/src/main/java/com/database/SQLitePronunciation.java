package com.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.entity.Pronunciation;

import java.util.ArrayList;

/**
 * Created by HP on 02.09.2017.
 */

public class SQLitePronunciation extends SQLiteDataController {
    public SQLitePronunciation(Context con) {
        super(con);
    }

    public ArrayList<Pronunciation> getListPronunciation(){
        ArrayList<Pronunciation> listPronunciation = new ArrayList<>();
        try{
            openDataBase();
            Cursor cs = database.rawQuery("select pronunciationID, name from PRONUNCIATION", null);
            Pronunciation pronunciation;
            while (cs.moveToNext()){
                pronunciation = new Pronunciation(cs.getInt(0),cs.getString(1));
                listPronunciation.add(pronunciation);
            }
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
