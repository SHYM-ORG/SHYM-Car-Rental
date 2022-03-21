package com.shym.front_end.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.shym.front_end.databinding.FragmentHomeBinding;
import com.shym.front_end.ui.bienvenueClient.ButtonClickListener;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textHome;
        final Button bienvClient = binding.bienvenueClient;
        final Button bienvAgence = binding.bienvenueAgence;
        ButtonClickListener listener = new ButtonClickListener(this.getActivity());
        bienvAgence.setOnClickListener(listener);
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