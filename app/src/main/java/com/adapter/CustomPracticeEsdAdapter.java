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
 * Created by Luong Vien on 28.01.2018.
 */

public class CustomPracticeEsdAdapter extends ArrayAdapter<Word> {

    public interface CallbackInterface{
        void onHandleSelection(int position, String text, int pronunc, int table);
    }

    private CallbackInterface mCallback;
    Context context;
    int resource;
    int pronunc;
    ArrayList<Word> listWord = new ArrayList<>();
    MediaPlayer mediaPlayer;
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    public CustomPracticeEsdAdapter(Context context, int resource, List<Word> objects, int pronunc) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.pronunc = pronunc;
        this.listWord = new ArrayList<Word>(objects);
        try{
            mCallback = (CustomPracticeEsdAdapter.CallbackInterface) context;
        }catch(ClassCastException ex){
            Toast.makeText(getContext(),ex.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {

        final int pos = position;
        String ok = listWord.get(position).getOk();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource,null);
        }

        TextView texWord = (TextView) convertView.findViewById(R.id.txtWordEsd);
        TextView textPro = (TextView) convertView.findViewById(R.id.txtProEsd);
        TextView textMeaning = (TextView) convertView.findViewById(R.id.txtMeaningEsd);
        ImageView img = (ImageView) convertView.findViewById(R.id.imgTickEsd);
        texWord.setText(listWord.get(position).getWord());
        textPro.setText(listWord.get(position).getPronoun());
        textMeaning.setText(listWord.get(position).getMean());
        ImageButton imgPlayMp3 = (ImageButton) convertView.findViewById(R.id.imgPlayMp3Esd);

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
                String fname=listWord.get(pos).getWord().toString().toLowerCase();
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

        ImageButton btnSpeak;
        btnSpeak = (ImageButton) convertView.findViewById(R.id.btnSpeakEsd);
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mCallback != null && ConnectionReceiver.isConnected()){
                    mCallback.onHandleSelection(pos,listWord.get(pos).getWord(),pronunc,2);
                }
                else{
                    Toast.makeText(getContext(), "Vui lòng kết nối internet", Toast.LENGTH_SHORT).show();
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
