package com.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.adapter.CustomLinkingAdapter;
import com.database.SQLiteDataController;
import com.database.SQLiteListIntonation;
import com.entity.Rule;
import com.something.hp.PictF.R;

import java.io.IOException;
import java.util.ArrayList;
public class LinkingSoundFragment extends Fragment {
    public ArrayList<Rule> listSentenceLinking;

    public LinkingSoundFragment() {
        // Required empty public constructor
    }

    public static LinkingSoundFragment newInstance() {
        LinkingSoundFragment fragment = new LinkingSoundFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDB();
        getListLinking();
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
    private void getListLinking(){
        SQLiteListIntonation sqlIntonation = new SQLiteListIntonation(getContext());
        listSentenceLinking = new ArrayList<>();
        listSentenceLinking = sqlIntonation.getListLinkingSentence();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootview = inflater.inflate(R.layout.fragment_linking_sound, container, false);
        ListView listView = (ListView) rootview.findViewById(R.id.listLinking);
        CustomLinkingAdapter adapter = new CustomLinkingAdapter(getContext(), R.layout.linking_sentence_item,listSentenceLinking);
        listView.setAdapter(adapter);
        return rootview;
    }
}
