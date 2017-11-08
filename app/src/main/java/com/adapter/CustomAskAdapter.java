package com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.entity.Question;
import com.example.hp.PictF.R;

import java.util.ArrayList;

/**
 * Created by HP on 05.09.2017.
 */

public class CustomAskAdapter extends ArrayAdapter<Question> {
    Context context;
    int resource;
    ArrayList<Question> listCategoryQuestion = new ArrayList<>();
    public CustomAskAdapter(Context context,int resource, ArrayList<Question> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listCategoryQuestion = new ArrayList<Question>(objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource,null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.txtQuestion);
        final Question question = listCategoryQuestion.get(position);
        textView.setText(question.getQuestion());
        return convertView;
    }
}
