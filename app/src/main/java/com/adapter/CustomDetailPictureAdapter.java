package com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.entity.InforPicture;

import java.util.ArrayList;

/**
 * Created by HP on 31.08.2017.
 */

public class CustomDetailPictureAdapter extends ArrayAdapter<InforPicture> {

    Context context;
    int resource;
    ArrayList<InforPicture> listInforPictures = new ArrayList<>();

    public CustomDetailPictureAdapter(Context context, int resource, ArrayList<InforPicture> objects) {
        super(context, resource, objects);
        this.context =  context;
        this.resource = resource;
        this.listInforPictures = new ArrayList<InforPicture>(objects);

    }

    public View getView(int position, View convertView,  ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, null);
        }

        return convertView;
    }
}
