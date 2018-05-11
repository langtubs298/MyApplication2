package com.adapter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.entity.Rule;
import com.something.hp.PictF.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luong Vien on 31.01.2018.
 */
public class CustomLinkingAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Rule> listLinkingSentence = new ArrayList<>();
    MediaPlayer mediaPlayer;
    private MyCallback mCallback;
    public  interface MyCallback {
        void callbackCall(String information);
    }
    public CustomLinkingAdapter(Context context, int resource, List<Rule> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listLinkingSentence = new ArrayList<Rule>(objects);
        try{
            mCallback = (MyCallback) context;
        }catch(ClassCastException ex){
            Toast.makeText(getContext(),ex.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    public View getView(final int position,View convertView,ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.txtSentenceLinking);
        TextView textPro = (TextView) convertView.findViewById(R.id.txtProLinking);
        textView.setText(listLinkingSentence.get(position).getContent1());
        textPro.setText(listLinkingSentence.get(position).getContent2());
        ImageButton imgPlayMp3 = (ImageButton) convertView.findViewById(R.id.imgPlaySentenceLinking);
        ImageButton btnRecord = (ImageButton) convertView.findViewById(R.id.btnRecordSentenceLinking);
        imgPlayMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname=String.valueOf(position+1);
                Uri mp3 = Uri.parse("android.resource://"
                        + context.getPackageName() + "/raw/linking"
                        + fname);
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(context, mp3);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(onCompletionListener);
            }
        });
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCallback != null){
                    mCallback.callbackCall(listLinkingSentence.get(position).getContent1());
                }
            }
        });
        return convertView;
    }
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer mp) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    };
}
