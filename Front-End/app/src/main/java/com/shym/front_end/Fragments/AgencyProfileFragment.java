package com.shym.front_end.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.shym.front_end.R;
import com.shym.front_end.adapter.CarAdapter;
import com.shym.front_end.databinding.FragmentRentalBinding;
import com.shym.front_end.models.Car;
import com.shym.front_end.utils.VolleyUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgencyProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgencyProfileFragment extends Fragment {


    private RecyclerView recyclerViewCars;
    private CarAdapter carAdapter;

    private ArrayList<Car> carList;

    private TextView textview;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AgencyProfileFragment() {
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
    public static AgencyProfileFragment newInstance(String param1, String param2) {
        AgencyProfileFragment fragment = new AgencyProfileFragment();
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
        View view1 = inflater.inflate(R.layout.fragment_profile_agency, container, false);



        recyclerViewCars =(RecyclerView) view1.findViewById(R.id.recycler_car) ;
        ProgressBar progressBar1;
        progressBar1 =(ProgressBar) view1.findViewById(R.id.progressbar2);;
        progressBar1.setVisibility(View.INVISIBLE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCars.setHasFixedSize(true);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        recyclerViewCars.setLayoutManager(linearLayoutManager);

        carList = new ArrayList<>();
        carAdapter = new CarAdapter(getContext(), carList);
        recyclerViewCars.setAdapter(carAdapter);

        ProgressBar progressBar;
        SharedPreferences sharedPref = getActivity().getSharedPreferences("auth", getActivity().MODE_PRIVATE);
        String token = sharedPref.getString("token", null);
        if (!token.equals("null")) {
            VolleyUtils.readAvailableCars(getContext(),carAdapter,carList,progressBar1);
            mContext = getActivity();
            View view = inflater.inflate(R.layout.fragment_profile_agency, container, false);
            LinearLayout edit = view.findViewById(R.id.editBtnLayout);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String phone = ((TextView)getActivity().findViewById(R.id.profilePhone)).getText().toString();
                    String email = ((TextView)getActivity().findViewById(R.id.profileEmail)).getText().toString();
                    String location = ((TextView)getActivity().findViewById(R.id.profileLocation)).getText().toString();
                    String description = ((TextView)getActivity().findViewById(R.id.profileDescription)).getText().toString();
                    EditProfileFragment editProfileFragment = new EditProfileFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("phone",phone);
                    bundle.putString("email",email);
                    bundle.putString("location",location);
                    bundle.putString("description",description);
                    editProfileFragment.setArguments(bundle);
                    replaceFragment(editProfileFragment,(AppCompatActivity) mContext);
                }
            });

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