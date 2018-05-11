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

/**
 * Created by Luong Vien on 12.01.2018.
 */

public class CustomPracticePronounAdapter extends ArrayAdapter<Word> {

    public interface CallbackInterface{
        void onHandleSelection(int position, String text, int pronunc, int table);
    }

    private CallbackInterface mCallback;
    Context context;
    int resource;
    int pronunc;
    MediaPlayer mediaPlayer;

    ArrayList<Word> listWord = new ArrayList<>();
    public CustomPracticePronounAdapter(Context context, int resource, ArrayList<Word> objects, int pronunc){
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listWord = new ArrayList<Word>(objects);
        this.pronunc = pronunc;
        try{
            mCallback = (CallbackInterface) context;
        }catch(ClassCastException ex){
        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos = position;
        String ok = listWord.get(position).getOk();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource,null);
        }

        TextView textWord = (TextView) convertView.findViewById(R.id.txtWord);
        TextView textPro = (TextView) convertView.findViewById(R.id.txtPro);
        TextView textMeanming = (TextView) convertView.findViewById(R.id.txtMeaning);
        ImageView img = (ImageView) convertView.findViewById(R.id.imgTick);
        ImageButton imgPlayMp3 = (ImageButton) convertView.findViewById(R.id.imgPlayMp3);
        textWord.setText(listWord.get(position).getWord());
        textPro.setText(listWord.get(position).getPronoun());
        textMeanming.setText(listWord.get(position).getMean());

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
        btnSpeak = (ImageButton) convertView.findViewById(R.id.btnSpeak);

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mCallback != null && ConnectionReceiver.isConnected()){
                    mCallback.onHandleSelection(pos,listWord.get(pos).getWord(),pronunc, 1);
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
            // TODO Auto-generated method stub
            mediaPlayer.release();
            mediaPlayer = null;
        }
    };
}
