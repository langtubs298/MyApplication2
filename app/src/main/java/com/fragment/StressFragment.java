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
import com.adapter.CustomStressAdapter;
import com.database.SQLiteDataController;
import com.database.SQLiteListIntonation;
import com.entity.Sentence;
import com.something.hp.PictF.R;
import java.io.IOException;
import java.util.ArrayList;

public class StressFragment extends Fragment {
    public ArrayList<Sentence> listStress;
    Boolean advertisement;

    public StressFragment() {
        // Required empty public constructor
    }
    public static StressFragment newInstance() {
        StressFragment fragment = new StressFragment();
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
        getListStress();
    }

    private void createDB(){
        SQLiteDataController sql = new SQLiteDataController(getActivity());
        try{
            sql.isCreatedDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void getListStress(){
        SQLiteListIntonation sqLiteListIntonation = new SQLiteListIntonation(getContext());
        listStress = new ArrayList<>();
        listStress = sqLiteListIntonation.getListStress();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_stress, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listStress);
        CustomStressAdapter adapter = new CustomStressAdapter(getContext(), R.layout.stress_list_item, listStress);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    StressWordFragment stressWordFragment = new StressWordFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("advertisement", advertisement);
                    stressWordFragment.setArguments(bundle);
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.fmContent, stressWordFragment);
                    transaction.addToBackStack("Frag1").commit();
                }
                else {
                    StressSentenceFragment stressSentenceFragment = new StressSentenceFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("advertisement", advertisement);
                    stressSentenceFragment.setArguments(bundle);
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.fmContent, stressSentenceFragment);
                    transaction.addToBackStack("Frag1").commit();
                }
            }
        });
        return rootView;
    }
}
