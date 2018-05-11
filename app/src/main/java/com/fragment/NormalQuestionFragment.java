package com.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.adapter.CustomSentenceApdater;
import com.database.SQLiteDataController;
import com.database.SQLiteListIntonation;
import com.entity.Sentence;
import com.google.android.gms.ads.MobileAds;
import com.something.hp.PictF.R;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class NormalQuestionFragment extends Fragment {
    public int intonation;
    Boolean advertisement;
    public ArrayList<Sentence> listSentence, listGuide;

    public NormalQuestionFragment() {
        // Required empty public constructor
    }

    public static NormalQuestionFragment newInstance() {
        NormalQuestionFragment fragment = new NormalQuestionFragment();
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
            intonation = getArguments().getInt("intonation");
            advertisement = getArguments().getBoolean("advertisement");
        }
        createDB();
        getListSentence();
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
    private void getListSentence(){
        SQLiteListIntonation sqLiteListIntonation = new SQLiteListIntonation(getContext());
        listSentence = new ArrayList<>();
        listGuide = new ArrayList<>();
        listSentence = sqLiteListIntonation.getListSentence(intonation);
        listGuide = sqLiteListIntonation.getGuideIntonation();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_normal_question, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listSentence);
        TextView txtHow = (TextView) rootView.findViewById(R.id.txtHowIntonation);
        txtHow.setText(listGuide.get(intonation-1).getSentence());
        CustomSentenceApdater apdater = new CustomSentenceApdater(getContext(), R.layout.intonation_sentence_item, listSentence, intonation);
        listView.setAdapter(apdater);
        // Inflate the layout for this fragment
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
