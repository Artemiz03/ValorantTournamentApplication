package com.example.valoranttournament.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.valoranttournament.Host_Control_Activity;
import com.example.valoranttournament.Models.Model;
import com.example.valoranttournament.R;
import com.example.valoranttournament.admin_panel;
import com.example.valoranttournament.databinding.FragmentNotificationsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NotificationsFragment extends Fragment {

    TextView Username;
    FirebaseUser auth;
    Model model;
    Button btnSettings;
    private NotificationsViewModel NotificationsViewModel;
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        NotificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btnSettings = root.findViewById(R.id.btnSettings);

        Username = root.findViewById(R.id.Username);
        //Retrieve data from database and display on profile
        auth = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                /*String name = snapshot.child("name").getValue().toString();
                Username.setText(name);*/

                Model model = snapshot.getValue(Model.class);
                Username.setText(""+model.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("AppHost")
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.child(auth.getUid()).exists()){
                                            startActivity(new Intent(getContext(), admin_panel.class));
                                        }
                                        else {
                                            startActivity(new Intent(getContext(), Host_Control_Activity.class));
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}