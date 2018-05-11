package com.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.adapter.CustomPracticePronounAdapter;
import com.database.SQLiteDataController;
import com.database.SQLiteListWords;
import com.entity.Word;
import com.google.android.gms.ads.MobileAds;
import com.something.hp.PictF.R;

import java.io.IOException;
import java.util.ArrayList;

public class PracticePronounFragment extends Fragment {
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    public int pronunc;
    public ArrayList<Word> listWord;
    int pos=0;
    Boolean advertisement;

    public PracticePronounFragment() {
        // Required empty public constructor
    }

    public static PracticePronounFragment newInstance() {
        PracticePronounFragment fragment = new PracticePronounFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(getContext(), getString(R.string.idAds));
        if (getArguments() != null) {
            pronunc = getArguments().getInt("pronunc");
            advertisement = getArguments().getBoolean("advertisement");
        }
        createDB();
        getListWords(pronunc);
    }

    private void getListWords(int pronunc){
        SQLiteListWords sqLiteListWords = new SQLiteListWords(getContext());
        listWord = new ArrayList<>();
        listWord = sqLiteListWords.getListWord(pronunc);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_practice_pronoun, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listPracticePronoun);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
            }
        });

        CustomPracticePronounAdapter adapter = new CustomPracticePronounAdapter(getContext(), R.layout.practice_pronoun_list_item, listWord, pronunc);
        listView.setAdapter(adapter);

        return rootView;
    }

}
