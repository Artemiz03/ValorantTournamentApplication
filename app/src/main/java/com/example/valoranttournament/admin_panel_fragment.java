package com.example.valoranttournament;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class admin_panel_fragment extends Fragment {

    DatabaseReference dr, dr2;
    EditText prizepool1;
    EditText prizepool2;
    EditText prizepool3;
    EditText matchName;
    EditText datetxt;
    EditText timetxt;

    EditText scrimName,dateScrim,timeScrim,scrimFormat;
    Button submit_match;
    Spinner spinner1;
    String[] gametype = {"Choose Game Type","Valorant Tournament", "Valorant Scrimmage"};
    Spinner spinner2;
    String[] tour_formattext = {"Choose Tournament  Format","Single Elimination", "Double Elimination"};



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_admin_panel_fragment, container, false);

        prizepool1 = view.findViewById(R.id.prizepool1);
        prizepool2 = view.findViewById(R.id.prizepool2);
        prizepool3 = view.findViewById(R.id.prizepool3);

        matchName = view.findViewById(R.id.matchName);
        datetxt = view.findViewById(R.id.datetxt);
        timetxt = view.findViewById(R.id.timetxt);

        submit_match = view.findViewById(R.id.submit_match);

        //spinner text
        spinner1 = view.findViewById (R.id.spinner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(), androidx.appcompat.
                R.layout.support_simple_spinner_dropdown_item, gametype);
        spinner1.setAdapter(adapter1);

        spinner2 = view.findViewById (R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getContext(), androidx.appcompat.
                R.layout.support_simple_spinner_dropdown_item, tour_formattext);
        spinner2.setAdapter(adapter2);

        // add date in database
        submit_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    Toast.makeText(getContext(), "Enter Details", Toast.LENGTH_SHORT).show();
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

                    Toast.makeText(getContext(), "Added Details", Toast.LENGTH_SHORT).show();

                    prizepool1.setText("");
                    prizepool2.setText("");
                    prizepool3.setText("");
                    matchName.setText("");
                    datetxt.setText("");
                    timetxt.setText("");

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

                    Toast.makeText(getContext(), "Added Details", Toast.LENGTH_SHORT).show();

                    prizepool1.setText("");
                    prizepool2.setText("");
                    prizepool3.setText("");
                    matchName.setText("");
                    datetxt.setText("");
                    timetxt.setText("");
                }
            }
        });
        return view;
    }
}