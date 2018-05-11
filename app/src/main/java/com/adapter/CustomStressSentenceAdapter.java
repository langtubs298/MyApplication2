package com.adapter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.entity.Sentence;
import com.something.hp.PictF.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luong Vien on 17.02.2018.
 */

public class CustomStressSentenceAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Sentence> listLinkingSentence = new ArrayList<>();
    MediaPlayer mediaPlayer;
    private MyCallbackSentence mCallback;
    public  interface MyCallbackSentence {
        void callBack(String information);
    }
    public CustomStressSentenceAdapter(Context context, int resource, List<Sentence> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listLinkingSentence = new ArrayList<Sentence>(objects);
        try{
            mCallback = (MyCallbackSentence) context;
        }catch(ClassCastException ex){
            Toast.makeText(getContext(),ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public View getView(final int position,View convertView,ViewGroup parent) {

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.txtSentenceStress);
        String tmp = listLinkingSentence.get(position).getSentence();
        String tmp1 = tmp.replace("span style=\"color:", "font color='").replace(";\"","'").replace("</span>", "</font>");
        textView.setText(Html.fromHtml(tmp1));
        ImageButton imgPlayMp3 = (ImageButton) convertView.findViewById(R.id.imgPlaySentenceStress);
        ImageButton btnRecord = (ImageButton) convertView.findViewById(R.id.btnRecordSentenceStress);
        imgPlayMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname=String.valueOf(position+1);
                Uri mp3 = Uri.parse("android.resource://"
                        + context.getPackageName() + "/raw/stress"
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
                    mCallback.callBack(listLinkingSentence.get(position).getSentence());
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
