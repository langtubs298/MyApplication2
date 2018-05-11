package com.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.adapter.CustomStressSentenceAdapter;
import com.database.SQLiteDataController;
import com.database.SQLiteListIntonation;
import com.entity.Sentence;
import com.something.hp.PictF.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class StressSentenceFragment extends Fragment {

    public ArrayList<Sentence> listStressSentence;
    private InterstitialAd mInterstitialAd;
    Boolean advertisement;

    public StressSentenceFragment() {
        // Required empty public constructor
    }

    public static StressSentenceFragment newInstance() {
        StressSentenceFragment fragment = new StressSentenceFragment();
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
            advertisement = getArguments().getBoolean("advertisement");
        }
        createDB();
        getListStressSentence();
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

    private void createDB(){
        SQLiteDataController sql = new SQLiteDataController(getActivity());
        try{
            sql.isCreatedDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getListStressSentence(){
        SQLiteListIntonation sql = new SQLiteListIntonation(getContext());
        listStressSentence = new ArrayList<>();
        listStressSentence = sql.getListSentenceStress();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_stress_sentence, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listPracticeStressSentence);
        CustomStressSentenceAdapter adapter = new CustomStressSentenceAdapter(getContext(), R.layout.stress_sentence_item, listStressSentence);
        listView.setAdapter(adapter);
        return rootView;
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
