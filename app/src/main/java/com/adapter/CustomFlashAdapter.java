package com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.entity.Flash;
import com.example.hp.PictF.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 02.09.2017.
 */

public class CustomFlashAdapter extends ArrayAdapter<Flash> {

    Context context;
    int resource;
    ArrayList<Flash> listFlash = new ArrayList<>();
    public CustomFlashAdapter(Context context, int resource, List<Flash> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listFlash = new ArrayList<Flash>(objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, null);
        }
        TextView tvNoiDung = (TextView) convertView.findViewById(R.id.txtFlash);


        final Flash flash = listFlash.get(position);
        //lấy Nội dung của Item ra để thiết lập nội dung item cho đúng
        tvNoiDung.setText(flash.getName());

        //lấy ImageView ra để thiết lập hình ảnh cho đúng

        //trả về View này, tức là trả luôn về các thông số mới mà ta vừa thay đổi
        return convertView;
    }
}
