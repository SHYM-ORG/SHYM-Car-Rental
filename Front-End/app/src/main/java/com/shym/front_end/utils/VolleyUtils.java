package com.shym.front_end.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.shym.front_end.ui.bienvenueAgency.BienvenueAgencyActivity;
import com.shym.front_end.ui.bienvenueClient.BienvenueClientActivity;

import org.json.JSONObject;

import java.util.Map;

public class VolleyUtils {
    public static void postData(String url, Map data, Context mContext){
        RequestQueue requstQueue = Volley.newRequestQueue(mContext);
        JSONObject dataJson = new JSONObject(data);
        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, url,dataJson,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
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



}
