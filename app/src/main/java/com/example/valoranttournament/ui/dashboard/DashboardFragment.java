package com.example.valoranttournament.ui.dashboard;

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
import com.example.valoranttournament.databinding.FragmentDashboardBinding;
import com.example.valoranttournament.faq_page;
import com.example.valoranttournament.guide_page;
import com.example.valoranttournament.maps_page;
import com.example.valoranttournament.patch_note;

public class DashboardFragment extends Fragment {

    private DashboardViewModel DashboardViewModel;
    private FragmentDashboardBinding binding;
    LinearLayout patchnotes;
    LinearLayout guide;
    LinearLayout maps;
    LinearLayout faq;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel  =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //link xml file with java file
        patchnotes = root.findViewById(R.id.patchnotes);
        patchnotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), patch_note.class));
            }
        });

        //link xml file with java file
        guide = root.findViewById(R.id.guide);
        guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), guide_page.class));
            }
        });

        //link xml file with java file
        maps = root.findViewById(R.id.maps);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), maps_page.class));
            }
        });

        //link xml file with java file
        faq = root.findViewById(R.id.faq);
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), faq_page.class));
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