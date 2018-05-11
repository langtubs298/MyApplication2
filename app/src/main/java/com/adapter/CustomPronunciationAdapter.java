package com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.entity.Pronunciation;
import com.something.hp.PictF.R;

import java.util.ArrayList;
import java.util.List;

import static com.something.hp.PictF.dummy.ImageNicer.decodeSampledBitmapFromResource;

/**
 * Created by Luong Vien on 02.09.2017.
 */

public class CustomPronunciationAdapter extends ArrayAdapter<Pronunciation> {

    Context context;
    int resource;
    ArrayList<Pronunciation> listPronunciation = new ArrayList<>();

    public CustomPronunciationAdapter(Context context, int resource, List<Pronunciation>objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listPronunciation = new ArrayList<Pronunciation>(objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, null);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imagePronunciation);
        int id = context.getResources().getIdentifier("img"+position, "drawable", context.getPackageName());
        imageView.setImageBitmap(
                decodeSampledBitmapFromResource(getContext().getResources(), id, 100, 100));
        TextView txtPronunciation = (TextView) convertView.findViewById(R.id.txtPronunciation);
        Pronunciation pronunciation = listPronunciation.get(position);
        txtPronunciation.setText(pronunciation.getName());

        return convertView;
    }
}
