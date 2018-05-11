package com.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.adapter.CustomIntonationAdapter;
import com.database.SQLiteDataController;
import com.database.SQLiteIntonation;
import com.entity.Intonation;
import com.something.hp.PictF.R;

import java.io.IOException;
import java.util.ArrayList;

public class IntonationFragment extends Fragment {
    public ArrayList<Intonation> listIntonation;
    Boolean advertisement;

    public IntonationFragment() {
        // Required empty public constructor
    }

    public static IntonationFragment newInstance() {
        IntonationFragment fragment = new IntonationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            advertisement = getArguments().getBoolean("advertisement");
        }
        createDB();
        getListIntonation();
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
    private void getListIntonation(){
        SQLiteIntonation sqlIntonation = new SQLiteIntonation(getContext());
        listIntonation = new ArrayList<>();
        listIntonation = sqlIntonation.getListIntonation();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_intonation, container, false);
        ListView listView = (ListView)rootView.findViewById(R.id.listIntonation);
        CustomIntonationAdapter adapter = new CustomIntonationAdapter(getContext(), R.layout.intonation_list_item,listIntonation);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        NormalQuestionFragment normalQuestionFragment = new NormalQuestionFragment();
                        FragmentManager manager = getActivity().getSupportFragmentManager();
                        Bundle bundle = new Bundle();
                        bundle.putInt("intonation", position+1);
                        bundle.putBoolean("advertisement", advertisement);
                        FragmentTransaction transaction = manager.beginTransaction();
                        normalQuestionFragment.setArguments(bundle);
                        transaction.replace(R.id.fmContent, normalQuestionFragment);
                        transaction.addToBackStack("Frag1").commit();
            }
        });
        return rootView;
    }
}
