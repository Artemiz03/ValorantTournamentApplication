package com.example.valoranttournament.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.valoranttournament.R;
import com.example.valoranttournament.databinding.FragmentHomeBinding;
import com.example.valoranttournament.valorant_activity;
import com.example.valoranttournament.valorant_scrim_layout;


public class HomeFragment extends Fragment {

    private HomeViewModel HomeViewModel;
    private FragmentHomeBinding binding;
    LinearLayout tour_match_layout;
    LinearLayout valorant_tour_layout;
    LinearLayout valorant_scrim_layout;
    LinearLayout valorant_add_friends;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel  =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //link xml file with java file
        valorant_tour_layout = root.findViewById(R.id.valorant_tour_layout);


        valorant_tour_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //For Fragment use getContext()--
                startActivity(new Intent(getContext(),valorant_activity.class));
            }
        });

        //link xml file with java file
        valorant_scrim_layout = root.findViewById(R.id.valorant_scrim_layout);

        valorant_scrim_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //For Fragment use getContext()--
                startActivity(new Intent(getContext(),valorant_scrim_layout.class));
            }
        });

        //link xml file with java file

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}