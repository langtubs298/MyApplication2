package com.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.adapter.CustomPracticeEsdAdapter;
import com.database.SQLiteDataController;
import com.database.SQLiteListWords;
import com.entity.Word;
import com.something.hp.PictF.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class PracticeEsdFragment extends Fragment implements CustomPracticeEsdAdapter.CallbackInterface{
    public int eds;
    public String content2;
    public ArrayList<Word> listEsdWord;
    private InterstitialAd mInterstitialAd;
    Boolean advertisement;
    public PracticeEsdFragment() {
        // Required empty public constructor
    }
    public static PracticeEsdFragment newInstance() {
        PracticeEsdFragment fragment = new PracticeEsdFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restoringPreferences();
        MobileAds.initialize(getContext(), getString(R.string.idAds));
        if (getArguments() != null) {
            eds = getArguments().getInt("esd");
            content2 = getArguments().getString("content2");
            advertisement = getArguments().getBoolean("advertisement");
        }
        createDB();
        getListEdsWord();
        if(!advertisement){
            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd = new InterstitialAd(getActivity());
            mInterstitialAd.setAdUnitId(getString(R.string.interstitial_id));

            mInterstitialAd.loadAd(adRequest);

            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded(){
                    showInterstitial();
                }
            });
        }
    }
    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    private void createDB() {
        // khởi tạo database
        SQLiteDataController sql = new SQLiteDataController(getActivity());
        try {
            sql.isCreatedDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void getListEdsWord(){
        SQLiteListWords sqlIntonation = new SQLiteListWords(getContext());
        listEsdWord = new ArrayList<>();
        listEsdWord = sqlIntonation.getListEsdWord(eds);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_practice_esd, container, false);
        TextView txtRule = (TextView) rootView.findViewById(R.id.txtRule);
        txtRule.setText(content2);
        ListView listView = (ListView) rootView.findViewById(R.id.listPracticeEsd);
        CustomPracticeEsdAdapter adapter = new CustomPracticeEsdAdapter(getContext(), R.layout.esd_list_sentence_item, listEsdWord, eds);
        listView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onHandleSelection(int position, String text, int pronunc, int table) {

    }

    @Override
    public void onResume() {
        super.onResume();
        restoringPreferences();
    }

    public void restoringPreferences()
    {
        SharedPreferences pre=getContext().getSharedPreferences
                ("search",MODE_PRIVATE);
        //lấy giá trị checked ra, nếu không thấy thì giá trị mặc định là false
        advertisement = pre.getBoolean("advertisement", false);
    }

    @Override
    public void onPause() {
        super.onPause();
        savingPreferences();
    }

    public void savingPreferences()
    {
        //tạo đối tượng getSharedPreferences
        SharedPreferences pre=getContext().getSharedPreferences
                ("search", MODE_PRIVATE);
        //tạo đối tượng Editor để lưu thay đổi
        SharedPreferences.Editor editor=pre.edit();
        //lưu vào editor
        editor.putBoolean("advertisement", advertisement);
        //chấp nhận lưu xuống file
        editor.commit();
    }
}
