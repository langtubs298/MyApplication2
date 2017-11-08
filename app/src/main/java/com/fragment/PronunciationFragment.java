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
import com.example.hp.PictF.R;

import java.io.IOException;
import java.util.ArrayList;

public class PronunciationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<Pronunciation> listPronunciation;

    public PronunciationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PronunciationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PronunciationFragment newInstance(String param1, String param2) {
        PronunciationFragment fragment = new PronunciationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        System.out.printf("ff"+listPronunciation.size());
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
