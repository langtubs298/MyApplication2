package com.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.adapter.CustomFlashAdapter;
import com.database.SQLiteFlash;
import com.entity.Flash;
import com.example.hp.PictF.R;
import com.database.SQLiteDataController;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link FlashCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FlashCardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public ArrayList<Flash> listFlash;


    public FlashCardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FlashCardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FlashCardFragment newInstance(String param1, String param2) {
        FlashCardFragment fragment = new FlashCardFragment();
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
        getListFlash();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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

    private void getListFlash(){
        SQLiteFlash flash = new SQLiteFlash(getContext());
        listFlash = new ArrayList<>();
        listFlash = flash.getListFlash();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_flash_card, container, false);

        ListView listView = (ListView)rootView.findViewById(R.id.listFlashCard);
        CustomFlashAdapter adapter = new CustomFlashAdapter(getContext(),R.layout.flash_list_item, listFlash);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DetailListPicture detailListPicture = new DetailListPicture();
            }
        });
        return rootView;
    }
}
