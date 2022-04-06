package com.shym.front_end.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.shym.front_end.Fragments.ProfileFragment;
import com.shym.front_end.R;
import com.shym.front_end.utils.VolleyUtils;

import java.util.HashMap;
import java.util.Map;

public class RegisterClientFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterClientFragment() {
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

    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_client, container, false);
        mContext = getActivity();
        Button register = view.findViewById(R.id.register_client_button);
        System.out.println(register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = ((EditText)getActivity().findViewById(R.id.editTextFirstName)).getText().toString();
                String lastName = ((EditText)getActivity().findViewById(R.id.editTextLastName)).getText().toString();
                String email = ((EditText)getActivity().findViewById(R.id.editTextTextEmail)).getText().toString();
                String password = ((EditText)getActivity().findViewById(R.id.editTextPassword)).getText().toString();
                Map<String, String> data = new HashMap();
                data.put("firstName", firstName);
                data.put("lastName", lastName);
                data.put("email", email);
                data.put("password", password);
                VolleyUtils.postData("https://shym-api.herokuapp.com/api/account/create/client", data, mContext);
            }
        });
        return view;
    }


}
