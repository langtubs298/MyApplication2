package com.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.entity.Picture;
import com.example.hp.PictF.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 26.08.2017.
 */

public class CustomPictureAdapter extends ArrayAdapter<Picture> {

    Context context;
    ArrayList<Picture> listPicture = new ArrayList<Picture>();
    int resource;

    public CustomPictureAdapter(Context context, int resource, List<Picture> objects){
        super(context, resource, objects);
        this.context = context;
        this.listPicture = new ArrayList<Picture>(objects);
        this.resource = resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, null);
        }

        TextView tvNoiDung = (TextView) convertView.findViewById(R.id.txtPicture);


        final Picture picture = listPicture.get(position);
        //lấy Nội dung của Item ra để thiết lập nội dung item cho đúng
            tvNoiDung.setText(picture.getName());

        //lấy ImageView ra để thiết lập hình ảnh cho đúng

        //trả về View này, tức là trả luôn về các thông số mới mà ta vừa thay đổi
        return convertView;
    }
}
