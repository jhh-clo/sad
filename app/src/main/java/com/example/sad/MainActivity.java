package com.example.sad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private DatabaseReference mDatabase;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    Switch fen1,fen2;
    Button btn1,btn2,btn3;
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8;
    String a1,a2,a3,a4,a5,a6;
    boolean buttonon1,buttonon2;

    private static final String ex1 = "switch1";
    private static final String ex2 = "switch2";
    private static final String ex3 = "switch3";
    private static final String ex4 = "switch4";
    private static final String ex5 = "switch5";
    private static final String ex6 = "switch6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        sharedPreferences = getSharedPreferences("", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        fen1 = (Switch)findViewById(R.id.fen_sw1);
        fen2 = (Switch)findViewById(R.id.fen_sw2);
        btn1 = (Button)findViewById(R.id.win_btn1);
        btn2 = (Button)findViewById(R.id.win_btn2);
        btn3 = (Button)findViewById(R.id.next_btn);
        textView1 = (TextView)findViewById(R.id.led_text1);
        textView2 = (TextView)findViewById(R.id.led_text2);
        textView3 = (TextView)findViewById(R.id.win_led1);
        textView5 = (TextView)findViewById(R.id.win_led2);
        textView4 = (TextView)findViewById(R.id.win2_led1);
        textView6 = (TextView)findViewById(R.id.win2_led2);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    final String uid = user.getUid();
                    // win1.setChecked(sharedPreferences.getBoolean(ex1, false));
                    //win2.setChecked(sharedPreferences.getBoolean(ex2, false));
                    //win3.setChecked(sharedPreferences.getBoolean(ex3, false));
                    //win4.setChecked(sharedPreferences.getBoolean(ex4, false));
                    fen1.setChecked(sharedPreferences.getBoolean(ex5, false));
                    fen2.setChecked(sharedPreferences.getBoolean(ex6, false));
                    a1 = dataSnapshot.child("ad").child(uid).child("fen1_trip").getValue().toString();
                    a2 = dataSnapshot.child("ad").child(uid).child("fen2_trip").getValue().toString();
                    a3 = dataSnapshot.child("ad").child(uid).child("win1_trip").getValue().toString();
                    a4 = dataSnapshot.child("ad").child(uid).child("win2_trip").getValue().toString();
                    a5 = dataSnapshot.child("ad").child(uid).child("win1_1").getValue().toString();
                    a6 = dataSnapshot.child("ad").child(uid).child("win2_1").getValue().toString();
                    int sd1 = Integer.parseInt(a1) ;
                    int sd2 = Integer.parseInt(a2) ;
                    int sd3 = Integer.parseInt(a3) ;
                    int sd4 = Integer.parseInt(a4) ;

                    btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(!buttonon1){
                                buttonon1 = true;
                                btn1.setBackground(getResources().getDrawable(R.drawable.windowsopen_1));
                                myRef.child("ad").child(uid).child("win1_1").setValue("on");
                                myRef.child("ad").child(uid).child("win1_2").setValue("off");



                            }
                            else {
                                buttonon1 = false;
                                btn1.setBackground(getResources().getDrawable(R.drawable.windowsclose_1));
                                myRef.child("ad").child(uid).child("win1_1").setValue("off");
                                myRef.child("ad").child(uid).child("win1_2").setValue("on");
                            }
                        }
                    });
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(!buttonon2){
                                buttonon2 = true;
                                btn2.setBackground(getResources().getDrawable(R.drawable.windowsopen_2));
                                myRef.child("ad").child(uid).child("win2_1").setValue("on");
                                myRef.child("ad").child(uid).child("win2_2").setValue("off");
                            }
                            else{
                                buttonon2 = false;
                                btn2.setBackground(getResources().getDrawable(R.drawable.windowsclose_2));
                                myRef.child("ad").child(uid).child("win2_2").setValue("on");
                                myRef.child("ad").child(uid).child("win2_1").setValue("off");
                            }
                        }
                    });
                    btn3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), Sens.class);
                            //액티비티 시작!
                            startActivity(intent);
                        }
                        }

                        );



                    fen1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                            // 스위치 버튼이 체크되었는지 검사하여 텍스트뷰에 각 경우에 맞게 출력합니다.
                            if (isChecked) {
                                editor.putBoolean(ex5,true);
                                myRef.child("ad").child(uid).child("pen1").setValue("on");


                            } else {
                                editor.putBoolean(ex5,false);


                                myRef.child("ad").child(uid).child("pen1").setValue("off");
                            }
                            editor.commit();

                        }
                    });
                    fen2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                            // 스위치 버튼이 체크되었는지 검사하여 텍스트뷰에 각 경우에 맞게 출력합니다.
                            if (isChecked) {
                                editor.putBoolean(ex6,true);


                                myRef.child("ad").child(uid).child("pen2").setValue("on");


                            } else {
                                editor.putBoolean(ex6,false);


                                myRef.child("ad").child(uid).child("pen2").setValue("off");
                            }
                            editor.commit();

                        }
                    });
                    if(sd1==0){
                        textView1.setBackgroundResource(R.drawable.ledgreen_1);


                    }
                    else{
                        textView1.setBackgroundResource(R.drawable.ledred_1);

                    }
                    if(sd2==0){
                        textView2.setBackgroundResource(R.drawable.ledgreen_1);
                    }
                    else {
                        textView2.setBackgroundResource(R.drawable.ledred_1);

                    }
                    if (sd3==0){
                        textView3.setBackgroundResource(R.drawable.ledgreen_1);


                    }
                    else {
                        textView3.setBackgroundResource(R.drawable.ledred_1);

                    }
                    if (sd4==0){
                        textView4.setBackgroundResource(R.drawable.ledgreen_1);


                    }
                    else {
                        textView4.setBackgroundResource(R.drawable.ledred_1);

                    }
                    if(a5.equals("on")){
                        textView5.setBackgroundResource(R.drawable.ledred_1);




                    }
                    else {
                        textView5.setBackgroundResource(R.drawable.ledgreen_1);
                    }
                    if(a6.equals("on")){
                        textView6.setBackgroundResource(R.drawable.ledgreen_1);
                        textView6.setBackgroundResource(R.drawable.ledred_1);




                    }
                    else {
                        textView6.setBackgroundResource(R.drawable.ledgreen_1);

                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}