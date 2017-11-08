package com.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.entity.Intonation;

import java.util.ArrayList;

/**
 * Created by HP on 18.10.2017.
 */

public class SQLiteIntonation extends SQLiteDataController {
    public SQLiteIntonation(Context con) {
        super(con);
    }

    public ArrayList<Intonation> getListIntonation(){

        ArrayList<Intonation> listIntonation = new ArrayList<>();
        try{
            openDataBase();
            Cursor cs = database.rawQuery("select intonationID, intonation from INTONATION", null);
            Intonation intonation;
            while (cs.moveToNext()) {
                intonation = new Intonation(cs.getInt(0), cs.getString(1));
                listIntonation.add(intonation);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return  listIntonation;
    }
}
