package com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.entity.Rule;
import com.something.hp.PictF.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.something.hp.PictF.dummy.ImageNicer.decodeSampledBitmapFromResource;

/**
 * Created by Luong Vien on 27.01.2018.
 */

public class CustomEsdAdapter extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<Rule> listEsdSentence = new ArrayList<>();
    public CustomEsdAdapter(Context context, int resource, List<Rule> objects) {
        super(context, resource, objects);
        this.context =  context;
        this.resource = resource;
        this.listEsdSentence = new ArrayList<Rule>(objects);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, null);
        }

        CircleImageView img = (CircleImageView) convertView.findViewById(R.id.imageEsd1);
        int id = context.getResources().getIdentifier("end"+position, "drawable", context.getPackageName());
        img.setImageBitmap(
                decodeSampledBitmapFromResource(getContext().getResources(), id, 100, 100));
        TextView txt = (TextView) convertView.findViewById(R.id.txtEsd);
        txt.setText(listEsdSentence.get(position).getContent1());
        return convertView;
    }

}
