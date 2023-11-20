package com.example.valoranttournament;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.valoranttournament.Adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class admin_panel extends AppCompatActivity {

    //DatabaseReference dr, dr2;
    //EditText prizepool1;
    //EditText prizepool2;
    //EditText prizepool3;
    //EditText matchName;
    //EditText datetxt;
    //EditText timetxt;

    //EditText scrimName,dateScrim,timeScrim,scrimFormat;
    //Button submit_match;
    //Spinner spinner1;
    //String[] gametype = {"Choose Game Type","Valorant Tournament", "Valorant Scrimmage"};
    //Spinner spinner2;
    //String[] tour_formattext = {"Choose Tournament  Format","Single Elimination", "Double Elimination"};

    //RecyclerView recyclerView;
    //AdminAdapter mAdapter;
    //ArrayList<Match_Model> list;

    TabLayout tabLayout;
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        getSupportActionBar().hide();

        tabLayout = findViewById(R.id.tablayout);
        viewpager = findViewById(R.id.viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewpager);

        /*prizepool1 = findViewById(R.id.prizepool1);
        prizepool2 = findViewById(R.id.prizepool2);
        prizepool3 = findViewById(R.id.prizepool3);

        matchName = findViewById(R.id.matchName);
        datetxt = findViewById(R.id.datetxt);
        timetxt = findViewById(R.id.timetxt);

        submit_match = findViewById(R.id.submit_match);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<Match_Model>();
        //retrieve data from database
        FirebaseDatabase.getInstance().getReference().child("New Match").child("Valorant Tournament").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    Match_Model matchModel2 = snapshot1.getValue(Match_Model.class);
                    list.add(matchModel2);
                }
                // set data in recycleView
                mAdapter = new AdminAdapter( admin_panel.this, list);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //spinner text
        spinner1 = findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, androidx.appcompat.
                R.layout.support_simple_spinner_dropdown_item, gametype);
        spinner1.setAdapter(adapter1);

        spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, androidx.appcompat.
                R.layout.support_simple_spinner_dropdown_item, tour_formattext);
        spinner2.setAdapter(adapter2);

        // add date in database
        submit_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prizepooltext1 = prizepool1.getText().toString();
                String prizepooltext2 = prizepool2.getText().toString();
                String prizepooltext3 = prizepool3.getText().toString();
                String matchNo = matchName.getText().toString();
                String datetext = datetxt.getText().toString();
                String timetext = timetxt.getText().toString();
                String gametype = spinner1.getSelectedItem().toString().toString();
                String tour_formattext = spinner2.getSelectedItem().toString().toString();

                if (TextUtils.isEmpty(prizepooltext1) || TextUtils.isEmpty(prizepooltext2)
                        || TextUtils.isEmpty(prizepooltext3) || TextUtils.isEmpty(matchNo)
                        || TextUtils.isEmpty(datetext) || TextUtils.isEmpty(timetext))
                {
                    Toast.makeText(admin_panel.this, "Enter Details", Toast.LENGTH_SHORT).show();
                }
                else if (gametype.equals("Valorant Tournament"))
                {
                    dr = FirebaseDatabase.getInstance().getReference().child("New Match").child("Valorant Tournament");
                    String matchid = dr.push().getKey();

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("_prizepool1", prizepooltext1);
                    hashMap.put("_prizepool2", prizepooltext2);
                    hashMap.put("_prizepool3", prizepooltext3);
                    hashMap.put("_MatchNo", matchNo);
                    hashMap.put("_date","Date:" +datetext);
                    hashMap.put("_time","Time:" +timetext);
                    hashMap.put("_tourformat", tour_formattext);
                    hashMap.put("gametype", gametype);
                    hashMap.put("matchid", matchid);
                    dr.child(matchid).setValue(hashMap);

                    Toast.makeText(admin_panel.this, "Added Details", Toast.LENGTH_SHORT).show();
                }
                else if (gametype.equals("Valorant Scrimmage"))
                {
                    dr2 = FirebaseDatabase.getInstance().getReference().child("New Match").child("Valorant Scrimmage");
                    String matchid = dr2.push().getKey();

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("_MatchNo", matchNo);
                    hashMap.put("_date","Date:" +datetext);
                    hashMap.put("_time","Time:" +timetext);
                    hashMap.put("_tourformat", tour_formattext);
                    hashMap.put("gametype", gametype);
                    hashMap.put("matchid", matchid);
                    dr2.child(matchid).setValue(hashMap);

                    Toast.makeText(admin_panel.this, "Added Details", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }
}