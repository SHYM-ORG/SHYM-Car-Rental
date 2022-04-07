package com.shym.front_end.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.shym.front_end.Fragments.HomeFragment;
import com.shym.front_end.R;
import com.shym.front_end.ui.bienvenueAgency.BienvenueAgencyActivity;
import com.shym.front_end.ui.bienvenueClient.BienvenueClientActivity;

import org.json.JSONObject;

import java.util.Map;

public class VolleyUtils {

    public static void logIn(Map data, Context mContext, ProgressBar progressBar){
        RequestQueue requstQueue = Volley.newRequestQueue(mContext);
        JSONObject dataJson = new JSONObject(data);
        progressBar.setVisibility(View.VISIBLE);
        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, "https://shym-api.herokuapp.com/login",dataJson,
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
        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, "https://shym-api.herokuapp.com/api/account/create/" + type,dataJson,
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


}
