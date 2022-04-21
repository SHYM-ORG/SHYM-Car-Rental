package com.shym.front_end.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.shym.front_end.Fragments.HomeFragment;
import com.shym.front_end.Fragments.AgencyProfileFragment;
import com.shym.front_end.R;
import com.shym.front_end.adapter.CarAdapter;
import com.shym.front_end.models.Car;
import com.shym.front_end.ui.bienvenueClient.BienvenueClientActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class VolleyUtils {

    private final static String API_BASE_URL = "https://shym-api.herokuapp.com";
    //private final static String API_BASE_URL = "https://127.0.0.1:8000";

    public static String getImageUrl(String imageName) {
        return API_BASE_URL + "/get/offerImage?imageName=" + imageName;
    }

    public static  void readAvailableCars(Context context , CarAdapter carAdapter,ArrayList<Car> carList,ProgressBar progressBar) {

        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(context);
        // in this case the data we are getting is in the form
        // of array so we are making a json array request.
        // below is the line where we are making an json array
        // request and then extracting data from each json object.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, API_BASE_URL + "/get/agencyAvailableCars", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.INVISIBLE);
                // progressBar.setVisibility(View.GONE);
                // courseRV.setVisibility(View.VISIBLE);
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
                        String idOffer = responseObj.getString("idOffer");
                        String model = responseObj.getString("title");
                        String image = responseObj.getString("image");
                        String series = responseObj.getString("series");
                        boolean availableNow = responseObj.getBoolean("availableNow");
                        int pricePerDay = responseObj.getInt("pricePerDay");

                        carList.add(new Car( idOffer,  model,  series, pricePerDay, availableNow, image));
                        carAdapter.notifyDataSetChanged();


                        //  buildRecyclerView();
                    } catch (JSONException e) {
                        // progressBar.setVisibility(View.INVISIBLE);
                        // ShowPopUp(context, "ERRROR", e.toString());
                        e.printStackTrace();

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.INVISIBLE);
                ShowPopUp(context, "ERRROR", error.getMessage());

            }
        });
        queue.add(jsonArrayRequest);
    }

    public static  void readRentedCars(Context context , CarAdapter carAdapter,ArrayList<Car> carList,ProgressBar progressBar) {

        progressBar.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, API_BASE_URL + "/get/agencyRentedCars", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.INVISIBLE);
                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject responseObj = response.getJSONObject(i);

                        String idOffer = responseObj.getString("idOffer");
                        String model = responseObj.getString("title");
                        String image = responseObj.getString("image");
                        String series = responseObj.getString("series");
                        boolean availableNow = responseObj.getBoolean("availableNow");
                        int pricePerDay = responseObj.getInt("pricePerDay");

                        carList.add(new Car( idOffer,  model,  series, pricePerDay, availableNow, image));
                        carAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.INVISIBLE);
                ShowPopUp(context, "ERRROR", error.getMessage());

            }
        });
        queue.add(jsonArrayRequest);
    }


    public static void logIn(Map data, Context mContext, ProgressBar progressBar){
        RequestQueue requstQueue = Volley.newRequestQueue(mContext);
        JSONObject dataJson = new JSONObject(data);
        progressBar.setVisibility(View.VISIBLE);
        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, API_BASE_URL + "/login",dataJson,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        SharedPreferences sharedPreferences = mContext.getSharedPreferences("auth", mContext.MODE_PRIVATE);
                        SharedPreferences.Editor ed = sharedPreferences.edit();
                        try{
                            ed.putString("token", response.getString("token"));
                            ed.commit();
                        } catch (Exception e){}
                        replaceFragment(new HomeFragment(), (AppCompatActivity) mContext);
                        Intent intent = new Intent(mContext, BienvenueClientActivity.class);
                        mContext.startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            progressBar.setVisibility(View.INVISIBLE);
                            ShowPopUp(mContext, "Authentication Error", "Incorrect email or password !!");
                            System.out.println(dataJson.getString("firstName"));
                        } catch (Exception e) {}

                    }
                }
        ){
            //here I want to post data to sever
        };
        requstQueue.add(jsonobj);
    }
    public static void signUp(Map data, Context mContext, String type, ProgressBar progressBar){
        RequestQueue requstQueue = Volley.newRequestQueue(mContext);
        JSONObject dataJson = new JSONObject(data);
        progressBar.setVisibility(View.VISIBLE);
        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, API_BASE_URL + "/api/account/create/" + type,dataJson,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(mContext, "Account Created !", Toast.LENGTH_SHORT).show();
                        replaceFragment(new HomeFragment(),(AppCompatActivity) mContext);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.INVISIBLE);
                        ShowPopUp(mContext, "Registration Error", " Oops! Try again !!");
                        try {
                            System.out.println(dataJson.getString("firstName"));
                        } catch (Exception e) {}

                    }
                }
        ){
            //here I want to post data to sever
        };
        requstQueue.add(jsonobj);
    }

    public static void signUpClient(Map data, Context mContext, ProgressBar progressBar){
        signUp(data, mContext, "client", progressBar);
    }

    public static void signUpAgency(Map data, Context mContext, ProgressBar progressBar){
        signUp(data, mContext, "agency", progressBar);
    }

    private static void replaceFragment(Fragment fragment, AppCompatActivity activity){
        FragmentManager fragmentManager=activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
    public static void ShowPopUp(Context mContext, String title, String msg) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(msg).setCancelable(false).setPositiveButton("Cancel",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                //Activity.this.finish();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void sendMessage(Map data, Context mContext) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JSONObject dataJson = new JSONObject(data);

        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, API_BASE_URL + "/contactUs/", dataJson,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(mContext, "Message Sent Successfully!", Toast.LENGTH_SHORT).show();
                        replaceFragment(new AgencyProfileFragment(),(AppCompatActivity) mContext);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ShowPopUp(mContext, "Error", "Try later...");
                    }
                }
        ){
            //here I want to post data to sever
        };
        requestQueue.add(jsonobj);
    }

    public static void updateProfile(Map data, Context mContext) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JSONObject dataJson = new JSONObject(data);

        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, API_BASE_URL + "/editProfile/", dataJson,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(mContext, "Profile Updated Successfully!", Toast.LENGTH_SHORT).show();
                        replaceFragment(new AgencyProfileFragment(),(AppCompatActivity) mContext);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ShowPopUp(mContext, "Error", "Try later...");
                    }
                }
        ){
            //here I want to post data to sever
        };
        requestQueue.add(jsonobj);
    }

}

