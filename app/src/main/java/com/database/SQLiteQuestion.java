package com.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.entity.Question;

import java.util.ArrayList;

/**
 * Created by HP on 18.10.2017.
 */

public class SQLiteQuestion extends SQLiteDataController {

    public SQLiteQuestion(Context con) {
        super(con);
    }

    public ArrayList<Question> getListQuestion(){
        ArrayList<Question> listQuestion= new ArrayList<>();
        try{
            openDataBase();
            Cursor cs = database.rawQuery("select questionID, question from QUESTION", null);
            Question question;
            while (cs.moveToNext()){
                question = new Question(cs.getInt(0), cs.getString(1));
                listQuestion.add(question);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close();
        }
        return listQuestion;
    }
}
