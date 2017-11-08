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

import com.adapter.CustomAskAdapter;
import com.database.SQLiteDataController;
import com.database.SQLiteQuestion;
import com.entity.Question;
import com.example.hp.PictF.R;

import java.io.IOException;
import java.util.ArrayList;

public class AskFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public ArrayList<Question> listQuestion;

    public AskFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AskFragment newInstance(String param1, String param2) {
        AskFragment fragment = new AskFragment();
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
        getListQuestion();
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
    private void getListQuestion(){
        SQLiteQuestion sqlQuestion = new SQLiteQuestion(getContext());
        listQuestion = new ArrayList<>();
        listQuestion = sqlQuestion.getListQuestion();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ask, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.listAsk);
        CustomAskAdapter adapter =new CustomAskAdapter(getContext(), R.layout.question_list_item, listQuestion);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                switch (position){
                    case 0: {
                        ChoiceFragment choiceFragment = new ChoiceFragment();
                        transaction.replace(R.id.fmContent, choiceFragment);
                        transaction.commit();
                        break;
                    }
                    case 1: {
                        LocationFragment locationFragment = new LocationFragment();
                        transaction.replace(R.id.fmContent, locationFragment);
                        transaction.commit();
                        break;
                    }
                    case 2:{
                        TimeFragment timeFragment = new TimeFragment();
                        transaction.replace(R.id.fmContent, timeFragment);
                        transaction.commit();
                        break;
                    }
                    case 3:{
                        ReasonFragment reasonFragment = new ReasonFragment();
                        transaction.replace(R.id.fmContent, reasonFragment);
                        transaction.commit();
                        break;
                    }
                    case 4:{
                        PeopleFragment peopleFragment = new PeopleFragment();
                        transaction.replace(R.id.fmContent, peopleFragment);
                        transaction.commit();
                        break;

                    }
                    case 5:{
                        OpinionFragment opinionFragment = new OpinionFragment();
                        transaction.replace(R.id.fmContent, opinionFragment);
                        transaction.commit();
                        break;

                    }
                    case 6:{
                        SuggestionFragment suggestionFragment = new SuggestionFragment();
                        transaction.replace(R.id.fmContent, suggestionFragment);
                        transaction.commit();
                        break;

                    }
                    case 7:{
                        HowFragment howFragment = new HowFragment();
                        transaction.replace(R.id.fmContent, howFragment);
                        transaction.commit();
                        break;
                    }
                }
            }
        });
        return rootView;
    }
}
