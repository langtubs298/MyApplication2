package com.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.adapter.CustomPracticePronounAdapter;
import com.something.hp.PictF.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.melnykov.fab.FloatingActionButton;

public class DetailPronunciationFragment extends Fragment implements CustomPracticePronounAdapter.CallbackInterface {
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    public int pronunc;
    public String how;
    private TextView mHow;
    private InterstitialAd mInterstitialAd;
    Boolean advertisement;

    public DetailPronunciationFragment() {
        // Required empty public constructor
    }
    public static DetailPronunciationFragment newInstance() {
        DetailPronunciationFragment fragment = new DetailPronunciationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MobileAds.initialize(getContext(), getString(R.string.idAds));
        if (getArguments() != null) {
            pronunc = getArguments().getInt("pronunc");
            how = getArguments().getString("how");
            advertisement = getArguments().getBoolean("advertisement");
        }
        if(!advertisement){
            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd = new InterstitialAd(getActivity());
            mInterstitialAd.setAdUnitId(getString(R.string.interstitial_id));
            mInterstitialAd.loadAd(adRequest);

            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded(){
                    showInterstitial();
                }
            });
        }
    }
    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_detail_pronunciation, container, false);
        mHow = (TextView) rootView.findViewById(R.id.txtUse);
        mHow.setText(how);
        FloatingActionButton btnPractice = (FloatingActionButton) rootView.findViewById(R.id.btnPractice);
        btnPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PracticePronounFragment practicePronounFragment = new PracticePronounFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                Bundle bundle = new Bundle();
                bundle.putInt("pronunc", pronunc);
                bundle.putBoolean("advertisement", advertisement);
                FragmentTransaction transaction = manager.beginTransaction();
                practicePronounFragment.setArguments(bundle);
                //Khi được goi, fragment truyền vào sẽ thay thế vào vị trí FrameLayout trong Activity chính
                transaction.replace(R.id.fmContent, practicePronounFragment);
                transaction.addToBackStack("Frag1").commit();
            }
        });
        String path = "";

        final VideoView view = (VideoView) rootView.findViewById(R.id.videoPronunciation);
        switch (pronunc){
            case 1:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.i;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 2:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.si;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 3:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.uu;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 4:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.u;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 5:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.aa;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 6:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.aw;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 7:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.o;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 8:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.a;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 9:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.e;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 10:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.ae;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 11:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.ow;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 12:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.se;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 13:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.ir;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 14:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.er;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 15:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.ue;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 16:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.ei;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 17:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.ai;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 18:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.oi;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 19:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.ou;
                view.setVideoURI(Uri.parse(path));
                break;
            }

            case 20:
            {
                path = "android.resource://" + getActivity().getPackageName() + "/"
                        + R.raw.au;
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
        FloatingActionButton btnPlay = (FloatingActionButton) rootView.findViewById(R.id.btnPlayPronunciation);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    view.start();
                }
        });
        return rootView;
    }

    @Override
    public void onHandleSelection(int position, String text, int pronunc, int table) {
//        startVoiceInput();
    }
}
