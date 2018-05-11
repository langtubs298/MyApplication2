package com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.entity.Sentence;
import com.something.hp.PictF.R;

import java.util.ArrayList;
import java.util.List;

import static com.something.hp.PictF.dummy.ImageNicer.decodeSampledBitmapFromResource;

/**
 * Created by HP on 01.02.2018.
 */

public class CustomStressAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Sentence> listStress;
    public CustomStressAdapter(Context context, int resource, List<Sentence> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listStress = new ArrayList<Sentence>(objects);
    }

    public View getView(int position,View convertView,ViewGroup parent) {

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.txtStress);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageIntonation);
        int id = context.getResources().getIdentifier("stress"+position, "drawable", context.getPackageName());
        imageView.setImageBitmap(
                decodeSampledBitmapFromResource(getContext().getResources(), id, 100, 100));
        textView.setText(listStress.get(position).getSentence());
        return convertView;
    }
}
