package com.shym.front_end.Fragments;

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

import com.shym.front_end.R;
import com.shym.front_end.databinding.FragmentHomeBinding;
import com.shym.front_end.ui.bienvenueAgency.BienvenueAgencyActivity;
import com.shym.front_end.ui.bienvenueClient.ClientButtonClickListener;

public class HomeFragment extends Fragment {


    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button bienvClient = binding.bienvenueClient;
        final Button bienvAgence = binding.bienvenueAgence;
        ClientButtonClickListener listener = new ClientButtonClickListener(this.getActivity());
        bienvAgence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BienvenueAgencyActivity.class);
                getActivity().startActivity(intent);

            }
        });
        bienvClient.setOnClickListener(listener);
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}