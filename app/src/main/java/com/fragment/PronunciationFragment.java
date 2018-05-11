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

import com.adapter.CustomPronunciationAdapter;
import com.database.SQLiteDataController;
import com.database.SQLitePronunciation;
import com.entity.Pronunciation;
import com.something.hp.PictF.R;

import java.io.IOException;
import java.util.ArrayList;

public class PronunciationFragment extends Fragment {
    ArrayList<Pronunciation> listPronunciation;
    Boolean advertisement;

    public PronunciationFragment() {
        // Required empty public constructor
    }
    public static PronunciationFragment newInstance() {
        PronunciationFragment fragment = new PronunciationFragment();
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
        getListPronunciation();
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

    private void getListPronunciation(){
        SQLitePronunciation pronunciation = new SQLitePronunciation(getContext());
        listPronunciation = new ArrayList<>();
        listPronunciation = pronunciation.getListPronunciation();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_pronunciation, container, false);
        ListView listView = (ListView)rootView.findViewById(R.id.listPronunciation);

        CustomPronunciationAdapter adapter = new CustomPronunciationAdapter(getContext(), R.layout.pronunciation_list_item, listPronunciation);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DetailPronunciationFragment detailPronunciationFragment = new DetailPronunciationFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                Bundle bundle = new Bundle();
                bundle.putInt("pronunc", listPronunciation.get(position).getPronunciationID());
                bundle.putString("how", listPronunciation.get(position).getDescription());
                bundle.putBoolean("advertisement", advertisement);
                FragmentTransaction transaction = manager.beginTransaction();
                detailPronunciationFragment.setArguments(bundle);
                //Khi được goi, fragment truyền vào sẽ thay thế vào vị trí FrameLayout trong Activity chính
                transaction.replace(R.id.fmContent, detailPronunciationFragment);
                transaction.addToBackStack("Frag1").commit();

            }
        });
        return rootView;
    }

}
