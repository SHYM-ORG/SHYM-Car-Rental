package com.shym.front_end.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shym.front_end.AddCarAgencyActivity;
import com.shym.front_end.adapter.CarAdapter;
import com.shym.front_end.databinding.FragmentRentalBinding;
import com.shym.front_end.models.Car;
import com.shym.front_end.utils.VolleyUtils;

import java.util.ArrayList;


public class RentalFragment extends Fragment {

    private FragmentRentalBinding binding;
    private RecyclerView recyclerViewCars;
    private RecyclerView recyclerViewCars2;
    private CarAdapter carAdapter;
    private CarAdapter carAdapter2;

    private ArrayList<Car> carList;
    private ArrayList<Car> carList2;

    private TextView textview;
    private ImageButton addcar;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRentalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerViewCars = binding.recyclerCar;
        recyclerViewCars2 = binding.recyclercar2;
        ProgressBar progressBar;
        progressBar = binding.progressbar;
        ProgressBar progressBar2;
        progressBar2 = binding.progressbar2;
        progressBar.setVisibility(View.INVISIBLE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        recyclerViewCars.setHasFixedSize(true);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        recyclerViewCars.setLayoutManager(linearLayoutManager);
        recyclerViewCars2.setHasFixedSize(true);
        linearLayoutManager2.setStackFromEnd(true);
        linearLayoutManager2.setReverseLayout(true);

        recyclerViewCars2.setLayoutManager(linearLayoutManager2);

        carList = new ArrayList<>();
        carAdapter = new CarAdapter(getContext(), carList);
        carList2 = new ArrayList<>();
        carAdapter2 = new CarAdapter(getContext(), carList2);
        recyclerViewCars.setAdapter(carAdapter);
        recyclerViewCars2.setAdapter(carAdapter2);

        SharedPreferences sharedPref = getActivity().getApplicationContext().getSharedPreferences("auth", getActivity().MODE_PRIVATE);
        String token = sharedPref.getString("token", "null");
        if (!token.equals("null")) {
            VolleyUtils.readAvailableCars(getContext(),carAdapter,carList,progressBar);
            VolleyUtils.readRentedCars(getContext(),carAdapter2,carList2,progressBar2);
        }
        binding.addcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext() , AddCarAgencyActivity.class));

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