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

import com.entity.Sentence;
import com.something.hp.PictF.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Luong Vien on 22.01.2018.
 */

public class CustomSentenceApdater extends ArrayAdapter<Sentence> {

    public  interface MyCallback {
        void callbackCall(String information);
    }

    Context context;
    int resource;
    int intonation;
    MediaPlayer mediaPlayer;
    ArrayList<Sentence> listSentence = new ArrayList<>();
    private MyCallback mCallback;

    public CustomSentenceApdater(Context context, int resource, ArrayList<Sentence> objects, int intonation) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.intonation = intonation;
        this.listSentence = new ArrayList<Sentence>(objects);
        try{
            mCallback = (MyCallback) context;
        }catch(ClassCastException ex){
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource,null);
        }

        TextView txtSentence = (TextView) convertView.findViewById(R.id.txtSentence);
        ImageButton btnPlay = (ImageButton) convertView.findViewById(R.id.imgPlaySentence);
        ImageButton btnRecord = (ImageButton) convertView.findViewById(R.id.btnSpeakSentence);
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCallback != null){
                    mCallback.callbackCall(listSentence.get(position).getSentence());
                }
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fname="";
                switch(intonation){
                    case 1: {
                        fname = "st";
                        break;
                    }
                    case 2: {
                        fname = "wh";
                        break;
                    }
                    case 3: {
                        fname = "lt";
                        break;
                    }
                    case 4: {
                        fname = "tag";
                        break;
                    }
                    case 5: {
                        fname = "yn";
                        break;
                    }
                    default: break;
                }
                int tmp = position+1;
                Uri mp3 = Uri.parse("android.resource://"
                        + context.getPackageName() + "/raw/"
                        + fname+tmp);
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
        txtSentence.setText(listSentence.get(position).getSentence());

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
