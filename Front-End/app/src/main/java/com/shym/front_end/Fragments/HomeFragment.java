package com.shym.front_end.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.shym.front_end.R;
import com.shym.front_end.adapter.CarAdapter;
import com.shym.front_end.databinding.FragmentHomeBinding;
import com.shym.front_end.models.Car;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    private FragmentHomeBinding binding;
    private RecyclerView recyclerViewCars;
    private CarAdapter carAdapter;
    //private List<Car> carList;
    private ArrayList<Car> carList;
   // private List<Car> carList2;
    private TextView textview;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerViewCars = binding.recyclerCar;
        textview=binding.textView;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCars.setHasFixedSize(true);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        recyclerViewCars.setLayoutManager(linearLayoutManager);

        carList = new ArrayList<>();
        carAdapter = new CarAdapter(getContext(), carList);
        recyclerViewCars.setAdapter(carAdapter);
        readCars(getContext());


        //Toast.makeText(getContext(), "testst", Toast.LENGTH_SHORT).show();




        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



    private void readCars(Context context) {



        Toast.makeText(context, "testst", Toast.LENGTH_SHORT).show();

        String url = "https://fakestoreapi.com/products/";
        RequestQueue queue = Volley.newRequestQueue(context);
        // in this case the data we are getting is in the form
        // of array so we are making a json array request.
        // below is the line where we are making an json array
        // request and then extracting data from each json object.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //progressBar.setVisibility(View.GONE);
                //courseRV.setVisibility(View.VISIBLE);
                for (int i = 0; i < response.length(); i++) {
                    // creating a new json object and
                    // getting each object from our json array.
                    try {
                        // we are getting each json object.
                        JSONObject responseObj = response.getJSONObject(i);


                        // now we get our response from API in json object format.
                        // in below line we are extracting a string with
                        // its key value from our json object.
                        // similarly we are extracting all the strings from our json object.
                        String place = responseObj.getString("title");
                        String model = responseObj.getString("title");
                        String image = responseObj.getString("image");


                        carList.add(new Car( place,  model,  image));
                        carAdapter.notifyDataSetChanged();


                      //  buildRecyclerView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        textview.setText("1Fail to get the data..");
                    }



                }
                carAdapter.notifyDataSetChanged();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textview.setText("Fail to get the data..");
            }
        });
        queue.add(jsonArrayRequest);
    }




}