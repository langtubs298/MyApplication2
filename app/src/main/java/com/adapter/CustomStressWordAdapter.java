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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.entity.Word;
import com.something.hp.PictF.R;
import com.listeners.ConnectionReceiver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luong Vien on 01.02.2018.
 */

public class CustomStressWordAdapter extends ArrayAdapter {

    public interface CallbackInterface{
        void onHandleSelection(int position, String text, int pronunc, int table);
    }

    Context context;
    int resource;
    ArrayList<Word> listWord;
    MediaPlayer mediaPlayer;
    private CallbackInterface mCallback;

    public CustomStressWordAdapter(Context context, int resource, List<Word> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listWord = new ArrayList<Word>(objects);
        try{
            mCallback = (CallbackInterface) context;
        }catch(ClassCastException ex){
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;
        String ok = listWord.get(position).getOk();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, null);
        }

        TextView txtWord = (TextView) convertView.findViewById(R.id.txtWordStress);
        TextView txtPro = (TextView) convertView.findViewById(R.id.txtProStress);
        TextView txtMean = (TextView) convertView.findViewById(R.id.txtMeaningStress);
        ImageView img = (ImageView) convertView.findViewById(R.id.imgTickStress);
        ImageButton imgPlayMp3 = (ImageButton) convertView.findViewById(R.id.imgPlayMp3Stress);
        ImageButton btnSpeak;
        btnSpeak = (ImageButton) convertView.findViewById(R.id.btnSpeakStress);
        if(ok!=null && ok!=""){
            img.setBackgroundResource(R.mipmap.ic_tick);
        }
        else
        {
            img.setBackgroundResource(R.mipmap.ic_blank);
        }
        imgPlayMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname=listWord.get(pos).getWord().toString().toLowerCase().replace(" ", "");
                Uri mp3 = Uri.parse("android.resource://"
                        + context.getPackageName() + "/raw/"
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
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mCallback != null && ConnectionReceiver.isConnected()){
                    mCallback.onHandleSelection(pos,listWord.get(pos).getWord(),1, 3);
                }
                else{
                    Toast.makeText(getContext(), "Vui lòng kết nối internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
        txtWord.setText(Html.fromHtml(listWord.get(position).getWord()));
        txtPro.setText(listWord.get(position).getPronoun());
        txtMean.setText(listWord.get(position).getMean());
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
