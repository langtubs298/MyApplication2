package com.fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.entity.MyDictionaryRequest;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.listeners.ConnectionReceiver;
import com.something.hp.PictF.R;

import static android.content.Context.MODE_PRIVATE;
public class DictionaryFragment extends Fragment implements MyDictionaryRequest.CallbackInterface, RewardedVideoAdListener{

    private int numberSearch;
    private Button videoAd;
    private RewardedVideoAd mad;
    Boolean advertisement;
    public DictionaryFragment() {
        // Required empty public constructor
    }
    public static DictionaryFragment newInstance() {
        DictionaryFragment fragment = new DictionaryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    String url;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(getContext(),
                "ca-app-pub-6406105887192068~9314396907");
        if (getArguments() != null) {
            advertisement = getArguments().getBoolean("advertisement");
        }
        mad = MobileAds.getRewardedVideoAdInstance(getContext());
        mad.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();
        check();
    }

    public void check() {
        boolean ret = ConnectionReceiver.isConnected();
        String msg;
        if (ret == true) {
        } else {
            msg = "Vui lòng kết nối internet";
            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    private String dictionaryEntries() {
        final String language = "en";
        EditText input = (EditText) getActivity().findViewById(R.id.editInput);
        final String word = input.getText().toString();
        final String word_id = word.toLowerCase(); //word id is case sensitive and lowercase is required
        return "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
    }

    private void checkButton(){
        if(numberSearch>0){
            videoAd.setVisibility(View.INVISIBLE);
        }
        else {
            videoAd.setVisibility(View.VISIBLE);
        }
    }
    private void loadRewardedVideoAd(){
        if(!mad.isLoaded()){
            mad.loadAd(getString(R.string.rewardedAds), new AdRequest.Builder().build());
        }
    }
    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dictionary, container, false);
        videoAd = (Button) rootView.findViewById(R.id.videoAd);
        Animation animFadein;
        animFadein = AnimationUtils.loadAnimation(getActivity(), R.anim.animation1);

        Button btnSearch = (Button) rootView.findViewById(R.id.btnFind);
        final TextView txtMeaning = (TextView) rootView.findViewById(R.id.textMeaning);
        final TextView txtMean = (TextView) rootView.findViewById(R.id.textMean);
        final TextView txtsomething = (TextView) rootView.findViewById(R.id.textsomething);
        final TextView txtExam = (TextView) rootView.findViewById(R.id.textExam);
        final TextView txtWord = (TextView) rootView.findViewById(R.id.textWord);
        final ImageView sound = (ImageView) rootView.findViewById(R.id.imageVolume);
        final ImageView record = (ImageView) rootView.findViewById(R.id.imageRecordDic);
        final TextView textScore = (TextView) rootView.findViewById(R.id.textScore);
        final TextView textLexical = (TextView) rootView.findViewById(R.id.textLexical);
        textScore.startAnimation(animFadein);
        restoringPreferences();
        checkButton();
        textScore.setText(numberSearch+" word(s)");
        final TextView txtPronunciation = (TextView) rootView.findViewById(R.id.textPronunciation);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberSearch==0){
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle("Hết lượt tìm");
                    alertDialog.setMessage("Bạn cần vào cửa hàng để mua thêm");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Đóng",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                else if(!ConnectionReceiver.isConnected()){
                    Toast.makeText(getContext(), "Vui lòng kết nối internet", Toast.LENGTH_SHORT).show();
                }
                    else{
                    txtMeaning.setText("");
                    txtMean.setText("");
                    txtExam.setText("");
                    txtsomething.setText("");
                    txtWord.setText("");
                    textLexical.setText("");
                    txtPronunciation.setText("");

                    url = dictionaryEntries();
                    MyDictionaryRequest dictionaryRequest;
                    dictionaryRequest = new MyDictionaryRequest(getActivity(),textLexical, txtMean, txtsomething, txtExam, txtMeaning, txtPronunciation, txtWord, sound, record);
                    dictionaryRequest.execute(url);
                    numberSearch--;
                    textScore.setText(numberSearch+" Word(s)");
                    checkButton();
                }
            }
        });

        videoAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mad.isLoaded()){
                    mad.show();
                }
                else{
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle("Đợi");
                    alertDialog.setMessage("Không có video để load");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Đóng",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });
        return rootView;
    }

    @Override
    public void onHandleSelection(String text, int table) {

    }

    @Override
    public void onResume() {
        mad.resume(getContext());
        super.onResume();
        restoringPreferences();
    }

    public void restoringPreferences()
    {
        SharedPreferences pre=getContext().getSharedPreferences
                ("search",MODE_PRIVATE);
        //lấy giá trị checked ra, nếu không thấy thì giá trị mặc định là false
        numberSearch=pre.getInt("numberSearch", 5);
    }

    @Override
    public void onPause() {
        mad.pause(getContext());
        super.onPause();
        savingPreferences();
    }

    public void savingPreferences()
    {
        //tạo đối tượng getSharedPreferences
        SharedPreferences pre=getContext().getSharedPreferences
                ("search", MODE_PRIVATE);
        //tạo đối tượng Editor để lưu thay đổi
        SharedPreferences.Editor editor=pre.edit();
        //lưu vào editor
        editor.putInt("numberSearch", numberSearch);
        //chấp nhận lưu xuống file
        editor.commit();
    }

    @Override
    public void onDestroy() {
        mad.destroy(getContext());
        super.onDestroy();
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

        loadRewardedVideoAd();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

        numberSearch+=1;
        checkButton();
        savingPreferences();
        final TextView textScore = (TextView) getActivity().findViewById(R.id.textScore);
        textScore.setText(numberSearch+" Word(s)");
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }
}
