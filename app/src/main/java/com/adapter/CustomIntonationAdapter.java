package com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.entity.Intonation;
import com.something.hp.PictF.R;

import java.util.ArrayList;
import java.util.List;

import static com.something.hp.PictF.dummy.ImageNicer.decodeSampledBitmapFromResource;

/**
 * Created by Luong Vien on 18.10.2017.
 */

public class CustomIntonationAdapter extends ArrayAdapter<Intonation> {

    Context context;
    int resource;
    ArrayList<Intonation> listIntonation = new ArrayList<>();
    public CustomIntonationAdapter(Context context, int resource, List<Intonation> objects) {
        super(context, resource,objects);
        this.context = context;
        this.resource = resource;
        this.listIntonation = new ArrayList<Intonation>(objects);
    }

    public View getView(int position,View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, null);
        }

        TextView textView = (TextView)convertView.findViewById(R.id.txtCaseIntonation);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageIntonation);
        int id = context.getResources().getIdentifier("into"+position, "drawable", context.getPackageName());
        imageView.setImageBitmap(
                decodeSampledBitmapFromResource(getContext().getResources(), id, 100, 100));
        final Intonation intonation= listIntonation.get(position);
        textView.setText(intonation.getIntonation());
        return convertView;
    }
}
