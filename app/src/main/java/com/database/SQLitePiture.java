package com.database;

import android.content.Context;

import com.entity.Picture;

import android.database.Cursor;
import android.database.SQLException;

import java.util.ArrayList;

/**
 * Created by HP on 29.08.2017.
 */

public class SQLitePiture extends SQLiteDataController {
    public SQLitePiture(Context con) {
        super(con);
    }

    public ArrayList<Picture> getListPicture(){
        ArrayList<Picture> listPicture = new ArrayList<>();

        try{
            openDataBase();
            Cursor cs = database.rawQuery("select pictureID, name from PICTURE", null);
            Picture picture;
            while (cs.moveToNext()){
                picture = new Picture(cs.getInt(0),cs.getString(1));
                listPicture.add(picture);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }

        return listPicture;
    }
}
