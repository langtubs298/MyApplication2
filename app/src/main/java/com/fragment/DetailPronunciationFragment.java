package com.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;

import com.example.hp.PictF.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link DetailPronunciationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailPronunciationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public int pronunc;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailPronunciationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailPronunciationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailPronunciationFragment newInstance(String param1, String param2) {
        DetailPronunciationFragment fragment = new DetailPronunciationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            pronunc = getArguments().getInt("pronunc");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detail_pronunciation, container, false);
        String path = "";
        String path2 = "";
        final VideoView view = (VideoView) rootView.findViewById(R.id.videoPronunciation);
        final VideoView view2 = (VideoView) rootView.findViewById(R.id.videoPronunciation2);
        switch (pronunc){
            case 1:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.i;
                view.setVideoURI(Uri.parse(path));
                break;
            }
            case 21:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.p;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 22:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.b;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 23:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.t;
                view.setVideoURI(Uri.parse(path));
                path2 = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.b;
                view.setVideoURI(Uri.parse(path));
                view2.setVideoURI(Uri.parse(path2));
                break;
            }
            case 24:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.d;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 25:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.k;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 26:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.g;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 27:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.f;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 28:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.v;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 29:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.s;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 30:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.z;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 31:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.th;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 32:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.d3;
                view.setVideoURI(Uri.parse(path));
                break;
            }
            case 33:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.sh;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 34:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.zi;
                view.setVideoURI(Uri.parse(path));
                break;
            }
            case 35:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.ch;
                view.setVideoURI(Uri.parse(path));
                break;
            }
            case 36:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.dd;
                view.setVideoURI(Uri.parse(path));
                break;
            }
            case 37:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.m;
                view.setVideoURI(Uri.parse(path));
                break;
            }
            case 38:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.n;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 39:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.ng;
                view.setVideoURI(Uri.parse(path));
                break;
            }
            case 40:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.h;
                view.setVideoURI(Uri.parse(path));
                break;
            }
            case 41:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.l;
                view.setVideoURI(Uri.parse(path));
                break;
            }
            case 42:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.r;
                view.setVideoURI(Uri.parse(path));
                break;
            }
            case 43:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.w;
                view.setVideoURI(Uri.parse(path));
                break;
            }
            case 44:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.j;
                view.setVideoURI(Uri.parse(path));
                break;
            }
            default:
                break;
        }
        Button btnPlay = (Button) rootView.findViewById(R.id.btnPlayPronuciation);
        Button btnPlay2 = (Button) rootView.findViewById(R.id.btnPlayPronuciation2);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.start();
            }
        });
        btnPlay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view2.start();
            }
        });



        return rootView;
    }

}
