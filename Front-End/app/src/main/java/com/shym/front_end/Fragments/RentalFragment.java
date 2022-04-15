package com.shym.front_end.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shym.front_end.R;
import com.shym.front_end.adapter.CarAdapter;
import com.shym.front_end.databinding.FragmentHomeBinding;
import com.shym.front_end.databinding.FragmentRentalBinding;
import com.shym.front_end.models.Car;
import com.shym.front_end.utils.VolleyUtils;

import java.util.ArrayList;


public class RentalFragment extends Fragment {

    private FragmentRentalBinding binding;
    private RecyclerView recyclerViewCars;
    private CarAdapter carAdapter;

    private ArrayList<Car> carList;

    private TextView textview;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentRentalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerViewCars = binding.recyclerCar;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCars.setHasFixedSize(true);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        recyclerViewCars.setLayoutManager(linearLayoutManager);

        carList = new ArrayList<>();
        carAdapter = new CarAdapter(getContext(), carList);
        recyclerViewCars.setAdapter(carAdapter);

        VolleyUtils.readCars("https://fakestoreapi.com/products/",getContext(),carAdapter,carList);




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}