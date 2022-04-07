package com.shym.front_end.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


import com.shym.front_end.R;
import com.shym.front_end.utils.VolleyUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ProgressBar progressBar;
        SharedPreferences sharedPref = getActivity().getSharedPreferences("auth", getActivity().MODE_PRIVATE);
        String token = sharedPref.getString("token", null);
        System.out.println(token);
        if (!token.equals("null")) {
            View view = inflater.inflate(R.layout.fragment_profile, container, false);
            return view;
        } else {
            View view = inflater.inflate(R.layout.fragment_login, container, false);
            progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
            progressBar.setVisibility(View.INVISIBLE);
            mContext = getActivity();
            Button register = view.findViewById(R.id.login_button);
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = ((EditText)getActivity().findViewById(R.id.email_login)).getText().toString();
                    String password = ((EditText)getActivity().findViewById(R.id.password_login)).getText().toString();
                    Map<String, String> data = new HashMap();
                    data.put("email", email);
                    data.put("password", password);
                    VolleyUtils.logIn(data, mContext, progressBar);
                }
            });
            return view;
        }
    }



}