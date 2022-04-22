package com.shym.front_end.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.shym.front_end.R;
import com.shym.front_end.utils.VolleyUtils;

import java.util.HashMap;
import java.util.Map;

public class EditProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditProfileFragment() {
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
    public static EditProfileFragment newInstance(String param1, String param2) {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    Context mContext;

    private static void replaceFragment(Fragment fragment, AppCompatActivity activity){
        FragmentManager fragmentManager=activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        //get old infos
        EditText editTextPhone = view.findViewById(R.id.et_phone);
        EditText editTextEmail = view.findViewById(R.id.et_email);
        //EditText editTextLocation = view.findViewById(R.id.et_location);
        EditText editTextDescription = view.findViewById(R.id.et_description);
        Bundle bundle = getArguments();
        if(bundle != null) {
            String phone = bundle.getString("phone");
            String email = bundle.getString("email");
            //String location = bundle.getString("location");
            String description = bundle.getString("description");
            editTextPhone.setText(phone);
            editTextEmail.setText(email);
            //editTextLocation.setText(location);
            editTextDescription.setText(description);
        }
        //update
        mContext = getActivity();
        Button update = view.findViewById(R.id.bt_register);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = ((EditText)getActivity().findViewById(R.id.et_phone)).getText().toString();
                String email = ((EditText)getActivity().findViewById(R.id.et_email)).getText().toString();
                //String location = ((EditText)getActivity().findViewById(R.id.et_location)).getText().toString();
                String description = ((EditText)getActivity().findViewById(R.id.et_description)).getText().toString();
                Map<String, String> data = new HashMap();
                data.put("phone",phone);
                data.put("email",email);
                //data.put("location",location);
                data.put("description",description);
                VolleyUtils.updateProfile(data, mContext);
            }
        });
        //cancel
        Button cancel = view.findViewById(R.id.bt_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgencyProfileFragment agencyProfileFragment = new AgencyProfileFragment();
                replaceFragment(agencyProfileFragment,(AppCompatActivity) mContext);
            }
        });
        /* --change locationg
        EditText locationText = view.findViewById(R.id.et_location);
        locationText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() <= locationText.getTotalPaddingRight()) {
                        System.out.println("hey");
                        return true;
                    }
                }
                return false;
            }
        }); */
        return view;
    }
}
