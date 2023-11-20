package com.example.valoranttournament;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valoranttournament.Adapters.teamsAdapter;
import com.example.valoranttournament.Models.Joined_Match_Model;
import com.example.valoranttournament.Models.Match_Model;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class valorant_detail_one extends AppCompatActivity {

    TextView _prizepool1_, _prizepool2_, _prizepool3_, _tourformat_, _date_, _time_, _matchName_;
    Button btnCancel, btnOk, btnJoin;
    EditText alert_text;

    RecyclerView teams_recycler;
    ArrayList<Joined_Match_Model> list;
    ArrayList<Match_Model> mlist;
    teamsAdapter mAdapter;
    FirebaseUser auth;

    String matchid;

    DatabaseReference dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valorant_detail_one);
        getSupportActionBar().hide();

        //link
        _prizepool1_ = findViewById(R.id._prizepool1_);
        _prizepool2_ = findViewById(R.id._prizepool2_);
        _prizepool3_ = findViewById(R.id._prizepool3_);
        _tourformat_ = findViewById(R.id._tourformat_);
        _date_ = findViewById(R.id._date_);
        _time_ = findViewById(R.id._time_);
        _matchName_ = findViewById(R.id._matchName_);
        btnJoin = findViewById(R.id.btnJoin);

        auth = FirebaseAuth.getInstance().getCurrentUser();

        //get data
        matchid = getIntent().getStringExtra("matchid");

        teams_recycler = findViewById(R.id.teams_recycler);
        teams_recycler.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<Joined_Match_Model>();

        //retrieve data of joining players
        /*FirebaseDatabase.getInstance().getReference().child("Joined Tournament").child("Valorant Tournament")
                .child("matchid")
                .addValueEventListener(new ValueEventListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                            Joined_Match_Model joined_match_model = dataSnapshot1.getValue(Joined_Match_Model.class);
                            list.add(joined_match_model);

                            //retrive data & check userUID exist
                            FirebaseDatabase.getInstance().getReference().child("New Match")
                                    .child("Valorant Tournament")
                                    .child("matchid")
                                    .addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.child(auth.getUid()).exists()){
                                                btnJoin.setText("Joined");
                                                btnJoin.setBackgroundColor(Color.parseColor("#FF2A972E"));
                                                btnJoin.setClickable(false);
                                            }
                                            else {
                                                btnJoin.setText("Join Now");
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                        }
                        mAdapter = new teamsAdapter(list, valorant_detail_one.this);
                        teams_recycler.setAdapter(mAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/


        String prizepooltext1_data = getIntent().getStringExtra("_prizepool1");
        _prizepool1_.setText(""+prizepooltext1_data);

        String prizepooltext2_data = getIntent().getStringExtra("_prizepool2");
        _prizepool2_.setText(""+prizepooltext2_data);

        String prizepooltext3_data = getIntent().getStringExtra("_prizepool3");
        _prizepool3_.setText(""+prizepooltext3_data);

        String tour_formattext_data = getIntent().getStringExtra("_tourformat");
        _tourformat_.setText(""+tour_formattext_data);

        String datetext_data = getIntent().getStringExtra("_date");
        _date_.setText(""+datetext_data);

        String timetext_data = getIntent().getStringExtra("_time");
        _time_.setText(""+timetext_data);

        String Match_Name_data = getIntent().getStringExtra("_matchNo");
        _matchName_.setText(""+Match_Name_data);

    }


    public void show_alert_dialog(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(valorant_detail_one.this);
        View mView = getLayoutInflater().inflate(R.layout.alert_layout, null);
        alert.setView(mView);

        btnOk = mView.findViewById(R.id.btnOk);
        btnCancel = mView.findViewById(R.id.btnCancel);

        AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        alert_text = mView.findViewById(R.id.alert_text);
        String alert_txt = alert_text.getText().toString();

        btnOk = mView.findViewById(R.id.btnOk);
        btnCancel = mView.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        alertDialog.dismiss();
                    }
        });


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase.getInstance().getReference().child("Joined Tournament")
                        .child("Valorant Tournament").child("matchid")
                        .child(auth.getUid())
                        .setValue("true");

                dr = FirebaseDatabase.getInstance().getReference().child("Joined Tournament")
                        .child("Valorant Tournament").child("matchid");

                String joinid = dr.push().getKey();
                String alert_txt = alert_text.getText().toString();
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("joinedname",alert_txt);
                hashMap.put("userid", auth.getUid());
                hashMap.put("matchid", matchid);
                hashMap.put("joinid", joinid);

                dr.child(joinid).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(valorant_detail_one.this, "Joined Successfully", Toast.LENGTH_SHORT).show();

                        // update join now
                        btnJoin.setText("Joined");
                        btnJoin.setBackgroundColor(Color.parseColor("#FF2A972E"));
                        btnJoin.setClickable(false);
                    }
                });

                alertDialog.dismiss();


            }
        });
                alertDialog.show();
            }

}