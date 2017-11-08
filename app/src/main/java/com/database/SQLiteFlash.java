package com.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.entity.Flash;

import java.util.ArrayList;

/**
 * Created by HP on 29.08.2017.
 */

public class SQLiteFlash extends SQLiteDataController {
    public SQLiteFlash(Context con) {
        super(con);
    }

    public ArrayList<Flash> getListFlash(){
        ArrayList<Flash> listFlash = new ArrayList<>();

        try{
            openDataBase();
            Cursor cs = database.rawQuery("select flashID, name from FLASH", null);
            Flash flash;
            while (cs.moveToNext()){
                flash = new Flash(cs.getInt(0),cs.getString(1));
                listFlash.add(flash);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }

        return listFlash;
    }
}
