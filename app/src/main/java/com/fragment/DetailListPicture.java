package com.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.adapter.CustomDetailPictureAdapter;
import com.entity.InforPicture;
import com.example.hp.PictF.R;

import java.util.ArrayList;
public class DetailListPicture extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String a;
    ArrayList<InforPicture> listInforPicture;

    public DetailListPicture() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DetailListPicture newInstance(String param1, String param2) {
        DetailListPicture fragment = new DetailListPicture();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listInforPicture = new ArrayList<>();
        InforPicture inforPicture= new InforPicture();
        inforPicture.setImage(1);
        inforPicture.setEnglishMeaning("Hello");
        inforPicture.setPronouciation("helo");
        inforPicture.setVietnameseMeaning("Xin chao");
        listInforPicture.add(inforPicture);
        if (getArguments() != null) {
            a = getArguments().getString("a","ok");
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detail_list_picture, container, false);
        ListView listView = (ListView)rootView.findViewById(R.id.list_detail_picture);
        CustomDetailPictureAdapter adapter = new CustomDetailPictureAdapter(getContext(),R.layout.detail_picture_item,listInforPicture);
        listView.setAdapter(adapter);
        return rootView;
    }
}
