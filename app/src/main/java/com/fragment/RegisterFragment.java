package com.fragment;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.entity.IdDevice;
import com.entity.Member;
import com.something.hp.PictF.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class RegisterFragment extends Fragment {
    private DatabaseReference mDatabase;
    private ArrayList<IdDevice> listDeviceId = new ArrayList<>();
    private ArrayList<IdDevice> listDeviceId3 = new ArrayList<>();

    public RegisterFragment() {
        // Required empty public constructor
    }

    int numberSearch;
    Boolean advertisement;

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        restoringPreferences();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        final EditText editEmail = (EditText) rootView.findViewById(R.id.editEmail);
        final EditText sdt = (EditText) rootView.findViewById(R.id.editSDT);
        final EditText editKey = (EditText) rootView.findViewById(R.id.editKey);
        final Spinner spinner = (Spinner) rootView.findViewById(R.id.groupSelect);
        TextView txtView = (TextView) rootView.findViewById(R.id.page_shop);
        txtView.setMovementMethod(LinkMovementMethod.getInstance());
        Button btn = (Button) rootView.findViewById(R.id.buttonRegister);
        mDatabase.child("keyAdvertisement").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                IdDevice idDevice1 = dataSnapshot.getValue(IdDevice.class);
                listDeviceId.add(idDevice1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase.child("keyDictionary").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                IdDevice idDevice1 = dataSnapshot.getValue(IdDevice.class);
                listDeviceId3.add(idDevice1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tmp = editKey.getText().toString();
                Boolean ch = false;
                String selectedCart = spinner.getSelectedItem().toString();
                if(selectedCart.equalsIgnoreCase("Tắt quảng cáo")){

                    for(int i = 0; i<listDeviceId.size();i++){
                        if(listDeviceId.get(i).getIdd().equalsIgnoreCase(tmp)){
                            ch = true;
                        }
                    }
                    if(ch){
                        String android_id = Settings.Secure.getString(getActivity().getContentResolver(),
                                Settings.Secure.ANDROID_ID);
                        Member member = new Member();
                        member.setDeviceID(android_id);
                        member.setKeyID(tmp);
                        member.setEmail(editEmail.getText().toString());
                        member.setSdt(sdt.getText().toString());
                        mDatabase.child("Member").push().setValue(member, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                if(databaseError == null){
                                    Query queryRef = mDatabase.child("keyAdvertisement").orderByChild("idd").equalTo(tmp);
                                    queryRef.addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                                            snapshot.getRef().setValue(null);
                                            advertisement = true;
                                            final String android_id = Settings.Secure.getString(getActivity().getContentResolver(),
                                                    Settings.Secure.ANDROID_ID);
                                            IdDevice idDevice = new IdDevice();
                                            idDevice.setIdd(android_id);
                                            mDatabase.child("advertisementId").push().setValue(idDevice);
                                            listDeviceId.clear();
                                            successfulNotificationAd();
                                        }

                                        @Override
                                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                                        }

                                        @Override
                                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                                        }

                                        @Override
                                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                                }
                                else {
                                    failedNotification();
                                }
                            }
                        });
                    }
                    else {
                        listDeviceId.clear();
                        keyNotification();
                    }
                }
                else if(selectedCart.equalsIgnoreCase("Mua thêm từ")){

                    for(int i = 0; i<listDeviceId3.size();i++){
                        if(listDeviceId3.get(i).getIdd().equalsIgnoreCase(tmp)){
                            ch = true;
                        }
                    }

                    if(ch){
                        String android_id = Settings.Secure.getString(getActivity().getContentResolver(),
                                Settings.Secure.ANDROID_ID);
                        Member member = new Member();
                        member.setDeviceID(android_id);
                        member.setKeyID(tmp);
                        member.setEmail(editEmail.getText().toString());
                        member.setSdt(sdt.getText().toString());
                        mDatabase.child("Member").push().setValue(member, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                if(databaseError == null){
                                    Query queryRef = mDatabase.child("keyDictionary").orderByChild("idd").equalTo(tmp);
                                    queryRef.addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                                            snapshot.getRef().setValue(null);
                                            numberSearch+=500;
                                            listDeviceId3.clear();
                                            successfulNotification();
                                            FragmentManager manager = getActivity().getSupportFragmentManager();
                                            FragmentTransaction transaction = manager.beginTransaction();
                                            DictionaryFragment fragment = new DictionaryFragment();
                                            //Khi được goi, fragment truyền vào sẽ thay thế vào vị trí FrameLayout trong Activity chính
                                            transaction.replace(R.id.fmContent, fragment);
                                            transaction.addToBackStack("Frag1");
                                            transaction.commit();
                                        }

                                        @Override
                                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                                        }

                                        @Override
                                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                                        }

                                        @Override
                                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                                }
                                else {
                                    failedNotification();
                                }
                            }
                        });
                    }
                    else {
                        listDeviceId3.clear();
                        keyNotification();
                    }
                }
                else{
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle("Lỗi!");
                    alertDialog.setMessage("Vui lòng chọn gói");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Đóng",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }//end select
            }

        });
        return rootView;
    }


    public void failedNotification(){
        listDeviceId.clear();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("Lỗi!");
        alertDialog.setMessage("Đăng ký thất bại");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Đóng",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void keyNotification(){
        listDeviceId.clear();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("Lỗi!");
        alertDialog.setMessage("Key sai");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Đóng",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void successfulNotification(){
        listDeviceId.clear();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("Thông báo!");
        alertDialog.setMessage("Đăng ký thành công!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Đóng",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void successfulNotificationAd(){
        listDeviceId.clear();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("Thông báo!");
        alertDialog.setMessage("Đăng ký thành công, hãy khởi động lại phần mềm");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Đóng",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        restoringPreferences();
    }

    public void restoringPreferences()
    {
        SharedPreferences pre=getContext().getSharedPreferences
                ("search",MODE_PRIVATE);
        //lấy giá trị checked ra, nếu không thấy thì giá trị mặc định là false
        numberSearch = pre.getInt("numberSearch", 10);
        advertisement = pre.getBoolean("advertisement", false);
    }

    @Override
    public void onPause() {
        super.onPause();
        savingPreferences();
    }

    public void savingPreferences()
    {
        //tạo đối tượng getSharedPreferences
        SharedPreferences pre=getActivity().getSharedPreferences
                ("search", getContext().MODE_PRIVATE);
        //tạo đối tượng Editor để lưu thay đổi
        SharedPreferences.Editor editor=pre.edit();
        //lưu vào editor
        editor.putInt("numberSearch", numberSearch);
        editor.putBoolean("advertisement", advertisement);
        //chấp nhận lưu xuống file
        editor.commit();
    }
}
