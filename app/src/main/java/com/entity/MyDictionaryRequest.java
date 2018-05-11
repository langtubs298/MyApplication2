package com.entity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.something.hp.PictF.R;
import com.listeners.ConnectionReceiver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Luong Vien on 26.03.2018.
 */

public class MyDictionaryRequest extends AsyncTask<String, Integer, String> {

    public interface CallbackInterface{
        void onHandleSelection(String text, int table);
    }

    private CallbackInterface mCallback;

    final String app_id = "67025b36";
    final String app_key = "dee61b18da7e84e8792e62d8df49af35";
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    MediaPlayer mediaPlayer;
    ProgressDialog progress;
    Context context;
    TextView txtWord, txtPronunciation, txtMeaning, textsomething, textExam, txtMean, textLexical;
    ImageView sound, record;

    public MyDictionaryRequest(Context context, TextView textLexical, TextView txtMean,TextView textsomething, TextView textExam, TextView txtMeaning, TextView txtPronunciation, TextView txtWord, ImageView sound, ImageView record){
        this.context = context;
        this.txtWord = txtWord;
        this.txtMeaning = txtMeaning;
        this.txtPronunciation = txtPronunciation;
        this.sound = sound;
        this.record = record;
        this.textsomething = textsomething;
        this.textExam = textExam;
        this.txtMean = txtMean;
        this.textLexical = textLexical;
        progress = new ProgressDialog(context);
        progress.setTitle("Vui lòng chờ");
        progress.setMessage("Đang tải dữ liệu...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog

        try{
            mCallback = (CallbackInterface) context;
        }catch(ClassCastException ex){
        }
    }
    @Override
    protected void onPreExecute() {
        progress.show();
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("app_id",app_id);
            urlConnection.setRequestProperty("app_key",app_key);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        }
        catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer mp) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    };

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try{

            JSONObject js = new JSONObject(s);
            JSONArray results = js.getJSONArray("results");

            JSONObject iEntries = results.getJSONObject(0);
            JSONArray isArray = iEntries.getJSONArray("lexicalEntries");
            JSONObject entries = isArray.getJSONObject(0);
            JSONArray e = entries.getJSONArray("entries");
            try{
                String lexicalCategory = entries.getString("lexicalCategory");
                textLexical.setText("("+lexicalCategory+")");
            }
            catch (JSONException ex){

            }

            JSONObject jsonObject = e.getJSONObject(0);
            JSONArray sensesArray = jsonObject.getJSONArray("senses");
            JSONArray pronunciation = entries.getJSONArray("pronunciations");
            final String word = entries.getString("text");

            JSONObject pronunciationObject = pronunciation.getJSONObject(0);
            String pro = pronunciationObject.getString("phoneticSpelling");

            JSONObject audioObject = null;
            try{
                audioObject = pronunciation.getJSONObject(0);
                sound.setImageResource(R.mipmap.ic_volumn);
            }
            catch (JSONException ex){

            }

            final String audiosLink = audioObject.getString("audioFile");
            Uri mp3 = Uri.parse(audiosLink);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mediaPlayer.setDataSource(context, mp3);
                mediaPlayer.prepare();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(onCompletionListener);

            txtWord.setText(word);
            txtPronunciation.setText("/"+pro+"/");

            JSONObject d = sensesArray.getJSONObject(0);
            try {
                JSONArray de = d.getJSONArray("definitions");
                txtMean.setText("Giải nghĩa: ");
                txtMeaning.setText(de.getString(0));
            }
            catch (JSONException ex){

            }
            try{
                JSONArray exam = d.getJSONArray("examples");
                JSONObject txt = exam.getJSONObject(0);
                textExam.setText("Ví dụ: ");
                textsomething.setText(txt.getString("text"));
            }
            catch (JSONException ec){

            }
            record.setImageResource(R.mipmap.ic_fileviewer);
            progress.dismiss();
            sound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Uri mp3 = Uri.parse(audiosLink);
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

            record.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(mCallback != null && ConnectionReceiver.isConnected()){
                        mCallback.onHandleSelection(word, 0);
                    }
                    else{
                        Toast.makeText(context, "Vui lòng kết nối internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        catch (JSONException e){
            progress.dismiss();
            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Không tìm thấy!");
            alertDialog.setMessage("Vui lòng nhập lại");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Đóng",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }
}
