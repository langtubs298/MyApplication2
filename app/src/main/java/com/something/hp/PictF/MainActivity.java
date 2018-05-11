package com.something.hp.PictF;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognizerIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.adapter.CustomLinkingAdapter;
import com.adapter.CustomPracticeEsdAdapter;
import com.adapter.CustomPracticePronounAdapter;
import com.adapter.CustomSentenceApdater;
import com.adapter.CustomStressSentenceAdapter;
import com.adapter.CustomStressWordAdapter;
import com.database.SQLiteDataController;
import com.database.SQLiteListWords;
import com.entity.IdDevice;
import com.entity.MyDictionaryRequest;
import com.fragment.AboutFragment;
import com.fragment.DictionaryFragment;
import com.fragment.EsdFragment;
import com.fragment.FreeTalkFragment;
import com.fragment.IntonationFragment;
import com.fragment.LinkingSoundFragment;
import com.fragment.PronunciationFragment;
import com.fragment.RecordFragment;
import com.fragment.RegisterFragment;
import com.fragment.StressFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.listeners.ConnectionReceiver;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CustomPracticePronounAdapter.CallbackInterface, CustomPracticeEsdAdapter.CallbackInterface, CustomStressWordAdapter.CallbackInterface, CustomLinkingAdapter.MyCallback, CustomSentenceApdater.MyCallback, CustomStressSentenceAdapter.MyCallbackSentence, MyDictionaryRequest.CallbackInterface {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    public String text;
    public int pronunc;
    public int id;
    public int table;
    MediaPlayer mediaPlayer;
    private DatabaseReference mDatabase;
    private AdView mAdView;
    private boolean advertisement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restoringPreferences();
        check();
        MobileAds.initialize(this, getString(R.string.idAds));
        if(advertisement){
            setContentView(R.layout.activity_main_ad);
        }
        else {
            setContentView(R.layout.activity_main);
            mAdView = (AdView) findViewById(R.id.adViewDetailPronounciation);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        final String android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        if(!advertisement){
            mDatabase.child("advertisementId").addChildEventListener(new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    IdDevice idDevice1 = dataSnapshot.getValue(IdDevice.class);
                    if(idDevice1.getIdd().equalsIgnoreCase(android_id)){
                        advertisement = true;
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        Fragment fragment = new PronunciationFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("advertisement", advertisement);
        fragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //Khi được goi, fragment truyền vào sẽ thay thế vào vị trí FrameLayout trong Activity chính
        transaction.replace(R.id.fmContent, fragment);
        transaction.commit();

    }

    public void check() {
        if (ConnectionReceiver.isConnected()) {
        } else {
            Toast.makeText(this, "Vui lòng kết nối internet", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //To call fragment
    public void callFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putBoolean("advertisement", advertisement);
        fragment.setArguments(bundle);
        //Khi được goi, fragment truyền vào sẽ thay thế vào vị trí FrameLayout trong Activity chính
        transaction.replace(R.id.fmContent, fragment);
        transaction.addToBackStack("Frag1");
        transaction.commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
            if (id == R.id.nav_pronunciation) {

                callFragment(new PronunciationFragment());
            } else if (id == R.id.nav_esd) {

                callFragment(new EsdFragment());
            }
            else if(id==R.id.nav_intonation){

                callFragment(new IntonationFragment());
            }
            else if(id==R.id.nav_linking){

                callFragment(new LinkingSoundFragment());
            }
            else if(id==R.id.nav_stress){

                callFragment(new StressFragment());
            }
            else if(id==R.id.nav_dic){

                callFragment(new DictionaryFragment());
            }
            else if (id == R.id.nav_about) {

                callFragment(new AboutFragment());
            }
            else if (id == R.id.nav_free){

                callFragment(new FreeTalkFragment());
            }
            else if (id == R.id.nav_register){

                callFragment(new RegisterFragment());
            }
//        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void createDB() {
        // khởi tạo database
        SQLiteDataController sql = new SQLiteDataController(this);
        try {
            sql.isCreatedDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onHandleSelection(int position, String text, int pronunc, int table) {
        this.text = text;
        this.pronunc = pronunc;
        this.id = position+1;
        this.table = table;
        startVoiceInput();
    }
    @Override
    public void callbackCall(String information){
        RecordFragment recordFragment = new RecordFragment();
        Bundle bundle = new Bundle();
        bundle.putString("information", information);
        bundle.putBoolean("advertisement", advertisement);
        recordFragment.setArguments(bundle);
        FragmentManager manager = this.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fmContent, recordFragment);
        transaction.addToBackStack("Frag1").commit();
    }

    @Override
    public void callBack(String information){
        RecordFragment recordFragment = new RecordFragment();
        Bundle bundle = new Bundle();
        bundle.putString("information", information);
        bundle.putBoolean("advertisement", advertisement);
        recordFragment.setArguments(bundle);
        FragmentManager manager = this.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fmContent, recordFragment);
        transaction.addToBackStack("Frag1").commit();
    }
    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Cố lên!?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    private void updateTick(){
        SQLiteListWords sqLiteListWords = new SQLiteListWords(this);
        sqLiteListWords.updateTick(pronunc, id,text);
    }

    private void updateTickEsd(){
        SQLiteListWords sqLiteListWords = new SQLiteListWords(this);
        sqLiteListWords.updateTickEsd(pronunc, id,text);
    }

    private void updateTickStressWord(){
        SQLiteListWords sqLiteListWords = new SQLiteListWords(this);
        sqLiteListWords.updateTickStressWord(id,text);
    }
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer mp) {
            // TODO Auto-generated method stub
            mediaPlayer.release();
            mediaPlayer = null;
        }
    };
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == Activity.RESULT_OK && null != data) {
                    int tmp;
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    if(text.equalsIgnoreCase(result.get(0))||text.equalsIgnoreCase(result.get(1))||text.equalsIgnoreCase(result.get(2))){
                        if(text.equalsIgnoreCase(result.get(0))){
                            tmp = 0;
                        }
                        else if(text.equalsIgnoreCase(result.get(1))){
                            tmp = 1;
                        }
                        else{
                            tmp = 2;
                        }
                        String fname="correct"+String.valueOf(tmp);
                        Uri mp3 = Uri.parse("android.resource://"
                                + this.getPackageName() + "/raw/"
                                + fname);
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        try {
                            mediaPlayer.setDataSource(this, mp3);
                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(onCompletionListener);
                        alertDialog.setTitle(text);
                        alertDialog.setMessage("Chuẩn không cần chỉnh");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Đóng",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                        createDB();
                        if(table == 0){

                        }
                        else if(table == 1){
                            updateTick();
                        }
                        else if(table == 2) {
                            updateTickEsd();
                        }
                        else {
                            updateTickStressWord();
                        }
                    }
                    else{
                        Uri mp3 = Uri.parse("android.resource://"
                                + this.getPackageName() + "/raw/"
                                + "wrong");
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        try {
                            mediaPlayer.setDataSource(this, mp3);
                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(onCompletionListener);
                        alertDialog.setTitle(result.get(0));
                        alertDialog.setMessage("Chưa chuẩn cần chỉnh");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Đóng",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                }
                break;
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoringPreferences();
    }

    public void restoringPreferences()
    {
        SharedPreferences pre=getSharedPreferences
                ("advertisementFile",MODE_PRIVATE);
        //lấy giá trị checked ra, nếu không thấy thì giá trị mặc định là false
        advertisement=pre.getBoolean("advertisement", false);
    }

    @Override
    public void onPause() {
        super.onPause();
        savingPreferences();
    }

    public void savingPreferences()
    {
        //tạo đối tượng getSharedPreferences
        SharedPreferences pre=getSharedPreferences
                ("advertisementFile", MODE_PRIVATE);
        //tạo đối tượng Editor để lưu thay đổi
        SharedPreferences.Editor editor=pre.edit();
        //lưu vào editor
        editor.putBoolean("advertisement", advertisement);
        //chấp nhận lưu xuống file
        editor.commit();
    }

    @Override
    public void onHandleSelection(String text, int table) {
        this.text = text;
        this.table = table;
        startVoiceInput();
    }
}
