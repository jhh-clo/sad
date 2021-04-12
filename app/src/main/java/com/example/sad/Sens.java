package com.example.sad;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Sens extends AppCompatActivity {
    private DatabaseReference mDatabase;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7;
    String tem1, tem2, tem3, tem4, tem5, tem6, ad1, ad2, ad3, ad4, ad5, ad6, co2_1, co2_2, co2_3, soil1, soil2;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sens);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        textView1 = (TextView) findViewById(R.id.tem1_text);
        textView2 = (TextView) findViewById(R.id.tem2_text);
        textView3 = (TextView) findViewById(R.id.tem3_text);
        textView4 = (TextView) findViewById(R.id.tem4_text);
        textView5 = (TextView) findViewById(R.id.hum);
        textView6 = (TextView) findViewById(R.id.co2);
        textView7 = (TextView) findViewById(R.id.soil);
        btn1 = (Button)findViewById(R.id.back_btn);


        mDatabase.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    final String uid = user.getUid();
                    tem1 = dataSnapshot.child("ad").child(uid).child("hem").child("hem1").getValue().toString();
                    tem2 = dataSnapshot.child("ad").child(uid).child("hem").child("hem2").getValue().toString();
                    tem3 = dataSnapshot.child("ad").child(uid).child("hem").child("hem3").getValue().toString();
                    tem4 = dataSnapshot.child("ad").child(uid).child("hem").child("hem4").getValue().toString();
                    ad1 = dataSnapshot.child("ad").child(uid).child("hum").child("hum1").getValue().toString();
                    co2_1 = dataSnapshot.child("ad").child(uid).child("co2").child("co2_1").getValue().toString();
                    soil1 = dataSnapshot.child("ad").child(uid).child("soil").child("soil1").getValue().toString();

                    textView1.setText(tem1);
                    textView2.setText(tem2);
                    textView3.setText(tem3);
                    textView4.setText(tem4);
                    textView5.setText(ad1);
                    textView6.setText(co2_1);
                    textView7.setText(soil1);

                    int sd1 = Integer.parseInt(tem1);
                    int sd2 = Integer.parseInt(tem2);
                    int sd3 = Integer.parseInt(tem3);
                    int sd4 = Integer.parseInt(tem4);

                    if (sd1 <= 40) {
                        textView1.setBackgroundResource(R.drawable.hemlight_1);

                    } else if (sd1 > 41) {
                        textView1.setBackgroundResource(R.drawable.hemlight1_2);
                    }

                    if (sd2 <= 40) {
                        textView2.setBackgroundResource(R.drawable.hemlight_1);

                    } else if (sd2 > 41) {
                        textView2.setBackgroundResource(R.drawable.hemlight1_2);
                    }
                    if (sd3 <= 40) {
                        textView3.setBackgroundResource(R.drawable.hemlight_1);

                    } else if (sd3 > 41) {
                        textView3.setBackgroundResource(R.drawable.hemlight1_2);
                    }
                    if (sd4 <= 40) {
                        textView4.setBackgroundResource(R.drawable.hemlight_1);

                    } else if (sd4 > 41) {
                        textView4.setBackgroundResource(R.drawable.hemlight1_2);
                    }
                    btn1.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            //액티비티 시작!
                            startActivity(intent);
                        }
                    }); 
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }
}