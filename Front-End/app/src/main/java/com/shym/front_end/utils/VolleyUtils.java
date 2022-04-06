package com.shym.front_end.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.shym.front_end.Fragments.HomeFragment;
import com.shym.front_end.R;
import com.shym.front_end.ui.bienvenueAgency.BienvenueAgencyActivity;
import com.shym.front_end.ui.bienvenueClient.BienvenueClientActivity;

import org.json.JSONObject;

import java.util.Map;

public class VolleyUtils {
    public static void logIn(Map data, Context mContext){
        RequestQueue requstQueue = Volley.newRequestQueue(mContext);
        JSONObject dataJson = new JSONObject(data);
        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, "https://shym-api.herokuapp.com/login",dataJson,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        SharedPreferences sharedPreferences = mContext.getSharedPreferences("auth", mContext.MODE_PRIVATE);
                        SharedPreferences.Editor ed = sharedPreferences.edit();
                        try{
                            ed.putString("token", response.getString("token"));
                        } catch (Exception e){}
                        ed.commit();
                        Intent intent = new Intent(mContext, BienvenueClientActivity.class);
                        mContext.startActivity(intent);
                        replaceFragment(new HomeFragment(),(AppCompatActivity) mContext);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
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

    public static void signUp(Map data, Context mContext, String type){
        RequestQueue requstQueue = Volley.newRequestQueue(mContext);
        JSONObject dataJson = new JSONObject(data);
        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, "https://shym-api.herokuapp.com/api/account/create/" + type,dataJson,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        replaceFragment(new HomeFragment(),(AppCompatActivity) mContext);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
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

    public static void signUpClient(Map data, Context mContext){
        signUp(data, mContext, "client");
    }

    public static void signUpAgency(Map data, Context mContext){
        signUp(data, mContext, "agency");
    }

    private static void replaceFragment(Fragment fragment, AppCompatActivity activity){
        FragmentManager fragmentManager=activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }


}
