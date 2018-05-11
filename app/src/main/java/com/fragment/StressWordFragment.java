package com.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.adapter.CustomStressWordAdapter;
import com.database.SQLiteDataController;
import com.database.SQLiteListWords;
import com.entity.Word;
import com.google.android.gms.ads.MobileAds;
import com.something.hp.PictF.R;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class StressWordFragment extends Fragment {
    public ArrayList<Word> listStressWord;
    Boolean advertisement;

    public StressWordFragment() {
        // Required empty public constructor
    }

    public static StressWordFragment newInstance() {
        StressWordFragment fragment = new StressWordFragment();
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
        getListStressWord();
    }

    private void createDB(){
        SQLiteDataController sql = new SQLiteDataController(getActivity());
        try{
            sql.isCreatedDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getListStressWord(){
        SQLiteListWords sql = new SQLiteListWords(getContext());
        listStressWord = new ArrayList<>();
        listStressWord = sql.getListStressWord();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_stress_word, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listPracticeStressWord);
        CustomStressWordAdapter adapter = new CustomStressWordAdapter(getContext(), R.layout.practice_stress_word_item, listStressWord);
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
