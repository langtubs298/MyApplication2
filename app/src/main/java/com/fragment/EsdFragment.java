package com.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.adapter.CustomEsdAdapter;
import com.database.SQLiteDataController;
import com.database.SQLiteListIntonation;
import com.entity.Rule;
import com.something.hp.PictF.R;

import java.io.IOException;
import java.util.ArrayList;

public class EsdFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public ArrayList<Rule> listEsd;
    Boolean advertisement;

    public EsdFragment() {
        // Required empty public constructor
    }

    public static EsdFragment newInstance() {
        EsdFragment fragment = new EsdFragment();
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
        getListEsd();
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
    private void getListEsd(){
        SQLiteListIntonation sqlIntonation = new SQLiteListIntonation(getContext());
        listEsd = new ArrayList<>();
        listEsd = sqlIntonation.getListEsdSentence();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_esd, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listEnding);
        CustomEsdAdapter adapter = new CustomEsdAdapter(getContext(), R.layout.esd_list_item, listEsd);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PracticeEsdFragment practiceEsdFragment = new PracticeEsdFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                Bundle bundle = new Bundle();
                bundle.putInt("esd", position+1);
                bundle.putString("content2", listEsd.get(position).getContent2());
                bundle.putBoolean("advertisement", advertisement);
                android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
                practiceEsdFragment.setArguments(bundle);
                transaction.replace(R.id.fmContent, practiceEsdFragment);
                transaction.addToBackStack("Frag1").commit();
            }
        });

        return rootView;
    }

}
